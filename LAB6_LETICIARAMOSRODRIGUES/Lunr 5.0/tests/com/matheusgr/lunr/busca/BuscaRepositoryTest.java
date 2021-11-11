package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.DocumentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BuscaRepositoryTest extends BuscaServiceTest {

    private BuscaRepository buscaRepository;

    @BeforeEach
    void preparaBuscaRepository(){
        this.buscaRepository = new BuscaRepository();
    }

    @Test
    void testAdicionaBuscaRecuperar() {
        DocumentoDTO[] documentosBS = buscaService.busca(buscaSimples);
        this.buscaRepository.adicionaBusca(buscaSimples,documentosBS);

        DocumentoDTO[] documentosBA = buscaService.busca(buscaAvancada);
        this.buscaRepository.adicionaBusca(buscaAvancada,documentosBA);

        HistoricoBusca historicoBuscaSimples = this.buscaRepository.recuperar(0);
        String[][] historicoBSDepuracao = historicoBuscaSimples.debug();
        String[] historicoBSIds = historicoBuscaSimples.ids();
        assertEquals(1, historicoBSDepuracao.length);
        assertArrayEquals(new String[] {"TERMO 1", "albedo"}, historicoBSDepuracao[0]);
        assertEquals(1, historicoBSIds.length);
        assertArrayEquals(new String[] {TEXTO3_ID}, historicoBSIds);

        HistoricoBusca historicoBuscaAvancada = this.buscaRepository.recuperar(1);
        String[][] historicoBADepuracao = historicoBuscaAvancada.debug();
        String[]  historicoBAIds = historicoBuscaAvancada.ids();
        assertEquals(1, historicoBADepuracao.length);
        assertArrayEquals(new String[] {"METADADO 1: TIPO", "VALOR DO METADADO: txt"}, historicoBADepuracao[0]);
        assertEquals(6, historicoBAIds.length);
        assertArrayEquals(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID, TEXTO6_ID}, historicoBAIds);
    }

    @Test
    void testAdicionaBuscaInvalido() {
        try{
            Busca buscaSimplesInvalida = new BuscaSimples(new String[] {""});
            this.buscaRepository.adicionaBusca(buscaSimplesInvalida, new DocumentoDTO[] {});
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }

        try{
            Busca buscaAvancadaInvalida = new BuscaAvancada(new HashMap<String, String>());
            this.buscaRepository.adicionaBusca(buscaAvancadaInvalida, new DocumentoDTO[] {});
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testRecuperarInvalido() {
        try{
            this.buscaRepository.recuperar(9);
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

}