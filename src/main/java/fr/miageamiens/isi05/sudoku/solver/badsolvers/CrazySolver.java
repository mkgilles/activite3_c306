package fr.miageamiens.isi05.sudoku.solver.badsolvers;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.solver.Solver;
import fr.miageamiens.isi05.sudoku.solver.Stoppable;

/**
 * Solveur fou, qui boucle indéfiniment.
 * <p>
 * Comme il implémente l'interface Stoppable, il est possible de l'arrêter.
 */
public final class CrazySolver implements Solver, Stoppable {

    /**
     * Indique si le solveur doit s'arrêter.
     */
    private volatile boolean stop = false;

    @Override
    public boolean solve(final Grille grille) {
        final long waitTimeInLoop = 1000L;
        while (!stop) {
            // je boucle sans rien faire
            try {
                Thread.sleep(waitTimeInLoop);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public void stop() {
        stop = true;
    }
}
