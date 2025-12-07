package fr.miageamiens.isi05.sudoku.solver;

/**
 * Exception générée par le solveur. Cette exception est utilisée pour
 * encapsuler les exceptions générées par le solveur.
 *
 * @author Sébastien Choplin
 */
public class SolverException extends Exception {

    /**
     * Constructeur.
     *
     * @param e Exception à encapsuler.
     */
    public SolverException(final Exception e) {
        super(e);
    }
}
