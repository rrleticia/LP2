package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;
import com.matheusgr.lunr.DocumentoExemplos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoJavaTest {

    private DocumentoJava documentoJava;
    private DocumentoJava documentoJavaAuxiliar;
    private DocumentoJava documentoJavaExemplo;

    @BeforeEach
    void preparaDocumentoJava(){
        this.documentoJava = new DocumentoJava("JAVA_04", "TEXTO EM JAVA!\nNUMERO QUATRO!\n");
        this.documentoJavaAuxiliar = new DocumentoJava("JAVA_05", "TEXTO EM JAVA!\nNUMERO CINCO!\n");
        var exemplo = new DocumentoExemplos();
        this.documentoJavaExemplo = new DocumentoJava("JAVA_06", exemplo.sampleJava());
    }

    @Test
    void testGetId() {
        assertEquals("JAVA_04", this.documentoJava.getId());
        assertEquals("JAVA_05", this.documentoJavaAuxiliar.getId());
        assertEquals("JAVA_06", this.documentoJavaExemplo.getId());
    }

    @Test
    void testGetIdInvalido() {
        DocumentoJava documentoJavaInvalido = new DocumentoJava(null,"TEXTO EM HTML");
        try{
            documentoJavaInvalido.getId();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testGetOriginal() {
        assertEquals("TEXTO EM JAVA!\nNUMERO QUATRO!\n", this.documentoJava.getOriginal());
        assertEquals("TEXTO EM JAVA!\nNUMERO CINCO!\n", this.documentoJavaAuxiliar.getOriginal());
        var exemplo = new DocumentoExemplos();
        assertEquals(exemplo.sampleJava(), this.documentoJavaExemplo.getOriginal());
    }

    @Test
    void testGetLimpo() {
        assertEquals("TEXTO EM JAVA \nNUMERO QUATRO" , this.documentoJava.getLimpo());
        assertEquals("TEXTO EM JAVA \nNUMERO CINCO", this.documentoJavaAuxiliar.getLimpo());
        assertEquals("""
                package com matheusgr lunr\s

                import java util Optional\s

                import com matheusgr lunr busca BuscaController\s
                import com matheusgr lunr busca HistoricoBusca\s
                import com matheusgr lunr documento Documento\s
                import com matheusgr lunr documento DocumentoController\s
                import com matheusgr lunr documento DocumentoDTO\s
                import com matheusgr lunr documento DocumentoRepository\s
                import com matheusgr lunr documento DocumentoService\s
                import com matheusgr similaridade ResultadoSimilaridade\s
                import com matheusgr similaridade SimilaridadeController\s


                public class LunrFacade \s

                \tprivate DocumentoService ds\s
                \tprivate DocumentoController dc\s
                \tprivate BuscaController bc\s
                \tprivate SimilaridadeController ps\s

                \tpublic LunrFacade   \s
                \t\tthis ds   new DocumentoService new DocumentoRepository   \s
                \t\tthis dc   new DocumentoController ds \s
                \t\tthis bc   new BuscaController ds \s
                \t\tthis ps   new SimilaridadeController ds \s
                \t\s
                \t
                \tpublic void adicionaDocumentoTxt String id  String use  \s
                \t\tthis dc adicionaDocumentoTxt id  use""", this.documentoJavaExemplo.getLimpo().replace("\r", ""));
    }

    @Test
    void testSetLimpo() {
        this.documentoJava.setLimpo("LIMPO NUMERO 1");
        this.documentoJavaAuxiliar.setLimpo("LIMPO NUMERO 2");
        assertEquals("LIMPO NUMERO 1", this.documentoJava.getLimpo());
        assertEquals("LIMPO NUMERO 2", this.documentoJavaAuxiliar.getLimpo());
    }

    @Test
    void testGetMetadados() {
        Map<String, String> mapaJava04 = this.documentoJava.getMetadados();
        String [] arrayDados = new String[]{"IMPORTS", "LINHAS", "AUTHOR", "TAMANHO", "METADATADATE", "TIPO"};
        Set<String> setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaJava04.keySet().size(), 6);
        assertEquals(mapaJava04.keySet(), setDados);
        assertEquals(mapaJava04.get("AUTHOR"), "");
        assertEquals(mapaJava04.get("IMPORTS"), "0");
        assertEquals(mapaJava04.get("LINHAS"), "2");
        assertEquals(mapaJava04.get("TAMANHO"), "28");
        assertEquals(mapaJava04.get("TIPO"), "java");

        Map<String, String> mapaJava05 = this.documentoJavaAuxiliar.getMetadados();
        assertEquals(mapaJava05.keySet().size(), 6);
        assertEquals(mapaJava05.keySet(), setDados);
        assertEquals(mapaJava05.get("AUTHOR"), "");
        assertEquals(mapaJava05.get("IMPORTS"), "0");
        assertEquals(mapaJava05.get("LINHAS"), "2");
        assertEquals(mapaJava05.get("TAMANHO"), "27");
        assertEquals(mapaJava05.get("TIPO"), "java");

        Map<String, String> mapaJava06 = this.documentoJavaExemplo.getMetadados();
        assertEquals(mapaJava06.keySet().size(), 6);
        assertEquals(mapaJava06.keySet(), setDados);
        assertEquals(mapaJava06.get("AUTHOR"), "TRUE");
        assertEquals(mapaJava06.get("IMPORTS"), "10");
        assertEquals(mapaJava06.get("LINHAS"), "43");
        assertEquals(mapaJava06.get("TAMANHO"), "1030");
        assertEquals(mapaJava06.get("TIPO"), "java");
    }

    @Test
    void testGetTexto() {
        String[] texto01 = this.documentoJava.getTexto();
        String[] texto02 = this.documentoJavaAuxiliar.getTexto();

        assertArrayEquals(texto01, new String[]{"EM", "JAVA", "NUMERO", "QUATRO", "TEXTO"});
        assertArrayEquals(texto02, new String[]{"CINCO", "EM", "JAVA", "NUMERO", "TEXTO"});
    }

    @Test
    void testMetricaTextoUtil() {
        assertEquals(this.documentoJava.metricaTextoUtil(), 0.76, 0.01);
        assertEquals(this.documentoJavaAuxiliar.metricaTextoUtil(), 0.75, 0.01);
        assertEquals(this.documentoJavaExemplo.metricaTextoUtil(), 0.5445227418321589, 0.01);
    }

    @Test
    void testToString() {
        String java04 = this.documentoJava.toString();
        String java05 = this.documentoJavaAuxiliar.toString();
        String java06= this.documentoJavaExemplo.toString();
        assertEquals(java04, """
                ===JAVA_04
                TEXTO EM JAVA\s
                NUMERO QUATRO""");
        assertEquals(java05, """
                ===JAVA_05
                TEXTO EM JAVA\s
                NUMERO CINCO""");
        assertEquals(java06.replace("\r", ""), """
                ===JAVA_06
                package com matheusgr lunr\s

                import java util Optional\s

                import com matheusgr lunr busca BuscaController\s
                import com matheusgr lunr busca HistoricoBusca\s
                import com matheusgr lunr documento Documento\s
                import com matheusgr lunr documento DocumentoController\s
                import com matheusgr lunr documento DocumentoDTO\s
                import com matheusgr lunr documento DocumentoRepository\s
                import com matheusgr lunr documento DocumentoService\s
                import com matheusgr similaridade ResultadoSimilaridade\s
                import com matheusgr similaridade SimilaridadeController\s


                public class LunrFacade \s

                \tprivate DocumentoService ds\s
                \tprivate DocumentoController dc\s
                \tprivate BuscaController bc\s
                \tprivate SimilaridadeController ps\s

                \tpublic LunrFacade   \s
                \t\tthis ds   new DocumentoService new DocumentoRepository   \s
                \t\tthis dc   new DocumentoController ds \s
                \t\tthis bc   new BuscaController ds \s
                \t\tthis ps   new SimilaridadeController ds \s
                \t\s
                \t
                \tpublic void adicionaDocumentoTxt String id  String use  \s
                \t\tthis dc adicionaDocumentoTxt id  use""");
    }

    @Test
    void testHashCode() {
        DocumentoJava documentoIgual = new DocumentoJava("JAVA_04", "TEXTO EM JAVA");
        DocumentoHtml documentoDiferente = new DocumentoHtml("HTML_01", "TEXTO EM HTML");
        assertEquals(this.documentoJava.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoJava.hashCode(), documentoDiferente.hashCode());
        assertNotEquals(this.documentoJava.hashCode(), this.documentoJavaAuxiliar.hashCode());
    }

    @Test
    void testEquals() {
        DocumentoJava documentoIgual = new DocumentoJava("JAVA_04", "TEXTO EM JAVA");
        DocumentoHtml documentoDiferente = new DocumentoHtml("HTML_01", "TEXTO EM HTML");
        assertEquals(this.documentoJava, documentoIgual);
        assertNotEquals(this.documentoJava, documentoDiferente);
        assertNotEquals(this.documentoJava, this.documentoJavaAuxiliar);
    }


}