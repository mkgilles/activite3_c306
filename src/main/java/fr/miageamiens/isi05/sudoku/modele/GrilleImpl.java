package fr.miageamiens.isi05.sudoku.modele;

import java.util.Collection;
import java.util.List;
import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurImpossibleException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInitialeModificationException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInterditeException;

/**
 * Interface de grille de sudoku. Chaque case d'une Grille peut contenir un
 * ElementDeGrille ou null si aucun élément n'est placé. Une Grille doit
 * toujours respecter les règles du sudoku.
 * <p>
 * Une Grille peut contenir des cases qui ne doivent pas être modifiées
 * (contenant les valeurs initiales de la Grille)
 * </p>
 *
 * @author Sébastien Choplin
 */
public class GrilleImpl implements Grille {

  /**
   * @return the valeurDeCaseInitiales
   */
  public ValeurDeCase[][] getValeurDeCaseInitiales() {
    return ValeurDeCaseInitiales;
  }

  /**
   * @param valeurDeCaseInitiales the valeurDeCaseInitiales to set
   */
  public void setValeurDeCaseInitiales(
      ValeurDeCase[][] valeurDeCaseInitiales) {
    ValeurDeCaseInitiales = valeurDeCaseInitiales;
  }

  private List<ValeurDeCase> valeursAutorisees;
  private int dimension;
  private int sousDimension;
  private ValeurDeCase[][] ValeurDeCaseInitiales;
  private ValeurDeCase[][] grille;

  /**
   * Constructeur par defaut de GrillleImpl.
   */
  public GrilleImpl() {
    this.valeursAutorisees = null;
    this.dimension = 0;
    this.sousDimension = 0;
    ValeurDeCaseInitiales = new ValeurDeCase[0][0];
  }

