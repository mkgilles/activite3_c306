package fr.miageamiens.isi05.sudoku.modele.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValeurImpossibleExceptionTest {

    /**
     * Test du constructeur ValeurImpossibleException(String).
     */
    @Test
    void testValeurImpossibleExceptionString() {
        String message = "Valeur non autorisée";
        ValeurImpossibleException ex = new ValeurImpossibleException(message);

        assertNotNull(ex);
        assertEquals(message, ex.getMessage(), "Le message doit être correctement propagé");
    }

    /**
     * Test du constructeur ValeurImpossibleException(Throwable).
     */
    @Test
    void testValeurImpossibleExceptionThrowable() {
        String msg = "Valeur non autorisée";
        ValeurImpossibleException ex = new ValeurImpossibleException(msg);

        assertNotNull(ex);
        assertEquals(msg, ex.getMessage(), "La cause doit être correctement propagée");
    }
}