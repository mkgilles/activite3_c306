package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Exception levée lorsqu'un élément est interdit dans une grille.
 *
 * @author Sébastien Choplin
 */
public class ValeurInterditeException extends GrilleException {
    /**
     * Constructeur.
     * @param msg Message d'erreur.
     */
    public ValeurInterditeException(final String msg) {
        super(msg);
    }
}
