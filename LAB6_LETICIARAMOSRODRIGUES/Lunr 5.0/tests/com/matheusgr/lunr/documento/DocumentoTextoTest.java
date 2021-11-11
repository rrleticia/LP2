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

class DocumentoTextoTest {

    private DocumentoTexto documentoTexto;
    private DocumentoTexto documentoTextoAuxiliar;

    @BeforeEach
    void preparaDocumentoTexto(){
        this.documentoTexto = new DocumentoTexto("TEXTO1_ID",
                """
                        motivos pelos quais eu quero muito o albedo!\r
                        acho ele muito bonito, o character design é impecável.\r
                        eu amo o estilo da gameplay dele, ele é o único personagem de geo que eu amo.\r
                        o va fez um trabalho incrível, sou apaixonada pela fala da ult dele.\r
                        por fim, ainda tem o fato dele ser build de defesa. eu sou stan dos flops!
                        """);
        this.documentoTextoAuxiliar = new DocumentoTexto("TEXTO2_ID",
                """
                        sou feliz como kokomi stan: um essay.\r
                        sou sim! gosto muito dos talentos dela.\r
                        a água viva cura muito bem, além de ser boa pro meu chongyun.\r
                        a ult dela não está perfeita, não buildei ela por completo, sem comentários.\r
                        amo as animações dela também! o character design, como sempre, impecável.\r
                        com certeza não me arrependo de ter pegado a mais linda!
                        """);
    }

    @Test
    void testGetId() {
        assertEquals("TEXTO1_ID", this.documentoTexto.getId());
        assertEquals("TEXTO2_ID", this.documentoTextoAuxiliar.getId());
    }

