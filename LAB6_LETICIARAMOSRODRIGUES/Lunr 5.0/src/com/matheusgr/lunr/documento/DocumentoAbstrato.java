package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 *
 * Classe abstrata responsável por permitir definir os métodos padrão das
 * classes do tio "Documento".
 *
 * Cada documento tem um id responsável pela identificação única desse, em conjunto, com o
 * texto original e o texto limpo, instanciados no momento da criação desse.
 *
 * Além disso, há o texto quebrado em subunidades, ou seja, palavras.
 *
 * Ademais, existe o conjunto de metadados que contém informações pertinentes sobre o documento. Esse
 * é definido com base no tipo de documento e dados encontrados nesse.
 *
 */
public abstract class DocumentoAbstrato implements Documento {

    private String id;
    private String original;
    private String limpo;
    protected Map<String, String> metadados;
    private String[] split;

    /**
     *
     * Construtor padrão de um DocumentoAbstrato.
     * Constrói um Documento com base no id e no texto original passados como parâmetro.
     *
     * @param id        O id de identificação do documento.
     * @param original  A String com o texto original do documento.
     */
    public DocumentoAbstrato(String id, String original) {
        this.id = id;
        this.original = original;
    }

    /**
     *
     * Retorna o id cadastrado para o documento no sistema.
     *
     * @return   A String que representa o id do documento.
     */
    public String getId() {
        if (id == null){
            throw new NullPointerException("ID NULO!");
        }
        return this.id;
    }

    /**
     *
     * Retorna o texto original contido no documento.
     *
     * @return  A String que representa o texto original do documento.
     */
    public String getOriginal() {
        return this.original;
    }

    /**
     * Retorna o texto limpo contido no documento.
     * O texto limpo não tem sinais de pontuação.
     *
     * @return    A String que representa o texto limpo do documento.
     */
    public String getLimpo() {
        return this.limpo;
    }

    /**
     *
     * Define o texto limpo do documento.
     *
     * @param limpo   A String que define o texto limpo do documento.
     */
    public void setLimpo(String limpo) {
        this.limpo = limpo;
    }

    /**
     *
     * Calcula a métrica de texto útil contido no documento a partir do texto limpo.
     * Esse cálculo é realizado de modo que os espaços são ignorados, ou seja, não são parte do texto útil.
     *
     * @return      O double que representa a quantidade de texto único,
     *              esse vai de 0 a 1, bem como, uma porcentagem em decimal.
     */
    public double metricaTextoUtil() {
        long extractedSize = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanSpaces, this.limpo)
                .length();
        return (1.0 * extractedSize) / this.original.length();
    }

    /**
     *
     * Retorna um array que contém as palavras, ou unidades, do texto.
     * Esse array está ordenado em alfabeticamente.
     *
     * @return         O array que contém o texto do documento.
     */
    public String[] getTexto() {
        if (this.split == null) {
            this.split = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanLines, this.limpo).split(" ");
            Arrays.sort(this.split);
        }
        return this.split;
    }

    /**
     *
     * Define a representação textual de cada documento.
     * A formatação está definida como "=== ID DO DOCUMENTO
     *                                  TEXTO LIMPO DO DOCUMENTO"
     *
     * @return      A String de representação textual do objeto.
     */
    public String toString() {
        return "===" + this.id + System.lineSeparator() + this.limpo;
    }

    /**
     *
     * Retorna os metadados do documento, conforme a especificação definida por cada documento.
     *
     * @return      O mapa que contém dos metadados do documento.
     */
    public abstract Map<String, String> getMetadados();

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o id do documento.
     *
     * @return    A representação em hashcode do objeto.
     */
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    /**
     * Compara dois objetos, com o critério de igualdade estabelecido como o id do documento.
     * Retorna "true" em caso de igualdade e "false" em caso de diferença.
     *
     * @param o  O objeto alvo da comparação.
     * @return   O boolean que representa a igualdade entre os objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentoAbstrato that = (DocumentoAbstrato) o;
        return Objects.equals(this.getId(), that.getId());
    }

}
