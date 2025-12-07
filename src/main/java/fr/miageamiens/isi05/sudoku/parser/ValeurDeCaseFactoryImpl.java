/**
 * 
 */
package fr.miageamiens.isi05.sudoku.parser;

import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;
import fr.miageamiens.isi05.sudoku.modele.ValeurDeCaseAsChar;

/**
 * Factory creating ValeurDeCaseImpl instances from an object (char or
 * String).
 */
public class ValeurDeCaseFactoryImpl implements ValeurDeCaseFactory {
  private char f;

  @SuppressWarnings("null")
  @Override
  public ValeurDeCase create(Object valeur) {
    if (valeur == null) {
      f = (Character) null;
    }
    if (valeur instanceof Character) {
      f = (char) valeur;
    } else if (valeur instanceof ValeurDeCase
        && ((String) valeur).length() == 1) {
      f = ((String) valeur).charAt(0);
    } else {
      throw new IllegalArgumentException(
          "Valeur non convertible en caractere: " + valeur);

    }
    ValeurDeCaseAsChar vdc = new ValeurDeCaseAsChar();
    vdc.init(f);
    return vdc;
  }

}