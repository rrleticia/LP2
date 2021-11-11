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

class DocumentoDTOTest {

    DocumentoTexto documentoTexto;
    DocumentoJava documentoJava;
    DocumentoHtml documentoHtml;
    DocumentoDTO documentoDTOTexto;
    DocumentoDTO documentoDTOJava;
    DocumentoDTO documentoDTOHtml;

    @BeforeEach
    void preparaDocumentoDTO(){
        this.documentoTexto = new DocumentoTexto("ID_01",
                """
                        motivos pelos quais eu quero muito o albedo!\r
                        acho ele muito bonito, o character design é impecável.\r
                        eu amo o estilo da gameplay dele, ele é o único personagem de geo que eu amo.\r
                        o va fez um trabalho incrível, sou apaixonada pela fala da ult dele.\r
                        por fim, ainda tem o fato dele ser build de defesa. eu sou stan dos flops!""");
        this.documentoDTOTexto = new DocumentoDTO(documentoTexto);

        var exemplo = new DocumentoExemplos();
        this.documentoJava = new DocumentoJava("ID_02", exemplo.sampleJava());
        this.documentoDTOJava = new DocumentoDTO(documentoJava);

        this.documentoHtml= new DocumentoHtml("ID_03", exemplo.sampleHTML());
        this.documentoDTOHtml = new DocumentoDTO(this.documentoHtml);
    }

    @Test
    void getId() {
        assertEquals("ID_01", this.documentoDTOTexto.getId());
        assertEquals("ID_02", this.documentoDTOJava.getId());
        assertEquals("ID_03", this.documentoDTOHtml.getId());
    }

    @Test
    void getTexto() {
        assertArrayEquals(this.documentoDTOTexto.getTexto(), this.documentoTexto.getTexto());
        assertArrayEquals(this.documentoDTOJava.getTexto(), this.documentoJava.getTexto());
        assertArrayEquals(this.documentoDTOHtml.getTexto(), this.documentoHtml.getTexto());
    }

    @Test
    void getMetadados() {
        Map<String, String> mapaTexto = this.documentoDTOTexto.getMetadados();
        String [] arrayDados = new String[]{"LINHAS","TAMANHO", "METADATADATE", "TIPO"};
        Set<String> setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaTexto.keySet().size(), this.documentoTexto.getMetadados().keySet().size());
        assertEquals(mapaTexto.keySet(), setDados);
        assertEquals(mapaTexto.get("LINHAS"), this.documentoTexto.getMetadados().get("LINHAS"));
        assertEquals(mapaTexto.get("TAMANHO"), this.documentoTexto.getMetadados().get("TAMANHO"));
        assertEquals(mapaTexto.get("TIPO"), this.documentoTexto.getMetadados().get("TIPO"));

