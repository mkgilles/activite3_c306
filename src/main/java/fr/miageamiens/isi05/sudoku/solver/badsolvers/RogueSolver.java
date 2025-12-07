package fr.miageamiens.isi05.sudoku.solver.badsolvers;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.exceptions.GrilleException;
import fr.miageamiens.isi05.sudoku.solver.Solver;
import fr.miageamiens.isi05.sudoku.solver.SolverException;

import java.util.ArrayList;
import java.util.List;

/**
 * Solveur qui ne respecte pas les règles du jeu.
 */
public final class RogueSolver implements Solver {
    @Override
    public boolean solve(final Grille grille) throws SolverException {
        try {
            // je remplis la grille, je ne me préoccupe pas des règles
            List<ValeurDeCase> valeursAutorisees
                    = new ArrayList<>(grille.getValeursAutorisees());
            ValeurDeCase valeurDeCase = valeursAutorisees.get(0);
            for (int i = 0; i < grille.getDimension(); i++) {
                for (int j = 0; j < grille.getDimension(); j++) {
                    if (!grille.isValeurInitiale(i, j)) {
                        // je mets partout la même valeur,
                        // mais je ne touche pas aux valeurs initiales
                        grille.setValeur(i, j, valeurDeCase);
                    }
                }
            }
            // j'ai réussi à résoudre la grille ;-)
            return true;
        } catch (GrilleException e) {
            throw new SolverException(e);
        }
    }
}
