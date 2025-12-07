package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Exception générée lorsqu'on tente de modifier une valeur initiale. Cette
 * exception est utilisée pour signaler qu'une valeur initiale ne peut pas être
 * modifiée.
 *
 * @author Sébastien Choplin
 */
public class ValeurInitialeModificationException extends GrilleException {

    /**
     * Constructeur.
     *
     * @param msg Message d'erreur.
     */
    public ValeurInitialeModificationException(final String msg) {
        super(msg);
    }
}
