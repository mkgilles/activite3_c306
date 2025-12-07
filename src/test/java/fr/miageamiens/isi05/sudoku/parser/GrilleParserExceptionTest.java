/**
 * 
 */
package fr.miageamiens.isi05.sudoku.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Exception levée lorsqu'une erreur est détectée lors du parsing d'une grille.
 *
 * @author Sébastien Choplin
 */
class GrilleParserExceptionTest {

  /**
   * Teste que la cause de l'exception est bien celle fournie au constructeur.
   */
  @Test
  void test() {
    Exception cause = new Exception("Cause de l'erreur du parsing");
    GrilleParserException exception = new GrilleParserException(cause);
    assertEquals(cause, exception.getCause(),
        "La cause de l'exception doit être celle fournie au constructeur");
  }

}
