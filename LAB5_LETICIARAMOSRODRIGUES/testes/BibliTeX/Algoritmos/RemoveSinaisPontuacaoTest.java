package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveSinaisPontuacaoTest {

    private AlgoritmoTransformacao removeSinaisPontuacao;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.removeSinaisPontuacao = new RemoveSinaisPontuacao();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("clean", this.removeSinaisPontuacao.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "so i said, i love venti! then you know what? they laughed!";
        assertEquals("so i said i love venti then you know what they laughed", this.removeSinaisPontuacao.transformaTexto(primeiroTexto));
        String segundoTexto = "ahhhh!!! lá vem o kaeya!!!";
        assertEquals( "ahhhh lá vem o kaeya", this.removeSinaisPontuacao.transformaTexto(segundoTexto));


    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveSinaisPontuacao();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar, this.removeSinaisPontuacao);
        assertNotEquals(transformacaoDiferente, this.removeSinaisPontuacao);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveSinaisPontuacao();
        AlgoritmoTransformacao transformacaoDiferente = new CamelCasefy();
        assertEquals(transformacaoAuxiliar.hashCode(), this.removeSinaisPontuacao.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.removeSinaisPontuacao.hashCode());
    }
}