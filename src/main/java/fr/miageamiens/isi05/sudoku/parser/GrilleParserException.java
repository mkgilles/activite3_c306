package fr.miageamiens.isi05.sudoku.parser;

/**
 * Exception levée lorsqu'une erreur est détectée lors du parsing d'une grille.
 *
 * @author Sébastien Choplin
 */
public class GrilleParserException extends Exception {

    /**
     * Constructeur.
     *
     * @param e exception à l'origine de l'erreur
     */
    public GrilleParserException(final Exception e) {
        super(e);
    }
}
