package fr.miageamiens.isi05.sudoku.solver;

import fr.miageamiens.isi05.sudoku.modele.Grille;

/**
 * Interface de résolution de Grille.
 *
 * @author Sébastien Choplin
 */
public interface Solver {
    /**
     * Résout une Grille.
     *
     * @param grille Grille à résoudre
     * @return true si la grille a été résolue
     * @throws SolverException si la grille ne peut pas être résolue
     */
    boolean solve(Grille grille) throws SolverException;
}
