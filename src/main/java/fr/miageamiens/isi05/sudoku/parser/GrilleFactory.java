package fr.miageamiens.isi05.sudoku.parser;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;

import java.util.Collection;

/**
 * Factory permettant de créer des grilles de sudoku à partir des valeurs
 * autorisées dans la grille et des valeurs initiales.
 *
 * @author Sébastien Choplin
 */
public interface GrilleFactory {

  /**
   * Crée une grille de sudoku à partir des valeurs autorisées dans la grille
   * et des valeurs initiales.
   *
   * @param valeurDeCasesAutorisees valeurs autorisées dans la grille
   * @param valeurDeCasesInitiales  tableau des valeurs initiales de la
   *                                grille correspondant à la grille de
   *                                sudoku. null pour une case vide.
   * 
   * @return grille de sudoku
   */
  Grille create(Collection<ValeurDeCase> valeurDeCasesAutorisees,
      ValeurDeCase[][] valeurDeCasesInitiales);

}
