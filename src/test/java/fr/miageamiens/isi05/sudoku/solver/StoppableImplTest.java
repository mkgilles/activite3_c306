/**
 * 
 */
package fr.miageamiens.isi05.sudoku.solver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests unitaires de la classe StoppableImpl.
 */
class StoppableImplTest {

  /**
   * Teste de la methode isStopped() avant et après appel à stop().
   */
  @Test
  void testInitialState() {
    StoppableImpl stoppable = new StoppableImpl();
    assertFalse(stoppable.isStopped(),
        "Par défaut, le traitement ne doit pas être arrêté");
  }

  /**
   * Teste de la methode isStopped() après appel à stop().
   */
  @Test
  void testStopChangesState() {
    StoppableImpl stoppable = new StoppableImpl();
    stoppable.stop();
    assertTrue(stoppable.isStopped(),
        "Après appel à stop(), le traitement doit être arrêté");
  }

}
