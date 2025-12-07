package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Exception levée lorsqu'une valeur est impossible dans une case.
 *
 * @author Sébastien Choplin
 */
public class ValeurImpossibleException extends GrilleException {
    /**
     * Constructeur.
     * @param msg Message d'erreur.
     */
    public ValeurImpossibleException(final String msg) {
        super(msg);
    }
}
