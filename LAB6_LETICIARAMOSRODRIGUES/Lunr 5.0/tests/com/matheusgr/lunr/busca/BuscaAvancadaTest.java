package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BuscaAvancadaTest extends BaseBuscaTest {

    private BuscaAvancada buscaAvancada;
    private BuscaAvancada buscaAvancadaAuxiliar;
    private BuscaAvancada buscaTermoBranco;

    @BeforeEach
    void preparaBuscaAvancada(){
        Map<String, String> metadadosBuscados = new HashMap<>();
        metadadosBuscados.put("TIPO", "txt");
        this.buscaAvancada = new BuscaAvancada(metadadosBuscados);

        Map<String, String> metadadosBuscadosAuxiliar = new HashMap<>();
        metadadosBuscadosAuxiliar.put("TIPO", "txt");
        metadadosBuscadosAuxiliar.put("LINHAS", "5");
        this.buscaAvancadaAuxiliar = new BuscaAvancada(metadadosBuscadosAuxiliar);

        Map<String, String> metadados = new HashMap<>();
        metadados.put("TIPO", "");
        metadados.put("LINHAS", "5");
        this.buscaTermoBranco = new BuscaAvancada(metadados);
    }

    @Test
    void preparaBuscaAvancadaInvalida(){
        Map<String, String> metadadosInvalidos = new HashMap<>();
        metadadosInvalidos.put("", "txt");
        metadadosInvalidos.put("LINHAS", "");
        try{
            this.buscaAvancada = new BuscaAvancada(metadadosInvalidos);
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void preparaBuscaAvancadaValida(){
        Map<String, String> metadadosValidos = new HashMap<>();
        metadadosValidos.put("", "txt");
        metadadosValidos.put("LINHAS", "1");
        this.buscaAvancada = new BuscaAvancada(metadadosValidos);
        metadadosValidos.put("TIPO", "txt");
        metadadosValidos.put("LINHAS", "");
        this.buscaAvancada = new BuscaAvancada(metadadosValidos);
    }

    @Test
    void testBusca() {
        Map<Documento, Integer> mapaResultado = buscaAvancada.busca(this.documentoService);
        assertEquals(6, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(1);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testBuscaAuxiliar() {
        Map<Documento, Integer> mapaResultado = buscaAvancadaAuxiliar.busca(this.documentoService);
        assertEquals(2, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO3_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(2);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testBuscaUmTermoBranco() {
        Map<Documento, Integer> mapaResultado = buscaTermoBranco.busca(this.documentoService);
        assertEquals(2, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO3_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(1);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testBuscaVazia() {
        Map<String, String> metadadosBusca = new HashMap<>();
        metadadosBusca.put("TIPO", "DTO");
        BuscaAvancada buscaVazia = new BuscaAvancada(metadadosBusca);
        Map<Documento, Integer> mapaResultado = buscaVazia.busca(this.documentoService);
        assertEquals(0, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testDescreveConsulta() {
        String[][] descricaoConsulta = buscaAvancada.descreveConsulta();
        assertEquals(descricaoConsulta.length, 1);
        assertArrayEquals(descricaoConsulta[0], new String[] {"METADADO 1: TIPO", "VALOR DO METADADO: txt"});
    }

    @Test
    void testDescreveConsultaAuxiliar() {
        String[][] descricaoConsulta = buscaAvancadaAuxiliar.descreveConsulta();
        assertEquals(descricaoConsulta.length, 2);
        assertArrayEquals(descricaoConsulta[0], new String[] {"METADADO 1: LINHAS", "VALOR DO METADADO: 5"});
        assertArrayEquals(descricaoConsulta[1], new String[] {"METADADO 2: TIPO", "VALOR DO METADADO: txt"});
    }

    @Test
    void testDescreveConsultaUmTermoBranco() {
        String[][] descricaoConsulta = buscaTermoBranco.descreveConsulta();
        assertEquals(descricaoConsulta.length, 1);
        assertArrayEquals(descricaoConsulta[0], new String[] {"METADADO 1: LINHAS", "VALOR DO METADADO: 5"});
    }

}