    @Test
    void testGetIdInvalido() {
        DocumentoTexto documentoTextoInvalido = new DocumentoTexto(null,"TEXTO EM HTML");
        try{
            documentoTextoInvalido.getId();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testGetOriginal() {
        assertEquals("""
                        motivos pelos quais eu quero muito o albedo!\r
                        acho ele muito bonito, o character design é impecável.\r
                        eu amo o estilo da gameplay dele, ele é o único personagem de geo que eu amo.\r
                        o va fez um trabalho incrível, sou apaixonada pela fala da ult dele.\r
                        por fim, ainda tem o fato dele ser build de defesa. eu sou stan dos flops!
                        """, this.documentoTexto.getOriginal());
        assertEquals("""
                        sou feliz como kokomi stan: um essay.\r
                        sou sim! gosto muito dos talentos dela.\r
                        a água viva cura muito bem, além de ser boa pro meu chongyun.\r
                        a ult dela não está perfeita, não buildei ela por completo, sem comentários.\r
                        amo as animações dela também! o character design, como sempre, impecável.\r
                        com certeza não me arrependo de ter pegado a mais linda!
                        """, this.documentoTextoAuxiliar.getOriginal());
    }

    @Test
    void testGetLimpo() {
        assertEquals("""
                motivos pelos quais eu quero muito o albedo\s
                acho ele muito bonito  o character design é impecável\s
                eu amo o estilo da gameplay dele  ele é o único personagem de geo que eu amo\s
                o va fez um trabalho incrível  sou apaixonada pela fala da ult dele\s
                por fim  ainda tem o fato dele ser build de defesa  eu sou stan dos flops""", this.documentoTexto.getLimpo().replace("\r", ""));
        assertEquals("""
                sou feliz como kokomi stan  um essay\s
                sou sim  gosto muito dos talentos dela\s
                a água viva cura muito bem  além de ser boa pro meu chongyun\s
                a ult dela não está perfeita  não buildei ela por completo  sem comentários\s
                amo as animações dela também  o character design  como sempre  impecável\s
                com certeza não me arrependo de ter pegado a mais linda""", this.documentoTextoAuxiliar.getLimpo().replace("\r", ""));
    }

    @Test
    void testSetLimpo() {
        this.documentoTexto.setLimpo("LIMPO NUMERO 1");
        this.documentoTextoAuxiliar.setLimpo("LIMPO NUMERO 2");
        assertEquals("LIMPO NUMERO 1", this.documentoTexto.getLimpo());
        assertEquals("LIMPO NUMERO 2", this.documentoTextoAuxiliar.getLimpo());
    }

    @Test
    void testGetMetadados() {
        Map<String, String> mapaTextoId_01 = this.documentoTexto.getMetadados();
        String [] arrayDados = new String[]{"LINHAS","TAMANHO", "METADATADATE", "TIPO"};
        Set<String> setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaTextoId_01.keySet().size(), 4);
        assertEquals(mapaTextoId_01.keySet(), setDados);
        assertEquals(mapaTextoId_01.get("LINHAS"), "5");
        assertEquals(mapaTextoId_01.get("TAMANHO"), "324");
        assertEquals(mapaTextoId_01.get("TIPO"), "txt");

        Map<String, String> mapaTextoId_02 = this.documentoTextoAuxiliar.getMetadados();
        assertEquals(mapaTextoId_02.keySet().size(), 4);
        assertEquals(mapaTextoId_02.keySet(), setDados);
        assertEquals(mapaTextoId_02.get("LINHAS"), "6");
        assertEquals(mapaTextoId_02.get("TAMANHO"), "351");
        assertEquals(mapaTextoId_02.get("TIPO"), "txt");

    }

    @Test
    void testGetTexto() {
        Documento primieroDoc = new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi\r\n");
        Documento segundoDoc = new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi\r\n");

        String[] texto01 = primieroDoc.getTexto();
        String[] texto02 = segundoDoc.getTexto();

        String[] esperado01 = new String[]{"kaeya", "kokomi", "kokomi", "kokomi", "qiqi", "raiden", "raiden", "tartaglia", "venti"};
        String[] esperado02 = new String[]{"albedo", "baizou", "kaeya", "kokomi", "kokomi", "kokomi", "qiqi", "raiden", "raiden", "tartaglia", "venti"};

        assertArrayEquals(texto01, esperado01);
        assertArrayEquals(texto02, esperado02);
    }

    @Test
    void testMetricaTextoUtil() {
        assertEquals(this.documentoTexto.metricaTextoUtil(), 0.77, 0.01);
        assertEquals(this.documentoTextoAuxiliar.metricaTextoUtil(), 0.78, 0.01);
    }

    @Test
    void testToString(){
        String texto01 = this.documentoTexto.toString();
        String texto02 = this.documentoTextoAuxiliar.toString();
        assertEquals(texto01.replace("\r", ""), """
                ===TEXTO1_ID
                motivos pelos quais eu quero muito o albedo\s
                acho ele muito bonito  o character design é impecável\s
                eu amo o estilo da gameplay dele  ele é o único personagem de geo que eu amo\s
                o va fez um trabalho incrível  sou apaixonada pela fala da ult dele\s
                por fim  ainda tem o fato dele ser build de defesa  eu sou stan dos flops""");
        assertEquals(texto02.replace("\r", ""), """
                ===TEXTO2_ID
                sou feliz como kokomi stan  um essay\s
                sou sim  gosto muito dos talentos dela\s
                a água viva cura muito bem  além de ser boa pro meu chongyun\s
                a ult dela não está perfeita  não buildei ela por completo  sem comentários\s
                amo as animações dela também  o character design  como sempre  impecável\s
                com certeza não me arrependo de ter pegado a mais linda""");
    }

    @Test
    void testHashCode() {
        DocumentoTexto documentoIgual = new DocumentoTexto("TEXTO1_ID", "TEXTO TEXTO TEXO");
        DocumentoJava documentoDiferente = new DocumentoJava("TEXTO2_ID", "TEXTO EM JAVA");
        assertEquals(this.documentoTexto.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoTexto.hashCode(), documentoDiferente.hashCode());
        assertNotEquals(this.documentoTexto.hashCode(), this.documentoTextoAuxiliar.hashCode());
    }

    @Test
    void testEquals() {
        DocumentoTexto documentoIgual = new DocumentoTexto("TEXTO1_ID", "TEXTO TEXTO TEXTO");
        DocumentoJava documentoDiferente = new DocumentoJava("TEXTO1_ID", "TEXTO TEXTO TEXTO");
        assertEquals(this.documentoTexto, documentoIgual);
        assertNotEquals(this.documentoTexto, documentoDiferente);
        assertNotEquals(this.documentoTexto, this.documentoTextoAuxiliar);
    }


}