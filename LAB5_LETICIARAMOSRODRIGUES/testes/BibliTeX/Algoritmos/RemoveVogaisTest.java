package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveVogaisTest {

    private AlgoritmoTransformacao removeVogais;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.removeVogais = new RemoveVogais();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("semVogais", this.removeVogais.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "kamisato ayato";
        assertEquals("kmst yt", this.removeVogais.transformaTexto(primeiroTexto));
        String segundoTexto = "i love you tohma";
        assertEquals(" lv y thm", this.removeVogais.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveVogais();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar, this.removeVogais);
        assertNotEquals(transformacaoDiferente, this.removeVogais);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveVogais();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar.hashCode(), this.removeVogais.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.removeVogais.hashCode());
    }
}