/**
 * 
 */
package fr.miageamiens.isi05.sudoku.modele.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class ValeurInitialeModificationExceptionTest {

  /**
   * Test de la levee d'une exception avec un message.
   */
  @Test
  void testValeurInitialeModificationException() {
    String message = "Modification d'une valeur initiale interdite";
    ValeurInitialeModificationException ex = new ValeurInitialeModificationException(
        message);

    assertNotNull(ex, "L'exception ne doit pas être nulle");
    assertEquals(message, ex.getMessage(),
        "Le message doit être correctement propagé");
  }

}