  /**
   * Methode d'initialisation de la grille.
   * 
   * @param valAutorisees valeurs autorisées dans la grille
   * @param valInitiales  valeurs initiales de la grille
   */
  public void initialisation(Collection<ValeurDeCase> valAutorisees,
      ValeurDeCase[][] valInitiales) {
    // Initialisation des valeurs autorisées
    List<ValeurDeCase> valeursAutorisees = null;
    this.valeursAutorisees = (List<ValeurDeCase>) valAutorisees;
    this.dimension = valAutorisees.size();
    this.sousDimension = (int) Math.sqrt(dimension);
    this.ValeurDeCaseInitiales = valInitiales;

    // Initialisation des valeurs initiales à utiliser dans la grille
    this.grille = new ValeurDeCase[dimension][dimension];
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        this.ValeurDeCaseInitiales[i][j] = ValeurDeCaseInitiales[i][j];
        this.grille[i][j] = ValeurDeCaseInitiales[i][j];
      }
    }
  }

  @Override
  /**
   * Renvoie les ElementDeGrille pouvant exister dans la grille.
   *
   * @return ensemble des éléments de grille pouvant être mis dans la grille
   */
  public Collection<ValeurDeCase> getValeursAutorisees() {
    return valeursAutorisees;
  }

  @Override
  /**
   * Renvoie la dimension de la grille. Exemple: 9 pour une grille de 9x9.
   *
   * @return largeur ou hauteur de la grille
   */
  public int getDimension() {
    return dimension;
  }

  @Override
  /**
   * Renvoie la dimension d'une sous-grille. Exemple: 3 pour une grille de
   * 3x3.
   *
   * @return largeur ou hauteur d'une sous-grille
   */
  public int getSousDimension() {
    return sousDimension;
  }

  /**
   * MEthode utilitaire de verification de bornes.
   * 
   * @param x
   * @param y
   * 
   * @throws HorsBornesException
   */
  private void checkBounds(int x, int y) throws HorsBornesException {
    if (x < 0 || y < 0 || x >= dimension || y >= dimension) {
      throw new HorsBornesException(
          "Coordonnées hors bornes: (" + x + "," + y + ")");
    }
  }

  @Override
  /**
   * Affecte une valeur dans une case de la grille, ou null pour 'vider' la
   * case.
   *
   * @param x     position x dans la grille
   * @param y     position y dans la grille
   * @param value élément de grille à mettre dans la case, null pour vider la
   *              case
   * 
   * @throws ValeurImpossibleException           si la valeur n'est pas
   *                                             autorisé à cette position
   *                                             dans la grille aux vues des
   *                                             autres valeurs de la grille
   * @throws ValeurInterditeException            si la valeur n'est pas
   *                                             autorisée dans cette grille
   *                                             pouvant être mis dans la
   *                                             grille
   * @throws HorsBornesException                 si x est en dehors de la
   *                                             grille ou si y est en dehors
   *                                             de la grille
   * @throws ValeurInitialeModificationException si une valeur initiale de la
   *                                             grille est en position x, y
   */
  public void setValeur(int x, int y, ValeurDeCase value)
      throws ValeurImpossibleException, ValeurInterditeException,
      HorsBornesException, ValeurInitialeModificationException {

    checkBounds(x, y);

    if (isValeurInitiale(x, y)) {
      throw new ValeurInitialeModificationException(
          "Impossible de modifier une valeur initiale");
    }

    // Vérification de la valeur autorisée DOIT venir avant isPossible
    if (!(valeursAutorisees.contains(value))) {
      throw new ValeurInterditeException("valeur interdite : " + value);
    }

    if (!isPossible(x, y, value)) {
      throw new ValeurImpossibleException(
          "Valeur impossible à placer: " + value);
    }

    grille[x][y] = value;
  }

  @Override
  /**
   * Renvoie une valeur de la grille.
   *
   * @param x position x dans la grille
   * @param y position y dans la grille
   * 
   * @return Valeur de la grille de la case x, y, null s'il n'y a pas
   *         d'élément à cette position
   * 
   * @throws HorsBornesException si x est en dehors de la grille ou si y est
   *                             en dehors de la grille
   */
  public ValeurDeCase getValeur(int x, int y) throws HorsBornesException {
    checkBounds(x, y);
    return grille[x][y];
  }

  @Override
  /**
   * Renvoie une valeur de la grille.
   *
   * @param x position x dans la grille
   * @param y position y dans la grille
   * 
   * @return Valeur de la grille de la case x, y, null s'il n'y a pas
   *         d'élément à cette position
   * 
   * @throws HorsBornesException si x est en dehors de la grille ou si y est
   *                             en dehors de la grille
   */
  public boolean isComplete() {
    boolean result = true;
    for (ValeurDeCase[] ligne : grille) {
      for (ValeurDeCase valeur : ligne) {
        if (valeur == null)
          result = false;
      }
    }

    return result;
  }

  @Override
  /**
   * Teste si une valeur peut être placée dans la grille.
   *
   * @param x     position x dans la grille
   * @param y     position y dans la grille
   * @param value valeur à mettre dans la case
   * 
   * @return true si value peut être placé dans la grille en position x, y en
   *         respectant les règles du sudoku et sans modifier une valeur
   *         initiale.
   * 
   * @throws HorsBornesException      si x ou y sont hors bornes
   * @throws ValeurInterditeException si value n'est pas un caractère pouvant
   *                                  être mis dans la grille
   */
  public boolean isPossible(int x, int y, ValeurDeCase v)
      throws HorsBornesException, ValeurInterditeException {
    checkBounds(x, y);

    if (v == null)
      return true;

    if (valeursAutorisees == null || !valeursAutorisees.contains(v)) {
      throw new ValeurInterditeException("Valeur interdite : " + v);
    }

    // Vérifier ligne
    for (int col = 0; col < this.getDimension(); col++) {
      if (col == y)
        continue;
      if (grille[x][col] != null && grille[x][col].equals(v))
        return false;
    }

    // Vérifier colonne
    for (int lig = 0; lig < this.getDimension(); lig++) {
      if (lig == x)
        continue;
      if (grille[lig][y] != null && grille[lig][y].equals(v))
        return false;
    }

    // Vérifier sous-grille
    int tailleSouGrille = (int) Math.sqrt(getDimension());
    int startX = (x / tailleSouGrille) * tailleSouGrille;
    int startY = (y / tailleSouGrille) * tailleSouGrille;

    for (int i = 0; i < tailleSouGrille; i++) {
      for (int j = 0; j < tailleSouGrille; j++) {
        int cx = startX + i;
        int cy = startY + j;
        if (cx == x && cy == y)
          continue;
        if (grille[cx][cy] != null && grille[cx][cy].equals(v))
          return false;
      }
    }

    return true;
  }

  @Override
  /**
   * Teste si une valeur est une valeur initiale de la grille. Une valeur
   * initiale est une valeur qui est placée dans la grille au départ et qui
   * ne peut pas être modifiée.
   *
   * @param x position x dans la grille
   * @param y position y dans la grille
   * 
   * @return true si la case x, y contient une valeur initiale de la grille.
   * 
   * @throws HorsBornesException si x ou y sont hors bornes
   */
  public boolean isValeurInitiale(int x, int y) throws HorsBornesException {
    boolean result = false;
    checkBounds(x, y);

    if (ValeurDeCaseInitiales[x][y] != null) {
      // Vérifie si la valeur courante de la grille est bien la valeur
      // initiale
      if (grille[x][y] != null
          && grille[x][y].equals(ValeurDeCaseInitiales[x][y])) {
        result = true;
      }
    }

    return result;

  }
}
