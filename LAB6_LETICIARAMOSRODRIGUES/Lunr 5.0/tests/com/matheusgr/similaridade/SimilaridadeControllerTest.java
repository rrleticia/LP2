package com.matheusgr.similaridade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.*;

class SimilaridadeControllerTest extends BaseSimilaridadeTest {

    private SimilaridadeController similaridadeController;

    @BeforeEach
    void preparaSimilaridadeController(){
        this.similaridadeController = new SimilaridadeController(new SimilaridadeService(this.documentoService));
    }

    @Test
    void testSimilaridade(){
        double valorSimilaridade = this.similaridadeController.similaridade(TEXTO6_ID, TEXTO7_ID);
        double valorEsperado = 1.0 * 6 / 8;
        assertEquals(valorSimilaridade, valorEsperado);
    }

    @Test
    void testSimilaridadeIgual(){
        double valorSimilaridade = this.similaridadeController.similaridade(TEXTO5_ID, TEXTO5_ID);
        assertEquals(valorSimilaridade, 1);
    }

    @Test
    void testSimilaridadeNulo(){
        double valorSimilaridade = this.similaridadeController.similaridade(TEXTO1_ID, TEXTO7_ID);
        assertEquals(valorSimilaridade, 0);
    }

    @Test
    void testSimilaridadeDOCInvalido() {
        try{
            this.similaridadeController.similaridade(TEXTO1_ID, null);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }

        try{
            this.similaridadeController.similaridade(null, TEXTO1_ID);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }

}