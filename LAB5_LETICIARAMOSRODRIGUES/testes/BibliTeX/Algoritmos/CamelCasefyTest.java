package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CamelCasefyTest {

    private AlgoritmoTransformacao camelCasely;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.camelCasely = new CamelCasefy();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("CaMeLcAsEfY", this.camelCasely.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "kokomi sangonomiya";
        assertEquals("KoKoMi sAnGoNoMiYa", this.camelCasely.transformaTexto(primeiroTexto));
        String segundoTexto = "xingqiu";
        assertEquals("XiNgQiU", this.camelCasely.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new CamelCasefy();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar, this.camelCasely);
        assertNotEquals(transformacaoDiferente, this.camelCasely);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new CamelCasefy();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar.hashCode(), this.camelCasely.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.camelCasely.hashCode());
    }
}