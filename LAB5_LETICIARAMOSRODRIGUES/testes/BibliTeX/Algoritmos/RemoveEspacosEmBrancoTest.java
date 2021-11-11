package BibliTeX.Algoritmos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.AlgorithmMethod;

import static org.junit.jupiter.api.Assertions.*;

class RemoveEspacosEmBrancoTest {
    AlgoritmoTransformacao removeEspacosEmBranco;

    @BeforeEach
    void preparaAlgoritmoTransformacao(){
        this.removeEspacosEmBranco = new RemoveEspacosEmBranco();
    }

    @Test
    void getNomeTransformacao() {
        assertEquals("cleanSpaces", this.removeEspacosEmBranco.getNomeTransformacao());
    }

    @Test
    void transformaTexto() {
        String primeiroTexto = "anemo e hydro > o resto";
        assertEquals("anemoehydro>oresto", this.removeEspacosEmBranco.transformaTexto(primeiroTexto ));
        String segundoTexto = "chongyun faz hmmm e eu morro de amores";
        assertEquals("chongyunfazhmmmeeumorrodeamores", this.removeEspacosEmBranco.transformaTexto(segundoTexto));
    }

    @Test
    void testEquals() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveEspacosEmBranco();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar, this.removeEspacosEmBranco);
        assertNotEquals(transformacaoDiferente, this.removeEspacosEmBranco);
    }

    @Test
    void testHashCode() {
        AlgoritmoTransformacao transformacaoAuxiliar = new RemoveEspacosEmBranco();
        AlgoritmoTransformacao transformacaoDiferente = new RemoveSinaisPontuacao();
        assertEquals(transformacaoAuxiliar.hashCode(), this.removeEspacosEmBranco.hashCode());
        assertNotEquals(transformacaoDiferente.hashCode(), this.removeEspacosEmBranco.hashCode());
    }
}