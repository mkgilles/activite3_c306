package fr.miageamiens.isi05.sudoku.modele;

import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurImpossibleException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInitialeModificationException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInterditeException;
import fr.miageamiens.isi05.sudoku.parser.GrilleFactory;
import fr.miageamiens.isi05.sudoku.parser.GrilleFactoryImpl;
import fr.miageamiens.isi05.sudoku.parser.GrilleParser;
import fr.miageamiens.isi05.sudoku.parser.GrilleParserException;
import fr.miageamiens.isi05.sudoku.parser.ValeurDeCaseFactory;
import fr.miageamiens.isi05.sudoku.parser.ValeurDeCaseFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrilleImplTest {

  private Grille grille;

  @BeforeEach
  void setup() throws GrilleParserException, IOException {
    GrilleFactory factory = new GrilleFactoryImpl();
    ValeurDeCaseFactory valeurFactory = new ValeurDeCaseFactoryImpl();

    URL resource = getClass().getResource("/grilles/sudoku9.txt");
    assertNotNull(resource,
        "Le fichier de grille n'a pas été trouvé dans le classpath");

    try (InputStream in = resource.openStream()) {
      grille = GrilleParser.parse(in, factory, valeurFactory);
    }
  }

  @Test
  void testGetValeursAutorisees() {
    Collection<ValeurDeCase> valeurs = grille.getValeursAutorisees();

    assertEquals(9, valeurs.size());

    List<String> attendues = valeurs.stream().map(ValeurDeCase::toString)
        .toList();

    assertEquals(List.of("1", "2", "3", "4", "5", "6", "7", "8", "9"),
        attendues);
  }

  @Test
  void testGetDimension() {
    assertEquals(9, grille.getDimension(),
        "La dimension de la grille doit être de 9");
  }

  @Test
  void testGetSousDimension() {
    assertEquals(3, grille.getSousDimension(),
        "La sous-dimension de la grille doit être de 3");
  }

  @Test
  void testSetValeurValide() throws Exception {
    ValeurDeCase valeurValide = new ValeurDeCaseFactoryImpl().create('5');
    grille.setValeur(0, 2, valeurValide);

    assertEquals(valeurValide, grille.getValeur(0, 2),
        "La valeur doit être correctement définie dans la grille");
  }

  @Test
  void testSetValeurInterdite() {
    ValeurDeCase valeurInterdite = new ValeurDeCaseFactoryImpl().create('X');
    assertThrows(ValeurInterditeException.class, () -> {
      grille.setValeur(0, 2, valeurInterdite);
    });
  }

  @Test
  void testSetValeurImpossible()
      throws ValeurImpossibleException, ValeurInterditeException,
      HorsBornesException, ValeurInitialeModificationException {
    ValeurDeCase valeurPossible = new ValeurDeCaseFactoryImpl().create('5');
    grille.setValeur(0, 0, valeurPossible);
    assertThrows(ValeurImpossibleException.class, () -> {
      grille.setValeur(0, 2, valeurPossible);
    });
  }

  @Test
  void testSetValeurHorsBornes()
      throws HorsBornesException, ValeurImpossibleException,
      ValeurInterditeException, ValeurInitialeModificationException {

    ValeurDeCase valeur = new ValeurDeCaseFactoryImpl().create('3');
    try {
      grille.setValeur(-1, 0, valeur);
      fail("Une HorsBornesException aurait dû être levée");
    } catch (HorsBornesException e) {
      // attendu
    } catch (Exception e) {
      fail("Exception inattendue: " + e.getMessage());
    }

  }

  @Test
  void testGetValeurBornee() {
    assertDoesNotThrow(() -> grille.getValeur(0, 0));
  }

  @Test
  void testGetValeurHorsBornes() {
    assertThrows(HorsBornesException.class, () -> {
      grille.getValeur(10, 10);
    });
  }

  @Test
  void testIsComplete() {
    assertFalse(grille.isComplete(),
        "La grille ne doit pas être complète au départ");
  }

  @Test
  void testIsPossible() {
    ValeurDeCase valeurPossible = new ValeurDeCaseFactoryImpl().create('5');
    assertThrows(HorsBornesException.class, () -> {
      grille.isPossible(-1, 0, valeurPossible);
    });
  }

  @Test
  void testIsValeurInitiale() {
    try {
      // from sudoku9.txt row 0, column 1 contains '6' (initial), column 0 is
      // empty
      assertTrue(grille.isValeurInitiale(0, 1),
          "La case (0,1) doit être une valeur initiale");
      assertFalse(grille.isValeurInitiale(0, 0),
          "La case (0,0) ne doit pas être une valeur initiale");
    } catch (Exception e) {
      fail("Exception inattendue: " + e.getMessage());
    }
  }

}