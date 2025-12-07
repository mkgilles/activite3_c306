package fr.miageamiens.isi05.sudoku.solver;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurImpossibleException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInitialeModificationException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInterditeException;

import java.util.Collection;
/**
 * Implémentation de Solver utilisant une approche par backtracking.
 *
 * @author Sébastien Choplin
 */

public class SolverImpl implements Solver {
  /**
   * Résout une Grille.
   *
   * @param grille Grille à résoudre
   * @return true si la grille a été résolue
   * @throws SolverException si la grille ne peut pas être résolue
   */
  @Override
  public boolean solve(Grille grille) throws SolverException {
    boolean solved = false;

    try {
      if (grille.isComplete()) {
        solved = true;
      } else {
        int dimension = grille.getDimension();

        for (int ligne = 0; ligne < dimension && !solved; ligne++) {
          for (int col = 0; col < dimension && !solved; col++) {
            if (grille.getValeur(ligne, col) == null) {
              Collection<ValeurDeCase> valeurs = grille
                  .getValeursAutorisees();
              for (ValeurDeCase valeur : valeurs) {
                try {
                  if (grille.isPossible(ligne, col, valeur)) {
                    grille.setValeur(ligne, col, valeur);
                    if (solve(grille)) {
                      solved = true;
                      break; // sortir de la boucle des valeurs
                    }
                    // retour en arrière
                    grille.setValeur(ligne, col, null);
                  }
                } catch (ValeurImpossibleException | ValeurInterditeException
                    | HorsBornesException
                    | ValeurInitialeModificationException e) {
                  // ignorer et tester la valeur suivante
                }
              }
            }
          }
        }
      }
    } catch (HorsBornesException e) {
      throw new SolverException(e);
    }

    return solved; // un seul return
  }
}