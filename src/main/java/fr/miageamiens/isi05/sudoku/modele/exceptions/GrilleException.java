package fr.miageamiens.isi05.sudoku.modele.exceptions;

/**
 * Classe de base des exceptions liées aux grilles de sudoku.
 * @author Sébastien Choplin
 */
public class GrilleException extends Exception {
    public GrilleException(final String msg) {
        super(msg);
    }

    public GrilleException(final Throwable cause) {
        super(cause);
    }

    public GrilleException(final String msg, final Throwable cause) {
        super(msg, cause);
    }
}