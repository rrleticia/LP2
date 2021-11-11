package com.matheusgr.similaridade;

import com.matheusgr.lunr.LunrApp;
import com.matheusgr.lunr.busca.BuscaController;
import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoController;
import com.matheusgr.lunr.documento.DocumentoService;
import com.matheusgr.lunr.documento.DocumentoTexto;
import org.junit.jupiter.api.BeforeEach;

public class BaseSimilaridadeTest {

    public static final String TEXTO1_ID = "123";
    public static final String TEXTO2_ID = "134";
    public static final String TEXTO3_ID = "145";
    public static final String TEXTO4_ID = "156";
    public static final String TEXTO5_ID = "167";
    public static final String TEXTO6_ID = "178";
    public static final String TEXTO7_ID = "189";
    public static final String TEXTO8_ID = "190";

    protected DocumentoService documentoService;

    @BeforeEach
    void setUp() throws Exception {
        this.documentoService = new DocumentoService();
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO1_ID, "um arquivo! texto simples.\r\nuse DUAS linhas apenas."));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO2_ID, "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!"));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO3_ID,
                """
                        motivos pelos quais eu quero muito o albedo!\r
                        acho ele muito bonito, o character design é impecável.\r
                        eu amo o estilo da gameplay dele, ele é o único personagem geo que eu amo.\r
                        o va fez é super incrível, sou apaixonada pela fala da ult dele.\r
                        por fim, ainda tem o fato dele ser build de defesa. eu sou stan dos flops!"""));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO4_ID,
                """
                        sou feliz como kokomi stan: um essay.\r
                        sou sim! gosto dos talentos dela.\r
                        a água viva cura bem, além de ser boa pro meu chongyun.\r
                        a ult dela não está perfeita, não buildei ela por completo, sem comentários.\r
                        amo as animações dela também! o character design, como sempre, impecável.\r
                        com certeza não me arrependo de ter pegado a linda mais lida!"""));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO5_ID,
                """
                        sou feliz como kokomi stan: um essay.\r
                        sou sim! gosto dos talentos dela.\r
                        a água viva cura bem, além de ser boa pro meu chongyun.\r
                        a ult dela não está perfeita, não buildei ela por completo, sem comentários.\r
                        amo as animações dela também! o character design, como sempre, impecável.\r
                        com certeza não me arrependo de ter pegado a linda mais lida!"""));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO6_ID,
                "kokomi, kokomi, venti, kaeya, raiden, raiden, tartaglia, kokomi, qiqi\r\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO7_ID,
                "kokomi, albedo, kokomi, venti, kaeya, raiden, raiden, tartaglia, baizou, kokomi, qiqi\r\n"));
        this.documentoService.adicionaDocumento(new DocumentoTexto(TEXTO8_ID,
                """
                        kamisato, sara, ningguang, xingqiu, sayu, chongyun, sara, kamisato,
                        ningguang, ayato, kamisato, ganyu, ningguang, chongyun, kamisato,
                        xingqiu, keqing, chongyun, ningguang, kamisato\r
                        """));

    }
}
