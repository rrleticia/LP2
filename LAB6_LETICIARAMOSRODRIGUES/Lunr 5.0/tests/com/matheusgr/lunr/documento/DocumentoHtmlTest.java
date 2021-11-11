package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;
import com.matheusgr.lunr.DocumentoExemplos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoHtmlTest {

    private DocumentoHtml documentoHtml;
    private DocumentoHtml documentoHtmlAuxiliar;
    private DocumentoHtml documentoHtmlExemplo;

    @BeforeEach
    void preparaDocumentoHtml(){
        this.documentoHtml = new DocumentoHtml("HTML_01", "TEXTO EM HTML!\nNUMERO UM!\n");
        this.documentoHtmlAuxiliar = new DocumentoHtml("HTML_02", "TEXTO EM HTML!\nNUMERO DOIS!\n");
        var exemplo = new DocumentoExemplos();
        this.documentoHtmlExemplo = new DocumentoHtml("HTML_03", exemplo.sampleHTML());
    }

    @Test
    void testGetId() {
        assertEquals("HTML_01", this.documentoHtml.getId());
        assertEquals("HTML_02", this.documentoHtmlAuxiliar.getId());
        assertEquals("HTML_03", this.documentoHtmlExemplo.getId());
    }

    @Test
    void testGetIdInvalido() {
        DocumentoHtml documentoHtmlInvalido = new DocumentoHtml(null,"TEXTO EM HTML");
        try{
            documentoHtmlInvalido.getId();
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testGetOriginal() {
        assertEquals("TEXTO EM HTML!\nNUMERO UM!\n", this.documentoHtml.getOriginal());
        assertEquals("TEXTO EM HTML!\nNUMERO DOIS!\n", this.documentoHtmlAuxiliar.getOriginal());
        var exemplo = new DocumentoExemplos();
        assertEquals(exemplo.sampleHTML(), this.documentoHtmlExemplo.getOriginal());
    }

    @Test
    void testGetLimpo() {
        assertEquals("TEXTO EM HTML \nNUMERO UM", this.documentoHtml.getLimpo());
        assertEquals("TEXTO EM HTML \nNUMERO DOIS", this.documentoHtmlAuxiliar.getLimpo());
        assertEquals("""
                doctype html\s
                 html\s
                 head\s
                     title Example Domain  title\s

                     meta charset  utf 8   \s
                     meta http equiv  Content type  content  text html  charset utf 8   \s
                     meta name  viewport  content  width device width  initial scale 1   \s
                     style type  text css \s
                    body \s
                        background color   f0f0f2\s
                        margin  0\s
                        padding  0\s
                        font family   apple system  system ui  BlinkMacSystemFont   Segoe UI    Open Sans    Helvetica Neue   Helvetica  Arial  sans serif\s
                       \s
                    \s
                    div \s
                        width  600px\s
                        margin  5em auto\s
                        padding  2em\s
                        background color   fdfdff\s
                        border radius  0 5em\s
                        box shadow  2px 3px 7px 2px rgba 0 0 0 0 02 \s
                    \s
                    a link  a visited \s
                        color   38488f\s
                        text decoration  none\s
                    \s
                     media  max width  700px  \s
                        div \s
                            margin  0 auto\s
                            width  auto\s
                        \s
                    \s
                      style    \s
                  head\s

                 body\s
                 div\s
                     h1 Example Domain  h1\s
                     p This domain is for use in illustrative examples in documents  You may use this
                    domain in literature without prior coordination or asking for permission   p\s
                     p  a href  https   www iana org domains example  More information     a   p\s
                  div\s
                  body\s
                  html""", this.documentoHtmlExemplo.getLimpo().replace("\r", ""));
    }

    @Test
    void testSetLimpo() {
        this.documentoHtml.setLimpo("LIMPO NUMERO 1");
        this.documentoHtmlAuxiliar.setLimpo("LIMPO NUMERO 2");
        assertEquals("LIMPO NUMERO 1", this.documentoHtml.getLimpo());
        assertEquals("LIMPO NUMERO 2", this.documentoHtmlAuxiliar.getLimpo());
    }

    @Test
    void testGetMetadados() {
        Map<String, String> mapaHtml01 = this.documentoHtml.getMetadados();
        String [] arrayDados = new String[]{"BRUTE_TAGS", "LINHAS", "HEAD", "TAMANHO", "METADATADATE", "TIPO"};
        Set<String> setDados = new HashSet<>(Arrays.asList((arrayDados)));
        assertEquals(mapaHtml01.keySet().size(), 6);
        assertEquals(mapaHtml01.keySet(), setDados);
        assertEquals(mapaHtml01.get("BRUTE_TAGS"), "0");
        assertEquals(mapaHtml01.get("HEAD"), "");
        assertEquals(mapaHtml01.get("LINHAS"), "2");
        assertEquals(mapaHtml01.get("TAMANHO"), "24");
        assertEquals(mapaHtml01.get("TIPO"), "html");

        Map<String, String> mapaHtml02 = this.documentoHtmlAuxiliar.getMetadados();
        assertEquals(mapaHtml02.keySet().size(), 6);
        assertEquals(mapaHtml02.keySet(), setDados);
        assertEquals(mapaHtml02.get("BRUTE_TAGS"), "0");
        assertEquals(mapaHtml02.get("HEAD"), "");
        assertEquals(mapaHtml02.get("LINHAS"), "2");
        assertEquals(mapaHtml02.get("TAMANHO"), "26");
        assertEquals(mapaHtml02.get("TIPO"), "html");

        Map<String, String> mapaHtml03 = this.documentoHtmlExemplo.getMetadados();
        assertEquals(mapaHtml03.keySet().size(), 6);
        assertEquals(mapaHtml03.keySet(), setDados);
        assertEquals(mapaHtml03.get("BRUTE_TAGS"), "24");
        assertEquals(mapaHtml03.get("HEAD").replace("\r", ""), """
                <head>
                    <title>Example Domain</title>

                    <meta charset="utf-8" />
                    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <style type="text/css">
                    body {
                        background-color: #f0f0f2;
                        margin: 0;
                        padding: 0;
                        font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
                       \s
                    }
                    div {
                        width: 600px;
                        margin: 5em auto;
                        padding: 2em;
                        background-color: #fdfdff;
                        border-radius: 0.5em;
                        box-shadow: 2px 3px 7px 2px rgba(0,0,0,0.02);
                    }
                    a:link, a:visited {
                        color: #38488f;
                        text-decoration: none;
                    }
                    @media (max-width: 700px) {
                        div {
                            margin: 0 auto;
                            width: auto;
                        }
                    }
                    </style>   \s
                """);
        assertEquals(mapaHtml03.get("LINHAS"), "46");
        assertEquals(mapaHtml03.get("TAMANHO"), "1297");
        assertEquals(mapaHtml03.get("TIPO"), "html");
    }

    @Test
    void testGetTexto() {
        assertArrayEquals(this.documentoHtml.getTexto(), new String[]{"EM", "HTML", "NUMERO","TEXTO", "UM"});
        assertArrayEquals(this.documentoHtmlAuxiliar.getTexto(), new String[]{"DOIS", "EM", "HTML", "NUMERO","TEXTO"});
    }

    @Test
    void testMetricaTextoUtil() {
        assertEquals(this.documentoHtml.metricaTextoUtil(), 0.73, 0.01);
        assertEquals(this.documentoHtmlAuxiliar.metricaTextoUtil(), 0.75, 0.01);
        assertEquals(this.documentoHtmlExemplo.metricaTextoUtil(), 0.59, 0.01);
    }

    @Test
    void testToString() {
        String html01 = this.documentoHtml.toString();
        String html02 = this.documentoHtmlAuxiliar.toString();
        String html03 = this.documentoHtmlExemplo.toString();
        assertEquals(html01, """
                ===HTML_01

                ===TEXTO EM HTML\s
                NUMERO UM""");
        assertEquals(html02, """
                ===HTML_02

                ===TEXTO EM HTML\s
                NUMERO DOIS""");
        assertEquals(html03.replace("\r", ""), """
                ===HTML_03
                <head>
                    <title>Example Domain</title>

                    <meta charset="utf-8" />
                    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                    <style type="text/css">
                    body {
                        background-color: #f0f0f2;
                        margin: 0;
                        padding: 0;
                        font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
                       \s
                    }
                    div {
                        width: 600px;
                        margin: 5em auto;
                        padding: 2em;
                        background-color: #fdfdff;
                        border-radius: 0.5em;
                        box-shadow: 2px 3px 7px 2px rgba(0,0,0,0.02);
                    }
                    a:link, a:visited {
                        color: #38488f;
                        text-decoration: none;
                    }
                    @media (max-width: 700px) {
                        div {
                            margin: 0 auto;
                            width: auto;
                        }
                    }
                    </style>   \s

                ===doctype html\s
                 html\s
                 head\s
                     title Example Domain  title\s

                     meta charset  utf 8   \s
                     meta http equiv  Content type  content  text html  charset utf 8   \s
                     meta name  viewport  content  width device width  initial scale 1   \s
                     style type  text css \s
                    body \s
                        background color   f0f0f2\s
                        margin  0\s
                        padding  0\s
                        font family   apple system  system ui  BlinkMacSystemFont   Segoe UI    Open Sans    Helvetica Neue   Helvetica  Arial  sans serif\s
                       \s
                    \s
                    div \s
                        width  600px\s
                        margin  5em auto\s
                        padding  2em\s
                        background color   fdfdff\s
                        border radius  0 5em\s
                        box shadow  2px 3px 7px 2px rgba 0 0 0 0 02 \s
                    \s
                    a link  a visited \s
                        color   38488f\s
                        text decoration  none\s
                    \s
                     media  max width  700px  \s
                        div \s
                            margin  0 auto\s
                            width  auto\s
                        \s
                    \s
                      style    \s
                  head\s

                 body\s
                 div\s
                     h1 Example Domain  h1\s
                     p This domain is for use in illustrative examples in documents  You may use this
                    domain in literature without prior coordination or asking for permission   p\s
                     p  a href  https   www iana org domains example  More information     a   p\s
                  div\s
                  body\s
                  html""");
    }

    @Test
    void testHashCode() {
        DocumentoHtml documentoIgual = new DocumentoHtml("HTML_01", "TEXTO EM HTML");
        DocumentoJava documentoDiferente = new DocumentoJava("TEXTO1_ID", "TEXTO EM JAVA");
        assertEquals(this.documentoHtml.hashCode(), documentoIgual.hashCode());
        assertNotEquals(this.documentoHtml.hashCode(), documentoDiferente.hashCode());
        assertNotEquals(this.documentoHtml.hashCode(), this.documentoHtmlAuxiliar.hashCode());
    }

    @Test
    void testEquals() {
        DocumentoHtml documentoIgual = new DocumentoHtml("HTML_01", "TEXTO EM HTML");
        DocumentoJava documentoDiferente = new DocumentoJava("TEXTO1_ID", "TEXTO EM JAVA");
        assertEquals(this.documentoHtml, documentoIgual);
        assertNotEquals(this.documentoHtml, documentoDiferente);
        assertNotEquals(this.documentoHtml, this.documentoHtmlAuxiliar);
    }

}