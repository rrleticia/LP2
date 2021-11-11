package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.DocumentoHtml;
import com.matheusgr.lunr.documento.DocumentoJava;
import com.matheusgr.lunr.documento.DocumentoService;
import com.matheusgr.lunr.documento.DocumentoTexto;
import com.matheusgr.lunr.DocumentoExemplos;
import org.junit.jupiter.api.BeforeEach;

public class BaseBuscaTest {

    public static final String TEXTO1_ID = "1";
    public static final String TEXTO2_ID = "2";
    public static final String TEXTO3_ID = "3";
    public static final String TEXTO4_ID = "4";
    public static final String TEXTO5_ID = "5";
    public static final String TEXTO6_ID = "6";
    public static final String JAVA_ID = "7";
    public static final String HTML_ID = "8";

    protected DocumentoService documentoService;

    @BeforeEach
    void setUp() throws Exception {
        this.documentoService = new DocumentoService();
        var exemplo = new DocumentoExemplos();
        this.documentoService.adicionaDocumento(new DocumentoHtml(HTML_ID, exemplo.sampleHTML()));
        this.documentoService.adicionaDocumento(new DocumentoJava(JAVA_ID, exemplo.sampleJava()));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO1_ID, "um arquivo! texto simples.\r\nuse DUAS linhas apenas.\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO2_ID, "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO3_ID,
                """
                        motivos pelos quais eu quero muito o albedo!\r
                        acho ele muito bonito, o character design é impecável.\r
                        eu amo o estilo da gameplay dele, ele é o único personagem de geo que eu amo.\r
                        o va fez um trabalho incrível, sou apaixonada pela fala da ult dele.\r
                        por fim, ainda tem o fato dele ser build de defesa. eu sou stan dos flops!\r                                                                       
                        """));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO4_ID,
                """
                        sou feliz como kokomi stan: um essay.\r
                        sou sim! gosto muito dos talentos dela.\r
                        a água viva cura muito bem, além de ser boa pro meu chongyun.\r
                        a ult dela não está perfeita, não buildei ela por completo, sem comentários.\r
                        amo as animações dela também! o character design, como sempre, impecável.\r
                        com certeza não me arrependo de ter pegado a mais linda!\r
                        """));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO5_ID, """
                        um arquivo! texto simples.\r
                        use QUATRO linhas agora.\r
                        MAIS AVANÇO!\r
                        EM DOBRO\r
                        """));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO6_ID,
                """
                        um arquivo! texto simples.\r
                        use CINCO linhas agora.\r
                        MAIS AVANÇO!\r
                        EM DOBRO\r
                        FIM\r
                        """));
    }
}
