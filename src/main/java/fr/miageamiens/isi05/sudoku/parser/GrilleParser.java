package fr.miageamiens.isi05.sudoku.parser;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.exceptions.GrilleException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurImpossibleException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Méthodes utilitaires permettant de créer une Grille à partir d'un fichier
 * texte. Il est attendu que la première ligne contienne : le symbole de case
 * vide, suivi des symboles possibles dans la grille (en UTF-8). Les autres
 * lignes contiennent le contenu de la grille.
 * <p>
 * Exemple: -1234 2--- 1--3 3--4 -1-2
 *
 * @author Sébastien Choplin
 */
public interface GrilleParser {

    /**
     * Lit un fichier une resource contenant une grille au format texte.
     *
     * @param grilleFile            fichier contenant la grille
     * @param grilleFactory         factory de grille
     * @param valeurDeGrilleFactory factory de valeur de grille
     * @return la grille
     * @throws GrilleParserException si une erreur survient lors du parsing
     */
    static Grille parse(final File grilleFile,
                        final GrilleFactory grilleFactory,
                        final ValeurDeCaseFactory valeurDeGrilleFactory)
            throws GrilleParserException {
        try (InputStream in = new FileInputStream(grilleFile)) {
            return parse(in, grilleFactory, valeurDeGrilleFactory);
        } catch (IOException e) {
            throw new GrilleParserException(e);
        }
    }


    /**
     * Lit une resource contenant une grille au format texte.
     * <p>
     * Cette resource est chargée depuis le classpath.
     *
     * @param resourcePath          chemin de la resource
     * @param grilleFactory         factory de grille
     * @param valeurDeGrilleFactory factory de valeur de grille
     * @return la grille
     * @throws GrilleParserException si une erreur survient lors du parsing
     */
    static Grille parse(final String resourcePath,
                        final GrilleFactory grilleFactory,
                        final ValeurDeCaseFactory valeurDeGrilleFactory)
            throws GrilleParserException {
        // Try to load as classpath resource first
        InputStream in = GrilleParser.class.getResourceAsStream(resourcePath);
        if (in != null) {
            try (InputStream is = in) {
                return parse(is, grilleFactory, valeurDeGrilleFactory);
            } catch (IOException e) {
                throw new GrilleParserException(e);
            }
        }

        // If not a classpath resource, try interpreting the argument as a filesystem path
        try {
            File f = new File(resourcePath);
            if (!f.exists() && resourcePath.startsWith("/")) {
                // Some callers pass URL.getPath() which on Windows starts with /C:/... -> strip leading '/'
                String alt = resourcePath.substring(1);
                f = new File(alt);
            }
            if (f.exists() && f.isFile()) {
                return parse(f, grilleFactory, valeurDeGrilleFactory);
            }
        } catch (SecurityException ignored) {
            // ignore and fall through to error
        }

        // As a last resort, try to load filename from classpath under /grilles/
        try {
            String name = new File(resourcePath).getName();
            InputStream is = GrilleParser.class.getResourceAsStream("/grilles/" + name);
            if (is != null) {
                try (InputStream in2 = is) {
                    return parse(in2, grilleFactory, valeurDeGrilleFactory);
                }
            }
        } catch (Exception ignored) {
        }

        throw new GrilleParserException(new IOException("Resource or file not found: " + resourcePath));
    }

    /**
     * Lit un flux contenant une grille au format texte.
     *
     * @param in                    flux en entrée
     * @param grilleFactory         factory de grille
     * @param valeurDeGrilleFactory factory de valeur de grille
     * @return la grille
     * @throws GrilleParserException si une erreur survient lors du parsing
     */
    static Grille parse(final InputStream in,
                        final GrilleFactory grilleFactory,
                        final ValeurDeCaseFactory valeurDeGrilleFactory)
            throws GrilleParserException {
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(in,
                                     StandardCharsets.UTF_8))) {

            String line = reader.readLine();
            if (line == null || line.isEmpty()) {
                throw new IllegalArgumentException("pas de première ligne");
            }
            final int dimension = line.length() - 1;
            final char vide = line.charAt(0);
            Map<Character, ValeurDeCase> valeurDeCaseHashMap =
                    new HashMap<>();
            for (int i = 1; i < line.length(); i++) {
                char value = line.charAt(i);
                if (value == vide) {
                    continue;
                }
                if (valeurDeCaseHashMap.containsKey(value)) {
                    throw new IllegalArgumentException(
                            "valeur possible dupliquée : " + value);
                }
                valeurDeCaseHashMap.put(value,
                        valeurDeGrilleFactory.create(value));
            }

            if (valeurDeCaseHashMap.size() != dimension) {
                throw new IllegalArgumentException(
                        "pas le bon nombre de valeurs possibles");
            }
            List<ValeurDeCase> valeursDeCasesAutorisees =
                    new ArrayList<>(valeurDeCaseHashMap.values());
            ValeurDeCase[][] grilleTab = new ValeurDeCase[dimension][dimension];


            for (int i = 0; i < dimension; i++) {
                line = reader.readLine();
                if (line == null || line.length() != dimension) {
                    throw new IOException(
                            "pas le bon nombre sur la ligne : " + line);
                }
                for (int j = 0; j < dimension; j++) {
                    char c = line.charAt(j);
                    if (c != vide) {
                        ValeurDeCase valeurDeCase = valeurDeCaseHashMap.get(c);
                        if (valeurDeCase == null) {
                            throw new ValeurImpossibleException(c
                                    + " impossible");
                        }
                        grilleTab[i][j] = valeurDeCase;
                    }
                }
            }


            return grilleFactory.create(valeursDeCasesAutorisees, grilleTab);

        } catch (IOException | GrilleException e) {
            throw new GrilleParserException(e);
        }
    }

}