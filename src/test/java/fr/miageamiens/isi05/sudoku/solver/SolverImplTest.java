/**
 * 
 */
package fr.miageamiens.isi05.sudoku.solver;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.net.URL;
import org.junit.jupiter.api.BeforeEach;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.parser.GrilleFactory;
import fr.miageamiens.isi05.sudoku.parser.GrilleFactoryImpl;
import fr.miageamiens.isi05.sudoku.parser.GrilleParser;
import fr.miageamiens.isi05.sudoku.parser.GrilleParserException;
import fr.miageamiens.isi05.sudoku.parser.ValeurDeCaseFactory;
import fr.miageamiens.isi05.sudoku.parser.ValeurDeCaseFactoryImpl;
import org.junit.jupiter.api.Test;

/**
 * 
 */
class SolverImplTest {

  private Grille grille;
  private Grille grilleDe9;

  @BeforeEach
  void setUp() throws Exception {
    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();

    URL ressource = getClass()
        .getResource("/grilles/sudoku16-moyen-presque-complete.txt");
    URL ressourceAlt = getClass().getResource("/grilles/sudoku9.txt");
    assertNotNull(ressource,
        "Le fichier de grille n'a pas été trouvé dans le classpath");

    try (InputStream in = ressource.openStream()) {
      grille = GrilleParser.parse(in, factory, valeurFactory);
    }
    try (InputStream in = ressourceAlt.openStream()) {
      grilleDe9 = GrilleParser.parse(in, factory, valeurFactory);
    }

    // Ajoute une vérification de dimension pour comprendre
    if (grille != null) {
      System.out
          .println("Dimension de la grille : " + grille.getDimension());
      System.out
          .println("Dimension de la grille : " + grilleDe9.getDimension());
    }
  }

  @Test
  void testParsing() throws GrilleParserException {
    assertNotNull(grille,
        "La grille ne doit pas être nulle après le parsing");
    System.out.println(grille);
  }

  @Test
  void testSolveGrilleDe16() throws SolverException {
    assertEquals(16, grille.getDimension(),
        "La grille doit être de dimension 16");
    SolverImpl solver = new SolverImpl();
    assertTrue(solver.solve(grille), "La grille doit être résolue");
    assertTrue(solver.solve(grille),
        "La grille doit être complète après résolution");
  }

  @Test
  void testSolveGrilleDe9() throws Exception {

    assertEquals(9, grilleDe9.getDimension(),
        "La grille doit être de dimension 9");
    SolverImpl solver = new SolverImpl();
    assertTrue(solver.solve(grilleDe9), "La grille doit être résolue");
    assertTrue(solver.solve(grilleDe9),
        "La grille doit être complète après résolution");
  }
}
