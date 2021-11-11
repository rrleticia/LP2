package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformaParaKatakanaTest {
    private AlgoritmoTransformacao transformaParaKtakana;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.transformaParaKtakana = new TransformaParaKatakana();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("foneticaJapao", this.transformaParaKtakana.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "oi! bom dia?";
        assertEquals("オイ! ベオエメ デイア?", this.transformaParaKtakana.transformaTexto(primeiroTexto));
        String segundoTexto = "tchau boa noite";
        assertEquals("テセアハアウ ベオア エネオイテエ", this.transformaParaKtakana.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaParaKatakana();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar, this.transformaParaKtakana);
        assertNotEquals(transformacaoDiferente, this.transformaParaKtakana);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new TransformaParaKatakana();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar.hashCode(), this.transformaParaKtakana.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.transformaParaKtakana.hashCode());
    }
}