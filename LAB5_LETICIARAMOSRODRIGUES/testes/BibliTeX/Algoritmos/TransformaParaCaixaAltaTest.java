package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TransformaParaCaixaAltaTest {

    private AlgoritmoTransformacao transformaParaCaixaAlta;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.transformaParaCaixaAlta = new TransformaParaCaixaAlta();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("upperCase", this.transformaParaCaixaAlta.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "albedo onii-chan";
        assertEquals("ALBEDO ONII-CHAN", this.transformaParaCaixaAlta.transformaTexto(primeiroTexto));
        String segundoTexto = "tartaglia childe";
        assertEquals("TARTAGLIA CHILDE", this.transformaParaCaixaAlta.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaParaCaixaAlta();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar, this.transformaParaCaixaAlta);
        assertNotEquals(transformacaoDiferente, this.transformaParaCaixaAlta);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaParaCaixaAlta();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar.hashCode(), this.transformaParaCaixaAlta.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.transformaParaCaixaAlta.hashCode());
    }
}