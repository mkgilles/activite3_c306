/**
 * 
 */
package fr.miageamiens.isi05.sudoku.modele;

/**
 * Implementation simple d'une valeur de case stockant un caractère.
 */
public class ValeurDeCaseAsChar implements ValeurDeCase {
  private char valeur;
  
  /**
   * Constructeur par défaut initialisant la valeur à '9'.
   */
  public ValeurDeCaseAsChar() {
    this.valeur = '1';
  }
  /**
   * Initialise la valeur (réservé au package).
   * 
   * @param value valeur à stocker
   */
  public void init(char valeur) {
    this.valeur = valeur;
  }

  @Override
  /**
   * Compare cet objet à un autre.
   * 
   * @param obj objet à comparer
   * 
   * @return true si les objets sont égaux, false sinon
   */
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof ValeurDeCaseAsChar))
      return false;
    ValeurDeCaseAsChar other = (ValeurDeCaseAsChar) obj;
    return this.valeur == other.valeur;

  }

  @Override
  /**
   * Retourne le code de hachage de cet objet.
   * 
   * @return code de hachage
   */
  public int hashCode() {
    return Character.hashCode(valeur);
  }

  @Override
  /**
   * Retourne une représentation textuelle de cet objet.
   * 
   * @return représentation textuelle
   */
  public String toString() {
    return String.valueOf(valeur);
  }

}