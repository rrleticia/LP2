package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformaVogaisEmHashtagTest {

    private AlgoritmoTransformacao transformaVogaisEmHashTags;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.transformaVogaisEmHashTags = new TransformaVogaisEmHashtag();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("TransformaVogaisEm#", this.transformaVogaisEmHashTags.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "so what if i hate kaeya?";
        assertEquals("s# wh#t #f # h#t# k##y#?", this.transformaVogaisEmHashTags.transformaTexto(primeiroTexto));
        String segundoTexto = "qiqi is the cutest love of my life";
        assertEquals("q#q# #s th# c#t#st l#v# #f my l#f#", this.transformaVogaisEmHashTags.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaVogaisEmHashtag();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar, this.transformaVogaisEmHashTags);
        assertNotEquals(transformacaoDiferente, this.transformaVogaisEmHashTags);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaVogaisEmHashtag();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar.hashCode(), this.transformaVogaisEmHashTags.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.transformaVogaisEmHashTags.hashCode());
    }
}