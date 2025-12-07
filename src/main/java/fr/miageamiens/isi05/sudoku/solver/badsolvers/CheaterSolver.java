package fr.miageamiens.isi05.sudoku.solver.badsolvers;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.exceptions.GrilleException;
import fr.miageamiens.isi05.sudoku.solver.Solver;
import fr.miageamiens.isi05.sudoku.solver.SolverException;

import java.util.ArrayList;
import java.util.List;

/**
 * Solveur utilisant des méthodes de triche pour résoudre une grille.
 */
public final class CheaterSolver implements Solver {
    @Override
    public boolean solve(final Grille grille) throws SolverException {
        try {
            // je vide toutes les cases et je remplis comme je veux
            for (int i = 0; i < grille.getDimension(); i++) {
                for (int j = 0; j < grille.getDimension(); j++) {
                    grille.setValeur(i, j, null);
                }
            }

            // je remplis la grille, elle est vide c'est facile ;-)
            List<ValeurDeCase> valeursAutorisees =
                    new ArrayList<>(grille.getValeursAutorisees());
            for (int i = 0; i < grille.getDimension(); i++) {
                int shift = i * grille.getSousDimension()
                        + i / grille.getSousDimension();
                for (int j = 0; j < grille.getDimension(); j++) {
                    int p = (shift + j) % valeursAutorisees.size();
                    grille.setValeur(i, j, valeursAutorisees.get(p));
                }
            }

            // j'ai réussi à résoudre la grille
            return true;

        } catch (GrilleException e) {
            throw new SolverException(e);
        }
    }

}
