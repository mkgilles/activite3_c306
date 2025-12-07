/**
 * 
 */
package fr.miageamiens.isi05.sudoku.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;

/**
 * 
 */
class GrilleParserTest {

  /**
   * Teste le parsing d'un fichier valide.
   * 
   * @throws GrilleParserException
   */
  @Test
  void testParseValideFile() throws GrilleParserException {

    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();
    File file = new File("src/test/resources/grilles/sudoku16-a.txt");
    Grille grille = GrilleParser.parse(file, factory, valeurFactory);
    assertNotNull(grille, "La grille ne doit pas être nulle");
  }

  /**
   * Teste le parsing d'un fichier invalide.
   */
  @Test
  void testParseInvalideFile() {

    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();
    File file = new File("src/test/resources/grilles/sudoku-invalide.txt");
    assertThrows(GrilleParserException.class, () -> {
      GrilleParser.parse(file, factory, valeurFactory);
    }, "Une GrilleParserException doit être levée pour un fichier invalide");
  }

  /**
   * Teste le parsing d'un fichier inexistant.
   */
  @Test
  void testParseNonExistFile() {

    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();
    File file = new File(
        "src/test/resources/grilles/sudoku-nonexistent.txt");
    assertThrows(GrilleParserException.class, () -> {
      GrilleParser.parse(file, factory, valeurFactory);
    }, "Une GrilleParserException doit être levée pour un fichier inexistant");
  }

  /**
   * Teste le parsing d'un fichier vide.
   * 
   * @throws GrilleParserException
   * @throws HorsBornesException
   */
  @Test
  void testParseFileVide()
      throws GrilleParserException, HorsBornesException {

    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();
    File file = new File("src/test/resources/grilles/sudoku4x4-vide.txt");
    Grille grille = GrilleParser.parse(file, factory, valeurFactory);

    for (int i = 0; i < grille.getDimension(); i++) {
      for (int j = 0; j < grille.getDimension(); j++) {
        assertNull(grille.getValeur(i, j),
            "Toutes les cases doivent être vides dans une grille vide");
      }

    }
  }

  /**
   * Teste le parsing d'une ressource avec valeur dupliquée dans la première
   * ligne.
   * 
   * @throws GrilleParserException
   */
  @Test
  void testParseResourceValeurDupliquee() throws GrilleParserException {
    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();
    String resourcePath = "/grilles/sudoku-valeur-dupliquee.txt";
    assertThrows(GrilleParserException.class, () -> {
      GrilleParser.parse(resourcePath, factory, valeurFactory);
    }, "Une GrilleParserException doit être levée pour une ressource avec valeur dupliquée");
  }
}