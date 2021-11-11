package com.matheusgr.lunr.documento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.print.Doc;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DocumentoRepositoryTest {

    DocumentoRepository documentoRepository;

    @BeforeEach
    void preparaDocumentoRepository() {
        this.documentoRepository = new DocumentoRepository();
    }

    @Test
    void testAdiciona() {
        Documento documentoTexto = new DocumentoTexto("156", "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!");
        Documento documentoTextoAuxiliar = new DocumentoTexto("145", "um arquivo! texto simples.\r\nuse DUAS linhas apenas.");
        this.documentoRepository.adiciona(documentoTexto);
        this.documentoRepository.adiciona(documentoTextoAuxiliar);
        assertEquals(2, this.documentoRepository.totalDocumentos());
        assertArrayEquals(new Documento[]{documentoTexto, documentoTextoAuxiliar}, this.documentoRepository.getTodosDocumentos().toArray());
    }

    @Test
    void testAdicionaInvalido() {
        Documento documentoTexto = new DocumentoTexto(null, "TEXTO");
        Documento documentoTextoAuxiliar = new DocumentoTexto("   ", "TEXTO");
        try{
            documentoRepository.adiciona(documentoTexto);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            documentoRepository.adiciona(documentoTextoAuxiliar);
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }

        try{
            documentoTexto = new DocumentoTexto("145", null);
            documentoRepository.adiciona(documentoTexto);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            documentoTextoAuxiliar = new DocumentoTexto("156", "    ");
            documentoRepository.adiciona(documentoTextoAuxiliar);
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testRecupera() {
        Documento documentoTexto = new DocumentoTexto("156", "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!");
        Documento documentoTextoAuxiliar = new DocumentoTexto("145", "um arquivo! texto simples.\r\nuse DUAS linhas apenas.");
        this.documentoRepository.adiciona(documentoTexto);
        this.documentoRepository.adiciona(documentoTextoAuxiliar);
        assertTrue(this.documentoRepository.recupera("156").isPresent());
        assertTrue(this.documentoRepository.recupera("145").isPresent());
        assertEquals(this.documentoRepository.recupera("156").get(), documentoTexto);
        assertEquals(this.documentoRepository.recupera("145").get(), documentoTextoAuxiliar);
    }

    @Test
    void testRecuperaInvalido() {
        try{
            documentoRepository.recupera(null);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            documentoRepository.recupera("   ");
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void totalDocumentos() {
        Documento documentoTexto = new DocumentoTexto("156", "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!");
        this.documentoRepository.adiciona(documentoTexto);
        assertEquals(1, this.documentoRepository.totalDocumentos());
        Documento documentoTextoAuxiliar = new DocumentoTexto("145", "um arquivo! texto simples.\r\nuse DUAS linhas apenas.");
        this.documentoRepository.adiciona(documentoTextoAuxiliar);
        assertEquals(2, this.documentoRepository.totalDocumentos());
    }

    @Test
    void testBuscaTermos() {
        Documento primieroDoc = new DocumentoTexto("TEXTO_07",
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi\r\n");
        Documento segundoDoc = new DocumentoTexto("TEXTO_08",
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi\r\n");
        Documento terceiroDoc = new DocumentoTexto("TEXTO_09",
                "kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato, ningguang, " +
                        "ayato, kamisato, ganyu, ningguang, chongyun, kamisato, xingqiu, keqing, chongyun, ningguang," +
                        "kamisato\r\n");
        this.documentoRepository.adiciona(primieroDoc);
        this.documentoRepository.adiciona(segundoDoc);
        this.documentoRepository.adiciona(terceiroDoc);
        Set<Documento> documentosKokomi = this.documentoRepository.busca("kokomi");
        Set<Documento> documentosResposta = new HashSet<>();
        documentosResposta.add(primieroDoc);
        documentosResposta.add(segundoDoc);
        assertEquals(documentosKokomi, documentosResposta);

        Set<Documento> documentosAlbedo = this.documentoRepository.busca("albedo");
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
        this.documentoRepository.adiciona(primieroDoc);
        this.documentoRepository.adiciona(segundoDoc);
        this.documentoRepository.adiciona(terceiroDoc);
        Set<Documento> documentosResultado = this.documentoRepository.busca("TIPO", "txt");
        Set<Documento> documentosEsperado = new HashSet<>();
        documentosEsperado.add(primieroDoc);
        documentosEsperado.add(segundoDoc);
        documentosEsperado.add(terceiroDoc);
        assertEquals(documentosResultado , documentosEsperado);

        Set<Documento> documentosResultadoAuxiliar = this.documentoRepository.busca("LINHAS", "3");
        Set<Documento> documentosEsperadoAuxiliar = new HashSet<>();
        documentosEsperadoAuxiliar.add(terceiroDoc);
        assertEquals(documentosResultadoAuxiliar, documentosEsperadoAuxiliar);
    }


}