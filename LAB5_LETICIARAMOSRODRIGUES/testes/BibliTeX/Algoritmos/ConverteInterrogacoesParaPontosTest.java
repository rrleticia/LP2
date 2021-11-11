package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverteInterrogacoesParaPontosTest {

    private AlgoritmoTransformacao interrogaParaPontos;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.interrogaParaPontos = new ConverteInterrogacoesParaPontos();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("InterrogaPraPontos", this.interrogaParaPontos.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "albedo? lindo? sim!";
        assertEquals("albedo. lindo. sim!", this.interrogaParaPontos.transformaTexto(primeiroTexto));
        String segundoTexto = "childe????? como assim?????";
        assertEquals("childe..... como assim.....", this.interrogaParaPontos.transformaTexto(segundoTexto));

    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new ConverteInterrogacoesParaPontos();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar, this.interrogaParaPontos);
        assertNotEquals(transformacaoDiferente, this.interrogaParaPontos);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new ConverteInterrogacoesParaPontos();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar.hashCode(), this.interrogaParaPontos.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.interrogaParaPontos.hashCode());
    }

}