        Map<String, String> mapaJava = this.documentoDTOJava.getMetadados();
        arrayDados = new String[]{"IMPORTS", "LINHAS", "AUTHOR", "TAMANHO", "METADATADATE", "TIPO"};
        setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaJava.keySet().size(), this.documentoJava.getMetadados().keySet().size());
        assertEquals(mapaJava.keySet(), setDados);
        assertEquals(mapaJava.get("AUTHOR"), this.documentoJava.getMetadados().get("AUTHOR"));
        assertEquals(mapaJava.get("IMPORTS"), this.documentoJava.getMetadados().get("IMPORTS"));
        assertEquals(mapaJava.get("LINHAS"), this.documentoJava.getMetadados().get("LINHAS"));
        assertEquals(mapaJava.get("TAMANHO"), this.documentoJava.getMetadados().get("TAMANHO"));
        assertEquals(mapaJava.get("TIPO"), this.documentoJava.getMetadados().get("TIPO"));

        Map<String, String> mapaHtml = this.documentoDTOHtml.getMetadados();
        arrayDados = new String[]{"BRUTE_TAGS", "LINHAS", "HEAD", "TAMANHO", "METADATADATE", "TIPO"};
        setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaHtml.keySet().size(), this.documentoHtml.getMetadados().keySet().size());
        assertEquals(mapaHtml.keySet(), setDados);
        assertEquals(mapaHtml.get("BRUTE_TAGS"), this.documentoHtml.getMetadados().get("BRUTE_TAGS"));
        assertEquals(mapaHtml.get("HEAD"), this.documentoHtml.getMetadados().get("HEAD"));
        assertEquals(mapaHtml.get("LINHAS"), this.documentoHtml.getMetadados().get("LINHAS"));
        assertEquals(mapaHtml.get("TAMANHO"), this.documentoHtml.getMetadados().get("TAMANHO"));
        assertEquals(mapaHtml.get("TIPO"), this.documentoHtml.getMetadados().get("TIPO"));
    }

    @Test
    void metricaTextoUtil() {
        assertEquals(this.documentoDTOTexto.metricaTextoUtil(), this.documentoTexto.metricaTextoUtil());
        assertEquals(this.documentoDTOJava.metricaTextoUtil(), this.documentoJava.metricaTextoUtil());
        assertEquals(this.documentoDTOHtml.metricaTextoUtil(), this.documentoHtml.metricaTextoUtil());
    }

    @Test
    void testToString() {
        String strDocumentoTexto = this.documentoTexto.toString();
        assertEquals(this.documentoDTOTexto.toString(), strDocumentoTexto);
        String strDocumentoJava = this.documentoJava.toString();
        assertEquals(this.documentoDTOJava.toString(), strDocumentoJava);
        String strDocumentoHtml = this.documentoHtml.toString();
        assertEquals(this.documentoDTOHtml.toString(), strDocumentoHtml);

    }

    @Test
    void testHashCode() {
        DocumentoDTO documentoIgual = new DocumentoDTO(this.documentoTexto);
        DocumentoDTO documentoDiferente = new DocumentoDTO(new DocumentoTexto("ID_DIFERNTE", "TEXTO"));
        assertEquals(this.documentoDTOTexto.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoDTOTexto.hashCode(), documentoDiferente.hashCode());

        documentoIgual = new DocumentoDTO(this.documentoJava);
        documentoDiferente = new DocumentoDTO(new DocumentoJava("ID_DIFERNTE", "TEXTO JAVA"));
        assertEquals(this.documentoDTOJava.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoDTOJava.hashCode(), documentoDiferente.hashCode());

        documentoIgual = new DocumentoDTO(this.documentoHtml);
        documentoDiferente = new DocumentoDTO(new DocumentoHtml("ID_DIFERNTE", "TEXTO HTML"));
        assertEquals(this.documentoDTOHtml.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoDTOHtml.hashCode(), documentoDiferente.hashCode());
    }

    @Test
    void testEquals() {
        DocumentoDTO documentoIgual = new DocumentoDTO(this.documentoTexto);
        DocumentoDTO documentoDiferente = new DocumentoDTO(new DocumentoTexto("ID_DIFERNTE", "TEXTO"));
        assertEquals(this.documentoDTOTexto, documentoIgual);
        assertNotEquals(this.documentoDTOTexto, documentoDiferente);

        documentoIgual = new DocumentoDTO(this.documentoJava);
        documentoDiferente = new DocumentoDTO(new DocumentoJava("ID_DIFERNTE", "TEXTO JAVA"));
        assertEquals(this.documentoDTOJava, documentoIgual);
        assertNotEquals(this.documentoDTOJava, documentoDiferente);

        documentoIgual = new DocumentoDTO(this.documentoHtml);
        documentoDiferente = new DocumentoDTO(new DocumentoHtml("ID_DIFERNTE", "TEXTO HTML"));
        assertEquals(this.documentoDTOHtml, documentoIgual);
        assertNotEquals(this.documentoDTOHtml, documentoDiferente);
    }
}