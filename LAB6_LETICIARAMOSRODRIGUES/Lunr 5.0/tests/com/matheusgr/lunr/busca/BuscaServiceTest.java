package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BuscaServiceTest extends BaseBuscaTest{

    protected BuscaService buscaService;
    protected BuscaSimples buscaSimples;
    protected BuscaAvancada buscaAvancada;
    private BuscaSimples buscaSimplesAuxiliar;
    private BuscaAvancada buscaAvancadaAuxiliar;
    private BuscaSimples buscaSimplesMultiplos;

    @BeforeEach
    void preparaBuscaService(){
        this.buscaService = new BuscaService(documentoService);

        String[] termos = {"albedo"};
        this.buscaSimples = new BuscaSimples(termos);
        String[] termosAuxiliar = {"um"};
        this.buscaSimplesAuxiliar = new BuscaSimples(termosAuxiliar);
        String[] termosMutlipos = {"albedo", "kokomi"};
        this.buscaSimplesMultiplos = new BuscaSimples(termosMutlipos);

        Map<String, String> metadadosBuscados = new HashMap<>();
        metadadosBuscados.put("TIPO", "txt");
        this.buscaAvancada = new BuscaAvancada(metadadosBuscados);

        Map<String, String> metadadosBuscadosAuxiliar = new HashMap<>();
        metadadosBuscadosAuxiliar.put("TIPO", "txt");
        metadadosBuscadosAuxiliar.put("LINHAS", "5");
        this.buscaAvancadaAuxiliar = new BuscaAvancada(metadadosBuscadosAuxiliar);
    }

    @Test
    void testBuscaSimples() {
        DocumentoDTO[] documentos = buscaService.busca(buscaSimples);
        assertEquals(documentos.length, 1);
        assertEquals(documentos[0].getId(), TEXTO3_ID);

        DocumentoDTO[] documentosAuxiliar = buscaService.busca(buscaSimplesAuxiliar);
        assertEquals(documentosAuxiliar.length, 5);

        Set<String> resultadoIds = Stream.of(documentosAuxiliar).map(DocumentoDTO::getId).collect(Collectors.toSet());
        Set<String> esperadoIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadoIds);

        DocumentoDTO[] documentosMultiplos = buscaService.busca(buscaSimplesMultiplos);
        assertEquals(documentosMultiplos.length, 2);
        resultadoIds = Stream.of(documentosMultiplos).map(DocumentoDTO::getId).collect(Collectors.toSet());
        esperadoIds = Stream.of(new String[] {TEXTO3_ID, TEXTO4_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadoIds);
    }

    @Test
    void testBuscaAvancada() {
        DocumentoDTO[] documentos = buscaService.busca(buscaAvancada);
        assertEquals(documentos.length, 6);

        Set<String> resultadoIds = Stream.of(documentos).map(DocumentoDTO::getId).collect(Collectors.toSet());
        Set<String> esperadoIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadoIds);

        DocumentoDTO[] documentosAuxiliar = buscaService.busca(buscaAvancadaAuxiliar);
        assertEquals(documentosAuxiliar.length, 2);

        Set<String> resultadoIdsAuxiliar = Stream.of(documentosAuxiliar).map(DocumentoDTO::getId).collect(Collectors.toSet());
        Set<String> esperadoIdsAuxiliar  = Stream.of(new String[] {TEXTO3_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIdsAuxiliar, esperadoIdsAuxiliar);
    }

    @Test
    void testRecuperaHistorico() {
        buscaService.busca(buscaSimples);
        HistoricoBusca historicoBuscaSimples = this.buscaService.recuperaHistorico(0);
        String[][] historicoBSDepuracao = historicoBuscaSimples.debug();
        String[] historicoBSIds = historicoBuscaSimples.ids();
        assertEquals(1, historicoBSDepuracao.length);
        assertArrayEquals(new String[] {"TERMO 1", "albedo"}, historicoBSDepuracao[0]);
        assertEquals(1, historicoBSIds.length);
        assertArrayEquals(new String[] {TEXTO3_ID}, historicoBSIds);


        buscaService.busca(buscaSimplesAuxiliar);
        HistoricoBusca historicoBuscaSimplesAuxiliar = this.buscaService.recuperaHistorico(1);
        String[][] historicoBSADepuracao = historicoBuscaSimplesAuxiliar.debug();
        String[]  historicoBSAIds = historicoBuscaSimplesAuxiliar.ids();
        assertEquals(1, historicoBSADepuracao.length);
        assertArrayEquals(new String[] {"TERMO 1", "um"}, historicoBSADepuracao[0]);
        assertEquals(5, historicoBSAIds.length);
        assertArrayEquals(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID}, historicoBSAIds);

        buscaService.busca(buscaAvancada);
        HistoricoBusca historicoBuscaAvancada = this.buscaService.recuperaHistorico(2);
        String[][] historicoBADepuracao = historicoBuscaAvancada.debug();
        String[]  historicoBAIds = historicoBuscaAvancada.ids();
        assertEquals(1, historicoBADepuracao.length);
        assertArrayEquals(new String[] {"METADADO 1: TIPO", "VALOR DO METADADO: txt"}, historicoBADepuracao[0]);
        assertEquals(6, historicoBAIds.length);
        assertArrayEquals(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID, TEXTO6_ID}, historicoBAIds);

        buscaService.busca(buscaAvancadaAuxiliar);
        HistoricoBusca historicoBuscaAvancadaAuxiliar = buscaService.recuperaHistorico(3);
        String[][] historicoBAADepuracao = historicoBuscaAvancadaAuxiliar.debug();
        String[]  historicoBAAIds = historicoBuscaAvancadaAuxiliar.ids();
        assertEquals(2, historicoBAADepuracao.length);
        assertArrayEquals(new String[] {"METADADO 1: LINHAS", "VALOR DO METADADO: 5"}, historicoBAADepuracao[0]);
        assertArrayEquals(new String[] {"METADADO 2: TIPO", "VALOR DO METADADO: txt"}, historicoBAADepuracao[1]);
        assertEquals(2, historicoBAAIds.length);
        assertArrayEquals(new String[] {TEXTO3_ID, TEXTO6_ID}, historicoBAAIds);

        buscaService.busca(buscaSimplesMultiplos);
        HistoricoBusca historicoBuscaSimplesMultiplos = buscaService.recuperaHistorico(4);
        String[][] historicoBSMDepuracao = historicoBuscaSimplesMultiplos.debug();
        String[]  historicoBSMIds = historicoBuscaSimplesMultiplos.ids();
        assertEquals(2, historicoBSMDepuracao.length);
        assertArrayEquals(new String[] {"TERMO 1", "albedo"}, historicoBSMDepuracao[0]);
        assertArrayEquals(new String[] {"TERMO 2", "kokomi"}, historicoBSMDepuracao[1]);
        assertEquals(2, historicoBSMIds.length);
        assertArrayEquals(new String[] {TEXTO3_ID, TEXTO4_ID}, historicoBSMIds);
    }

    @Test
    void testRecuperaHistoricoInvalido() {
        buscaService.busca(buscaSimples);
        buscaService.busca(buscaSimplesAuxiliar);
        buscaService.busca(buscaAvancada);
        buscaService.busca(buscaAvancadaAuxiliar);
        try{
            buscaService.recuperaHistorico(8);
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

}