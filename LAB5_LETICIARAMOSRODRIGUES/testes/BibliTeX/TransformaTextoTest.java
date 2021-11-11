package BibliTeX;

import BibliTeX.Algoritmos.*;
import BibliTeX.Loggers.BarbieLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformaTextoTest {
    TransformaTexto transformaTexto;

    @BeforeEach
    void preparaTransformaTexto(){
        this.transformaTexto = new TransformaTexto();
    }

    @Test
    void testConstrutor(){
        TransformaTexto transformaTextoTeste = new TransformaTexto();
        BarbieLogger barbieLogger = new BarbieLogger();
        TransformaTexto transformaTextoLoggerTeste = new TransformaTexto(barbieLogger);
        try{
            TransformaTexto transformaTextoNulleste = new TransformaTexto(null);
        } catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }


    @Test
    void testGetTransformacao() {
        try{
            transformaTexto.getTransformacao("abcd");
        } catch (NullPointerException npe){
            npe.printStackTrace();
        }
        assertNotNull(transformaTexto.getTransformacao("foneticaJapao"));
    }

    @Test
    void testExisteTransformacao() {
        assertTrue(transformaTexto.existeTransformacao("clean"));
        assertFalse(transformaTexto.existeTransformacao("cleanLetras"));
    }

    @Test
    void testExisteTransformacaoExcecao(){
        try{
            transformaTexto.existeTransformacao(null);
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            transformaTexto.existeTransformacao("");
        }catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            transformaTexto.existeTransformacao("   ");
        }catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testGetDadoHistorico() {
        String textoOriginal = "eu penso no: venti!";
        transformaTexto.transforma("CaMeLcAsEfY", textoOriginal);
        transformaTexto.transforma("clean", textoOriginal);
        transformaTexto.transforma("cleanSpaces", textoOriginal);
        String dadoHistorico = transformaTexto.getDadoHistorico(2);
        assertEquals("TRANSFORMAÇÃO REALIZADA: cleanSpaces. ORIGINAL: eu penso no: venti!. RESULTADO: eupensono:venti!.", dadoHistorico);
    }

    @Test
    void testCadastraNovaTransformacao() {
        AlgoritmoTransformacao hashtag = new TransformaVogaisEmHashtag();
        transformaTexto.cadastraTransformacao("#Hashtag", hashtag);
        assertTrue(transformaTexto.existeTransformacao("#Hashtag"));
        String textoOriginal = "childe fofinho";
        assertEquals("ch#ld# f#f#nh#", transformaTexto.transforma("#Hashtag", textoOriginal));
    }

    @Test
    void testCadastraMesmaTransformacao() {
        AlgoritmoTransformacao katakana = new TransformaParaKatakana();
        transformaTexto.cadastraTransformacao("Katakana", katakana);
        assertTrue(transformaTexto.existeTransformacao("Katakana"));
        assertTrue(transformaTexto.existeTransformacao("foneticaJapao"));
        assertEquals(transformaTexto.getTransformacao("foneticaJapao"), katakana);
    }

    @Test
    void testCadastraTransformacaoMesmoNome() {
        AlgoritmoTransformacao clean = new RemoveEspacosEmBranco();
        transformaTexto.cadastraTransformacao("clean", clean);
        assertTrue(transformaTexto.existeTransformacao("clean"));
        assertEquals(transformaTexto.getTransformacao("clean"), clean);
    }

    @Test
    void testCadastraTransformacaoDiferenteNomeJaExistente() {
        AlgoritmoTransformacao cleanPontuacao = new RemoveSinaisPontuacao();
        transformaTexto.cadastraTransformacao("clean", cleanPontuacao);
        assertTrue(transformaTexto.existeTransformacao("clean"));
        assertEquals(transformaTexto.getTransformacao("clean"), cleanPontuacao);
    }

    @Test
    void testCadastraTransformacaoNomeExcecao(){
        try{
            AlgoritmoTransformacao cleanPontuacao = new RemoveSinaisPontuacao();
            transformaTexto.cadastraTransformacao(null, cleanPontuacao);
        }
        catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            AlgoritmoTransformacao cleanPontuacao = new RemoveSinaisPontuacao();
            transformaTexto.cadastraTransformacao("", cleanPontuacao);
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            AlgoritmoTransformacao cleanPontuacao = new RemoveSinaisPontuacao();
            transformaTexto.cadastraTransformacao("     ", cleanPontuacao);
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testCadastraTransformacaoAlgoritmoExcecao(){
        try{
            transformaTexto.cadastraTransformacao("cleanPontuacao", null);
        }
        catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testTransformaBase() {
        String textoOriginal = "albedo lindo? sim o mais lindo!";
        assertEquals("AlBeDo lInDo? SiM O MaIs lInDo!", transformaTexto.transforma("CaMeLcAsEfY", textoOriginal));
        assertEquals("albedo lindo sim o mais lindo", transformaTexto.transforma("clean", textoOriginal));
        assertEquals("albedo lindo. sim o mais lindo!", transformaTexto.transforma("InterrogaPraPontos", textoOriginal));
        assertEquals("ALBEDO LINDO? SIM O MAIS LINDO!", transformaTexto.transforma("upperCase", textoOriginal));
        assertEquals("albedolindo?simomaislindo!", transformaTexto.transforma("cleanSpaces", textoOriginal));
        assertEquals("アレイベエデオ レイイエネデオ? エシイエメ オ エメアイエシ レイイエネデオ!", transformaTexto.transforma("foneticaJapao", textoOriginal));
        assertEquals("lbd lnd? sm  ms lnd!", transformaTexto.transforma("semVogais", textoOriginal));
    }

    @Test
    void testetTransformaNovas() {
        AlgoritmoTransformacao hashtag = new TransformaVogaisEmHashtag();
        transformaTexto.cadastraTransformacao("#Hashtag", hashtag);
        String textoOriginal = "albedo lindo? sim o mais lindo!";
        assertEquals("#lb#d# l#nd#? s#m # m##s l#nd#!", transformaTexto.transforma("#Hashtag", textoOriginal));
    }

    @Test
    void testTransformaExcecao() {
        try{
            transformaTexto.transforma(null, "texto");
        }
        catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            transformaTexto.transforma("","texto");
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            transformaTexto.transforma("   ", "texto");
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            transformaTexto.transforma("transformacao", null);
        }
        catch(NullPointerException npe){
            npe.printStackTrace();
        }
        try{
            transformaTexto.transforma("transformacao","");
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
        try{
            transformaTexto.transforma("transformacao", "   ");
        }
        catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testQuantidadeTransformacoesRealizadas() {
        assertEquals(0, transformaTexto.quantidadeTransformacoesRealizadas());
        String textoOriginal = "kaeya mentiroso";
        transformaTexto.transforma("CaMeLcAsEfY", textoOriginal);
        transformaTexto.transforma("clean", textoOriginal);
        assertEquals(2, transformaTexto.quantidadeTransformacoesRealizadas());
    }

    @Test
    void testListagemTextosOriginais() {
        String primeiroTexto = "kazuha eu te amo";
        String segundoTexto = "queria muito o banner da kokomi agora";
        transformaTexto.transforma("upperCase", primeiroTexto);
        transformaTexto.transforma("clean", segundoTexto);
        transformaTexto.transforma("foneticaJapao", segundoTexto);
        transformaTexto.transforma("semVogais", primeiroTexto);
        assertEquals("LISTAGEM DE TEXTOS ORIGINAIS:\nkazuha eu te amo\nqueria muito o banner da kokomi agora", transformaTexto.listagemTextosOriginais());
    }

    @Test
    void testListagemTextosOriginaisVazia() {
        String resultadoLista = transformaTexto.listagemTextosOriginais();
        assertEquals("NÃO FORAM REALIZADAS TRANSFORMAÇÕES", resultadoLista);
    }

    @Test
    void testListagemHistoricoTransformacoes() {
        String primeiroTexto = "não sei jogar de sara";
        String segundoTexto = "meu deusss te amo scaramouche";
        transformaTexto.transforma("clean", primeiroTexto);
        transformaTexto.transforma("upperCase", segundoTexto);
        transformaTexto.transforma("semVogais", segundoTexto);
        transformaTexto.transforma("upperCase", primeiroTexto);
        String resultadoLista = transformaTexto.listagemHistoricoTransformacoes();
        String esperadoLista = "LISTAGEM DO HISTÓRICO DE TRANSFORMAÇÕES:\n" +
                "1 - TRANSFORMAÇÃO REALIZADA: clean. ORIGINAL: não sei jogar de sara. RESULTADO: não sei jogar de sara.\n" +
                "2 - TRANSFORMAÇÃO REALIZADA: upperCase. ORIGINAL: meu deusss te amo scaramouche. RESULTADO: MEU DEUSSS TE AMO SCARAMOUCHE.\n" +
                "3 - TRANSFORMAÇÃO REALIZADA: semVogais. ORIGINAL: meu deusss te amo scaramouche. RESULTADO: m dsss t m scrmch.\n" +
                "4 - TRANSFORMAÇÃO REALIZADA: upperCase. ORIGINAL: não sei jogar de sara. RESULTADO: NÃO SEI JOGAR DE SARA.";
        assertEquals(esperadoLista, resultadoLista);
    }

    @Test
    void testListagemHistoricoTransformacoesVazia() {
        String resultadoLista = transformaTexto.listagemHistoricoTransformacoes();
        assertEquals("NÃO FORAM REALIZADAS TRANSFORMAÇÕES", resultadoLista);
    }

    @Test
    void testListagemTransformacoesAlfabeticaBase() {
        String resultadoLista = transformaTexto.listagemTransformacoesAlfabetica();
        String esperadoLista = "LISTAGEM DAS TRANSFORMAÇÕES CADASTRADAS:\n" +
                "1 - CaMeLcAsEfY\n" +
                "2 - clean\n" +
                "3 - cleanSpaces\n" +
                "4 - foneticaJapao\n" +
                "5 - InterrogaPraPontos\n" +
                "6 - semVogais\n" +
                "7 - upperCase";
        assertEquals(esperadoLista, resultadoLista);
    }

    @Test
    void testListagemTransformacoesAlfabeticaNovo() {
        AlgoritmoTransformacao hashtag = new TransformaVogaisEmHashtag();
        transformaTexto.cadastraTransformacao("#Hashtag", hashtag);
        String resultadoLista = transformaTexto.listagemTransformacoesAlfabetica();
        String esperadoLista = "LISTAGEM DAS TRANSFORMAÇÕES CADASTRADAS:\n" +
                "1 - CaMeLcAsEfY\n" +
                "2 - clean\n" +
                "3 - cleanSpaces\n" +
                "4 - foneticaJapao\n" +
                "5 - InterrogaPraPontos\n" +
                "6 - semVogais\n" +
                "7 - TransformaVogaisEm#\n" +
                "8 - upperCase";
        assertEquals(esperadoLista, resultadoLista);
    }

}