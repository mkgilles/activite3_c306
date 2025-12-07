package fr.miageamiens.isi05.sudoku.modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Interface des éléments de la grille de sudoku.
 *
 * @author Sébastien Choplin
 */
class ValeurDeCaseAsCharTest {
  
 
  /**
   * Retourne le code de hachage de cet objet.
   * 
   * @return code de hachage
   */
  @Test
  void testHashCode() {
    assertEquals((int) '1', new ValeurDeCaseAsChar().hashCode());
  }

  /**
   * Test du constructeur
   */
  @Test
  void testValeurDeCaseAsChar() {
    
    assertDoesNotThrow(() -> new ValeurDeCaseAsChar());
  }

  /**
   * Compare cet objet à un autre.
   * 
   * @param obj objet à comparer
   * 
   * @return true si les objets sont égaux, false sinon
   */
  @Test
  void testEqualsObject() {
    assertTrue(
        new ValeurDeCaseAsChar().equals(new ValeurDeCaseAsChar()));
  }

  /**
   * Retourne une représentation textuelle de cet objet.
   * 
   * @return représentation textuelle
   */
  @Test
  void testToString() {
    assertEquals("1", new ValeurDeCaseAsChar().toString());
  }

}
