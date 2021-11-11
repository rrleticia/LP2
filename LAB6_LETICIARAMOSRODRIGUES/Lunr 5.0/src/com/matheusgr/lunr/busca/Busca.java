package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import java.util.Map;

/**
 * Interface que representa os métodos de um objeto do tipo "Busca".
 * Através da definição do padrão de busca, a Busca é responsável por realizar
 * operações com o objetivo de reunir todos os elementos que se adequam as informações procuradas.
 */
public interface Busca {

    /**
     *
     * Retorna um mapa que contém os resultados da busca.
     * O mapa inclui apenas os documentos que atendem aos critérios estabelecidos no objeto Busca.
     * Esse mapa associa como chave um documento a um inteiro, esse inteiro pode apresentar diferentes
     * significados dependendo do tipo de Busca.
     *
     * @param ds  DocumentoService a ser utilizado para busca.
     * @return    Um mapa que contém o resultado da busca.
     */
    public Map<Documento, Integer> busca(DocumentoService ds);

    /**
     *
     * Retorna a descrição da consulta em formato de matriz, a fim de explicar os critérios dessa.
     * As linhas indicam os parâmetros passados, enquanto as colunas indicam o valor associado ao parâmetro.
     *
     *
     * @return    A matriz de Strings que descreve a consulta.
     */
    public String[][] descreveConsulta();
}
