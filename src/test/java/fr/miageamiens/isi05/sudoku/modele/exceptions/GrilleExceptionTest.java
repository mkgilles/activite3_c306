/**
 * 
 */
package fr.miageamiens.isi05.sudoku.modele.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class GrilleExceptionTest {

  /**
   * Methode de test de levee d'une exception avec cause
   */
  @Test
  void testGrilleExceptionException() {
    Exception cause = new Exception("Cause de l'exception");
    GrilleException grilleException = new GrilleException(cause);
    assertNotNull(grilleException);
    assertEquals(cause, grilleException.getCause());
  }

  /**
   * Test de levee d'une exception avec un message.
   */
  @Test
  void testGrilleExceptionString() {
    Exception messageException = new GrilleException("Message de l'exception");
    assertNotNull(messageException);
    assertEquals("Message de l'exception", messageException.getMessage());
  }

}
