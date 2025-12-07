package fr.miageamiens.isi05.sudoku.parser;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Collection;

import fr.miageamiens.isi05.sudoku.modele.Grille;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCaseAsChar;
import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;

import org.junit.jupiter.api.Test;

/**
 * Test de la classe GrilleFactoryImpl.
 */
class GrilleFactoryImplTest {

  // Factory de ValeurDeCase
  ValeurDeCaseFactory valeurDeCaseFactory;
  // Factory de creation de Grilles
  GrilleFactoryImpl grilleFactory;
  // Une instance de grille
  Grille grille;

  /**
   * Crée une grille de sudoku à partir des valeurs autorisées dans la grille
   * et des valeurs initiales.
   *
   * @param valeurDeCasesAutorisees valeurs autorisées dans la grille
   * @param valeurDeCasesInitiales  tableau des valeurs initiales de la
   *                                grille correspondant à la grille de
   *                                sudoku. null pour une case vide.
   * 
   * @return grille de sudoku
   */
  @Test
  void testCreateGrilleDe9() {

    Collection<ValeurDeCase> valeurDeCasesAutorisees = new ArrayList<>();
    valeurDeCaseFactory = new ValeurDeCaseFactoryImpl();
    for (char i = '1'; i <= '9'; i++) {
      valeurDeCasesAutorisees.add(valeurDeCaseFactory.create(i));
    }

    ValeurDeCase[][] valeurDeCasesInitiales = new ValeurDeCase[9][9];
    valeurDeCasesInitiales[0][2] = valeurDeCaseFactory.create('5');
    valeurDeCasesInitiales[0][1] = valeurDeCaseFactory.create('5');

    Grille grille = new GrilleFactoryImpl().create(valeurDeCasesAutorisees,
        valeurDeCasesInitiales);
    // Grille grille = factory.create(valeurDeCasesAutorisees,
    // valeurDeCasesInitiales);
    assertNotNull(grille, "La grille ne doit pas être nulle");

    assertEquals(9, grille.getDimension(),
        "La taille de la grille doit être 9");

  }

  /**
   * Crée une grille de sudoku de dimension 4x4.
   */
  @Test
  void testCreateGrilleDe4() {

    Collection<ValeurDeCase> valeurDeCasesAutorisees = new ArrayList<>();
    valeurDeCaseFactory = new ValeurDeCaseFactoryImpl();
    for (char i = '1'; i <= '4'; i++) {
      valeurDeCasesAutorisees.add(valeurDeCaseFactory.create(i));
    }

    ValeurDeCase[][] valeurDeCasesInitiales = new ValeurDeCase[4][4];
    valeurDeCasesInitiales[0][3] = valeurDeCaseFactory.create('1');
    valeurDeCasesInitiales[0][1] = valeurDeCaseFactory.create('2');

    Grille grille = new GrilleFactoryImpl().create(valeurDeCasesAutorisees,
        valeurDeCasesInitiales);
    assertNotNull(grille, "La grille ne doit pas être nulle");

    assertEquals(4, grille.getDimension(),
        "La taille de la grille doit être 4");

  }

  /**
   * Crée une grille de sudoku de dimension 16x16.
   * 
   * @throws HorsBornesException si une valeur est hors bornes
   */
  @Test
  void testCreateGrilleDe16() throws HorsBornesException {
    Collection<ValeurDeCase> valeurDeCasesAutorisees = new ArrayList<>();
    valeurDeCaseFactory = new ValeurDeCaseFactoryImpl();

    // chiffres 1 à 9
    for (char i = '0'; i <= '9'; i++) {
      valeurDeCasesAutorisees.add(valeurDeCaseFactory.create(i));
    }
    // lettres a à f
    for (char c = 'a'; c <= 'f'; c++) {
      valeurDeCasesAutorisees.add(valeurDeCaseFactory.create(c));
    }

    ValeurDeCase[][] valeurDeCasesInitiales = new ValeurDeCase[16][16];
    valeurDeCasesInitiales[0][2] = valeurDeCaseFactory.create('a');
    valeurDeCasesInitiales[0][1] = valeurDeCaseFactory.create('b');

    GrilleFactoryImpl factory = new GrilleFactoryImpl();
    Grille grille = factory.create(valeurDeCasesAutorisees,
        valeurDeCasesInitiales);

    assertNotNull(grille, "La grille ne doit pas être nulle");
    assertEquals(16, grille.getDimension(),
        "La taille de la grille doit être 16");
    assertEquals(valeurDeCaseFactory.create('b'), grille.getValeur(0, 1),
        "La valeur de la case (0,1) doit être 'b'");
  }

}