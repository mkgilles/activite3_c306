package fr.miageamiens.isi05.sudoku.modele;


import fr.miageamiens.isi05.sudoku.modele.exceptions.HorsBornesException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurImpossibleException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInitialeModificationException;
import fr.miageamiens.isi05.sudoku.modele.exceptions.ValeurInterditeException;

import java.util.Collection;

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
public interface Grille {

    /**
     * Renvoie les ElementDeGrille pouvant exister dans la grille.
     *
     * @return ensemble des éléments de grille pouvant être mis dans la grille
     */
    Collection<ValeurDeCase> getValeursAutorisees();

    /**
     * Renvoie la dimension de la grille. Exemple: 9 pour une grille de 9x9.
     *
     * @return largeur ou hauteur de la grille
     */
    int getDimension();

    /**
     * Renvoie la dimension d'une sous-grille. Exemple: 3 pour une grille de
     * 3x3.
     *
     * @return largeur ou hauteur d'une sous-grille
     */
    int getSousDimension();

    /**
     * Affecte une valeur dans une case de la grille, ou null pour 'vider' la
     * case.
     *
     * @param x     position x dans la grille
     * @param y     position y dans la grille
     * @param value élément de grille à mettre dans la case, null pour vider la
     *              case
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
    void setValeur(int x, int y, ValeurDeCase value)
            throws ValeurImpossibleException, ValeurInterditeException,
                   HorsBornesException, ValeurInitialeModificationException;

    /**
     * Renvoie une valeur de la grille.
     *
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @return Valeur de la grille de la case x, y, null s'il n'y a pas
     * d'élément à cette position
     * @throws HorsBornesException si x est en dehors de la grille ou si y est
     *                             en dehors de la grille
     */
    ValeurDeCase getValeur(int x, int y) throws HorsBornesException;

    /**
     * Teste si une grille est remplie.
     *
     * @return true si la grille est complete
     */
    boolean isComplete();

    /**
     * Teste si une valeur peut être placée dans la grille.
     *
     * @param x     position x dans la grille
     * @param y     position y dans la grille
     * @param value valeur à mettre dans la case
     * @return true si value peut être placé dans la grille en position x, y en
     * respectant les règles du sudoku et sans modifier une valeur initiale.
     * @throws HorsBornesException      si x ou y sont hors bornes
     * @throws ValeurInterditeException si value n'est pas un caractère pouvant
     *                                  être mis dans la grille
     */
    boolean isPossible(int x, int y, ValeurDeCase value)
            throws HorsBornesException, ValeurInterditeException;


    /**
     * Teste si une valeur est une valeur initiale de la grille. Une valeur
     * initiale est une valeur qui est placée dans la grille au départ et qui ne
     * peut pas être modifiée.
     *
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @return true si la case x, y contient une valeur initiale de la grille.
     * @throws HorsBornesException si x ou y sont hors bornes
     */
    boolean isValeurInitiale(int x, int y) throws HorsBornesException;


}
