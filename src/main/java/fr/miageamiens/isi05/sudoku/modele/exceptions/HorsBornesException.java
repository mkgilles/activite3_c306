package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Exception levée lorsqu'une valeur est hors des bornes autorisées.
 *
 * @author Sébastien Choplin
 */
public class HorsBornesException extends GrilleException {
    /**
     * Constructeur avec message.
     * @param msg Message d'erreur.
     */
    public HorsBornesException(final String msg) {
        super(msg);
    }

    /**
     * Constructeur avec cause.
     * @param cause Throwable cause.
     */
    public HorsBornesException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructeur avec message et cause.
     * @param msg Message d'erreur.
     * @param cause Throwable cause.
     */
    public HorsBornesException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}