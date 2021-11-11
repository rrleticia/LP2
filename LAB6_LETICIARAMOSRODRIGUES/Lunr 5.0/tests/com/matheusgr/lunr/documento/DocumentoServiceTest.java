package com.matheusgr.lunr.documento;

import com.matheusgr.lunr.DocumentoExemplos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoServiceTest {

    protected DocumentoService documentoService;

    @BeforeEach
    void preparaDocumentoService() {
        this.documentoService = new DocumentoService();
    }

    @Test
    void testAdicionaDocumento() {
        var exemplo = new DocumentoExemplos();
        DocumentoHtml htmlOriginal = new DocumentoHtml("123", exemplo.sampleHTML());
        DocumentoJava javaOriginal = new DocumentoJava("134", exemplo.sampleJava());
        this.documentoService.adicionaDocumento(new DocumentoHtml("123", exemplo.sampleHTML()));
        this.documentoService.adicionaDocumento(new DocumentoJava("134", exemplo.sampleJava()));

        assertEquals(this.documentoService.totalDocumentos(), 2);
        assertArrayEquals(this.documentoService.listaDocumentos().toArray(), new Documento[]{htmlOriginal, javaOriginal});
    }

    @Test
    void testRecuperaDocumento() {
        var exemplo = new DocumentoExemplos();
        DocumentoHtml htmlOriginal = new DocumentoHtml("123", exemplo.sampleHTML());
        DocumentoJava javaOriginal = new DocumentoJava("134", exemplo.sampleJava());
        this.documentoService.adicionaDocumento(new DocumentoHtml("123", exemplo.sampleHTML()));
        this.documentoService.adicionaDocumento(new DocumentoJava("134", exemplo.sampleJava()));


        Optional<Documento> documentoHtml = this.documentoService.recuperaDocumento("123");
        assertTrue(documentoHtml.isPresent());
        assertEquals(documentoHtml.get(), htmlOriginal);

        Optional<Documento> documentoJava = this.documentoService.recuperaDocumento("134");
        assertTrue(documentoJava.isPresent());
        assertEquals(documentoJava.get(), javaOriginal);
    }

    @Test
    void testTotalDocumentos() {
        var exemplo = new DocumentoExemplos();
        assertEquals(this.documentoService.totalDocumentos(), 0);
        this.documentoService.adicionaDocumento(new DocumentoHtml("123", exemplo.sampleHTML()));
        assertEquals(this.documentoService.totalDocumentos(), 1);
        this.documentoService.adicionaDocumento(new DocumentoJava("134", exemplo.sampleJava()));
        assertEquals(this.documentoService.totalDocumentos(), 2);
        this.documentoService.adicionaDocumento(new DocumentoTexto("145", "um arquivo! texto simples.\r\nuse DUAS linhas apenas."));
        assertEquals(this.documentoService.totalDocumentos(), 3);
        this.documentoService.adicionaDocumento(new DocumentoTexto("156", "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!"));
        assertEquals(this.documentoService.totalDocumentos(), 4);
    }

    @Test
    void testConcatena() {
        this.documentoService.adicionaDocumento(new DocumentoTexto("145", "um arquivo! texto simples.\r\nuse DUAS linhas apenas."));
        this.documentoService.adicionaDocumento(new DocumentoTexto("156", "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!"));
        this.documentoService.concatena("145", "156");

        Documento primeiroDocumento = this.documentoService.recuperaDocumento("145").get();
        Documento segundoDocumento = this.documentoService.recuperaDocumento("156").get();

        String termosPrimeiroDoc = Arrays.stream(primeiroDocumento.getTexto()).collect(Collectors.joining());
        String termosSegundoDoc =Arrays.stream(segundoDocumento.getTexto()).collect(Collectors.joining());
        String textoMerge = termosPrimeiroDoc + termosSegundoDoc;

        DocumentoTexto documentoAuxiliar = new DocumentoTexto("_MERGE145|156", textoMerge);
        Optional<Documento> documentoMerge = this.documentoService.recuperaDocumento("_MERGE145|156");
        assertTrue(documentoMerge.isPresent());
        assertEquals(documentoMerge.get(), documentoAuxiliar);
    }

    @Test
    void testSumariza() {
        this.documentoService.adicionaDocumento(new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi\r\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi\r\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto("TEXTO_09",
                "kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato, ningguang, " +
                        "ayato, kamisato, ganyu, ningguang, chongyun, kamisato, xingqiu, keqing, chongyun, ningguang," +
                        "kamisato\r\n"));

        String[] primeiroSumario = this.documentoService.sumariza("TEXTO_07");
        String[] segundoSumario = this.documentoService.sumariza("TEXTO_08");
        String[] terceiroSumario  =this.documentoService.sumariza("TEXTO_09");
        assertEquals(3, primeiroSumario.length);
        assertEquals(5, segundoSumario.length);
        assertEquals(5, terceiroSumario.length);
        assertArrayEquals(primeiroSumario, new String[]{"tartaglia", "raiden", "kokomi"});
        assertArrayEquals(segundoSumario, new String[]{"baizou", "tartaglia", "albedo", "raiden", "kokomi"});
        assertArrayEquals(terceiroSumario, new String[]{"keqing", "xingqiu", "chongyun", "ningguang", "kamisato"});
    }

    @Test
    void testBuscaTermos() {
        Documento primieroDoc = new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi.\n");
        Documento segundoDoc = new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi.\n");
        Documento terceiroDoc = new DocumentoTexto("TEXTO_09",
                """
                        kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato, ningguang,\r                        
                        ayato, kamisato, ganyu, ningguang, chongyun, kamisato, xingqiu, keqing, chongyun, ningguang,\r                         
                        kamisato.\r                     
                        """);
        this.documentoService.adicionaDocumento(primieroDoc);
        this.documentoService.adicionaDocumento(segundoDoc);
        this.documentoService.adicionaDocumento(terceiroDoc);
        Set<Documento> documentosKokomi  = this.documentoService.busca("kokomi");
        Set<Documento> documentosResposta = new HashSet<>();
        documentosResposta.add(primieroDoc);
        documentosResposta.add(segundoDoc);
        assertEquals(documentosKokomi , documentosResposta);

        Set<Documento> documentosAlbedo = this.documentoService.busca("albedo");
        Set<Documento> documentosRespostaAuxiliar = new HashSet<>();
        documentosResposta.add(primieroDoc);
        assertEquals(documentosAlbedo, documentosRespostaAuxiliar);
    }

    @Test
    void testBuscaMetadados() {
        Documento primieroDoc = new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi.\n");
        Documento segundoDoc = new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi.\n");
        Documento terceiroDoc = new DocumentoTexto("TEXTO_09",
                """
                        kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato, ningguang,\r                        
                        ayato, kamisato, ganyu, ningguang, chongyun, kamisato, xingqiu, keqing, chongyun, ningguang,\r                         
                        kamisato.\r                     
                        """);
        this.documentoService.adicionaDocumento(primieroDoc);
        this.documentoService.adicionaDocumento(segundoDoc);
        this.documentoService.adicionaDocumento(terceiroDoc);
        Set<Documento> documentosResultado = this.documentoService.busca("TIPO", "txt");
        Set<Documento> documentosEsperado = new HashSet<>();
        documentosEsperado.add(primieroDoc);
        documentosEsperado.add(segundoDoc);
        documentosEsperado.add(terceiroDoc);
        assertEquals(documentosResultado , documentosEsperado);

        Set<Documento> documentosResultadoAuxiliar = this.documentoService.busca("LINHAS", "3");
        Set<Documento> documentosEsperadoAuxiliar = new HashSet<>();
        documentosEsperadoAuxiliar.add(terceiroDoc);
        assertEquals(documentosResultadoAuxiliar, documentosEsperadoAuxiliar);
    }

    @Test
    void testListaDocumentos() {
        Documento primieroDoc = new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi\r\n");
        Documento segundoDoc = new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi\r\n");
        Documento terceiroDoc = new DocumentoTexto("TEXTO_09",
                "kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato, ningguang, " +
                        "ayato, kamisato, ganyu, ningguang, chongyun, kamisato, xingqiu, keqing, chongyun, ningguang," +
                        "kamisato\r\n");
        this.documentoService.adicionaDocumento(primieroDoc);
        this.documentoService.adicionaDocumento(segundoDoc);
        this.documentoService.adicionaDocumento(terceiroDoc);
        assertEquals(3, this.documentoService.listaDocumentos().size());
        assertArrayEquals(new Documento[]{primieroDoc, segundoDoc, terceiroDoc}, this.documentoService.listaDocumentos().toArray());
    }
}