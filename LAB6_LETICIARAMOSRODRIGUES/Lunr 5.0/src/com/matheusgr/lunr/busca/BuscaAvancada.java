package com.matheusgr.lunr.busca;

import com.matheusgr.lunr.documento.Documento;
import com.matheusgr.lunr.documento.DocumentoService;

import javax.print.Doc;
import java.util.*;

/**
 * BuscaSimples realiza uma operação de busca a partir de metadados
 *
 * Dado os metadados, deve se buscar por tais documentos, de modo que apenas os documentos
 * que contém todos os metadados são válidos como resultado da busca.
 *
 * Não existe um grau de relevância ou necessidade de ordenação.
 *
 * O valor associado ao metadado também é considerado como um critério da busca.
 *
 * Os documentos que não tem nenhum dos metadados pesquisados, ou em que os metadados estão associados
 * a valores diferentes do da busca, não devem ser retornados.
 */
public class BuscaAvancada implements Busca {

    private Map<String, String> metadadosBuscados;

    /**
     *
     * Construtor padrão com os metadados a serem encontrados.
     *
     * Os metadados não vazios são ignorados. Pelo menos 1 metadado deve ser não vazio.
     *
     * @param metadadosBusca   Os metadados a serem encontrados.
     */
    public BuscaAvancada(Map<String, String> metadadosBusca){
        (new ValidadorBusca()).valida(metadadosBusca);
        this.metadadosBuscados = metadadosValidos(metadadosBusca);
    }

    /**
     *
     * Remove os metadados em que a chave ou o valor estão vazios, de modo a manter apenas
     * os metadados válidos necessários para a busca avançada.
     *
     * @param metadadosBusca    Os metadados a serem encontrados.
     * @return                  O mapa que contém apenas os metadados válidos para a busca/
     */
    private Map<String, String> metadadosValidos(Map<String, String> metadadosBusca) {
        Map<String, String> metadadosValidos = new LinkedHashMap<>();
        for(String metadado : metadadosBusca.keySet()){
            if (metadado.isBlank() || metadadosBusca.get(metadado).isBlank()){
                continue;
            }
            else{
                metadadosValidos.put(metadado, metadadosBusca.get(metadado));
            }
        }
        return metadadosValidos;
    }

    /**
     * Realiza a busca a partir da consulta ao DocumentoService.
     *
     * O DocumentoService realiza apenas a operação de reunir todos os documentos armazenados no repositório.
     *
     * @param ds DocumentoService a ser utilizado para busca.
     * @return Mapa com os documentos encontrados e a quantidade de metadados válidos.
     */
    @Override
    public Map<Documento, Integer> busca(DocumentoService ds) {
        Map<Documento, Integer> respostaDocumentos = new LinkedHashMap<>();

        for (String metadado : this.metadadosBuscados.keySet()){
            String esperadoValor = this.metadadosBuscados.get(metadado);
            Set<Documento> documentos = ds.busca(metadado, esperadoValor);
            for(Documento d : documentos){
                respostaDocumentos.put(d, respostaDocumentos.getOrDefault(d, 0) + 1);
            }
        }

        Documento[] documentos = respostaDocumentos.keySet().toArray(new Documento[0]);
        for(int i = 0; i < documentos.length; i++){
            if(respostaDocumentos.get(documentos[i]) != this.metadadosBuscados.size()){
                respostaDocumentos.remove(documentos[i]);
            }
        }

        return respostaDocumentos;
    }

    /**
     * Descreve uma consulta para explicar a consulta que foi feita.
     * A linha apresenta-se na formatação "METADADO (NÚMERO DO METADADO): METADADO BUSCADO".
     * A coluna apresenta-se na formatação "VALOR DO METADADO: VALOR DO METADADO BUSCADO".
     *
     * @return Descrição da busca, onde cada linha representa um parâmetro de busca
     *         e as colunas representam um detalhamento de cada parâmetro.
     */
    @Override
    public String[][] descreveConsulta() {
        String[][] resultado = new String[this.metadadosBuscados.size()][];

        int i = 0;
        for (String metadado : metadadosBuscados.keySet()) {
            resultado[i] = new String[] {"METADADO " + (i + 1) + ": " + metadado,
                                         "VALOR DO METADADO: " + this.metadadosBuscados.get(metadado)};
            i++;
        }

        return resultado;

    }
}
