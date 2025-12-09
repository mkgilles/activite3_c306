package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Classe de base des exceptions liées aux grilles de sudoku.
 * @author Sébastien Choplin
 */
public class GrilleException extends Exception {
    public GrilleException(final String msg) {
        super(msg);
    }

    /**
     * Constructeur avec message.
     * 
     * @param cause
     */
    public GrilleException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructeur avec message plus cause.
     * 
     * @param msg
     * @param cause
     */
    public GrilleException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}