package fr.miageamiens.isi05.sudoku.modele;

/**
 * Interface des éléments de la grille de sudoku.
 *
 * @author Sébastien Choplin
 */
public interface ValeurDeCase {

  /**
   * Compare cet objet à un autre.
   * 
   * @param obj objet à comparer
   * 
   * @return true si les objets sont égaux, false sinon
   */
  boolean equals(Object obj);

  /**
   * Retourne le code de hachage de cet objet.
   * 
   * @return code de hachage
   */
  int hashCode();

  /**
   * Retourne une représentation textuelle de cet objet.
   * 
   * @return représentation textuelle
   */
  String toString();

}
