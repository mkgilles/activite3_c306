package fr.miageamiens.isi05.sudoku.parser;

import fr.miageamiens.isi05.sudoku.modele.ValeurDeCase;

/**
 * Factory permettant de créer des valeurs de case à partir d'un objet.
 *
 * @author Sébastien Choplin
 */
public interface ValeurDeCaseFactory {

    /**
     * Crée une valeur de case à partir d'un objet.
     * @param value objet
     * @return valeur de case
     */
    ValeurDeCase create(Object value);

}
