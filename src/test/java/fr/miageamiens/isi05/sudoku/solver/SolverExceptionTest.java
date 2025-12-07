/**
 * 
 */
package fr.miageamiens.isi05.sudoku.solver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class SolverExceptionTest {

  /**
   * Test de levee de l'exception lors de la resolution du sudoku.
   */
  @Test
  void testSolverException() {
    Exception cause =  new Exception("Cause de l'exception");    
    SolverException ex = new SolverException(cause);
    assertNotNull(ex, "L'exception ne doit pas être nulle");
      assertEquals(cause, ex.getCause(),
        "Le message doit être correctement propagé");
  }

}
