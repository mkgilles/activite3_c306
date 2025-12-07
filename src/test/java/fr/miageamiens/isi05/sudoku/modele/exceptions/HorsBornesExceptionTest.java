/**
 * 
 */
package fr.miageamiens.isi05.sudoku.modele.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HorsBornesExceptionTest {

  @Test
  void testHorsBornesExceptionWithMessage() {
    String message = "Indice hors bornes";
    HorsBornesException ex = new HorsBornesException(message);

    assertNotNull(ex);
    assertEquals(message, ex.getMessage(),
        "Le message doit être correctement propagé");
  }

  @Test
  void testHorsBornesExceptionWithCause() {
    Exception cause = new Exception("Cause initiale");
    // Use constructor accepting a cause
    HorsBornesException ex = new HorsBornesException(cause);

    assertNotNull(ex);
    assertEquals(cause, ex.getCause(), "La cause doit être correctement propagée");
  }

  @Test
  void testHorsBornesExceptionWithMessageAndCause() {
    Exception cause = new Exception("Cause initiale");
    HorsBornesException ex = new HorsBornesException("Erreur hors bornes", cause);

    assertNotNull(ex);
    assertEquals("Erreur hors bornes", ex.getMessage());
    assertEquals(cause, ex.getCause());
  }
}