package fr.miageamiens.isi05.sudoku.solver.badsolvers;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.solver.Solver;

/**
 * Solveur paresseux, qui ne fait rien mais dit que la grille est résolue.
 */
public final class LazySolver implements Solver {

    @Override
    public boolean solve(final Grille grille) {
        return true;
    }
}
