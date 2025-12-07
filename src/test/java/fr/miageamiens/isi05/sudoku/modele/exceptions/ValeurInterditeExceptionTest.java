/**
 * 
 */
package fr.miageamiens.isi05.sudoku.modele.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class ValeurInterditeExceptionTest {

  /**
   * Test method for {@link fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInterditeException#ValeurInterditeException(java.lang.String)}.
   */
  @Test
  void testValeurInterditeException() {
    String message = "Valeur interdite dans la grille";
    Exception ex = new ValeurInterditeException(message);
    
    assertNotNull(ex, "L'exception ne doit pas être nulle");
    assertEquals(message, ex.getMessage(), "Le message doit être correctement propagé");
  }

}
