package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BuscaSimplesTest extends BaseBuscaTest {

    private BuscaSimples buscaSimples;
    private BuscaSimples buscaSimplesAuxiliar;


    @BeforeEach
    void preparaBuscaSimples(){
        String[] termos = {"albedo"};
        this.buscaSimples = new BuscaSimples(termos);

        String[] termosAuxiliar = {"albedo", "amo"};
        this.buscaSimplesAuxiliar = new BuscaSimples(termosAuxiliar);
    }

    @Test
    void preparaBuscaSimplesInvalida() {
        String[] termos = {""};
        try{
            this.buscaSimples = new BuscaSimples(termos);
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void preparaBuscaSimplesValida() {
        String[] termos = {"albedo", "", "kokomi", ""};
        this.buscaSimples = new BuscaSimples(termos);
    }

    @Test
    void testBusca() {
        Map<Documento, Integer> mapaResultado = buscaSimples.busca(this.documentoService);
        assertEquals(1, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO3_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(1);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testBuscaAuxiliar() {
        Map<Documento, Integer> mapaResultado = buscaSimplesAuxiliar.busca(this.documentoService);
        assertEquals(2, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO3_ID, TEXTO4_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(1);
        esperadosValor.add(2);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testBuscaMaisDeCinco(){
        String[] termosAuxiliar = {"um"};
        this.buscaSimplesAuxiliar = new BuscaSimples(termosAuxiliar);

        Map<Documento, Integer> mapaResultado = buscaSimplesAuxiliar.busca(this.documentoService);
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
    void testBuscaUmTermoVazio(){
        String[] termosAuxiliar = {"um", "", "kokomi"};
        this.buscaSimplesAuxiliar = new BuscaSimples(termosAuxiliar);

        Map<Documento, Integer> mapaResultado = buscaSimplesAuxiliar.busca(this.documentoService);
        assertEquals(6, mapaResultado.size());

        Set<String> resultadoIds = mapaResultado.keySet().stream().map(Documento::getId).collect(Collectors.toSet());
        Set<String> esperadosIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, TEXTO3_ID, TEXTO4_ID, TEXTO5_ID, TEXTO6_ID}).collect(Collectors.toSet());
        assertEquals(resultadoIds, esperadosIds);

        Set<Integer> resultadoValor = new HashSet<>(mapaResultado.values());
        Set<Integer> esperadosValor = new HashSet<>();
        esperadosValor.add(1);
        esperadosValor.add(2);
        assertEquals(resultadoValor, esperadosValor);
    }

    @Test
    void testDescreveConsulta() {
        String[][] descricaoConsulta = buscaSimples.descreveConsulta();
        assertEquals(descricaoConsulta.length, 1);
        assertArrayEquals(descricaoConsulta[0], new String[] {"TERMO 1", "albedo"});
    }

    @Test
    void testDescreveConsultaAuxiliar() {
        String[][] descricaoConsulta = buscaSimplesAuxiliar.descreveConsulta();
        assertEquals(descricaoConsulta.length,2);
        assertArrayEquals(descricaoConsulta[0], new String[] {"TERMO 1", "albedo"});
        assertArrayEquals(descricaoConsulta[1], new String[] {"TERMO 2", "amo"});
    }
}