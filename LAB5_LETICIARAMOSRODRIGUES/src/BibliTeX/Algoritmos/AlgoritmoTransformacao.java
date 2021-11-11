package BibliTeX.Algoritmos;

/**
 * Interface responsável por definir os métodos abstratos que devem ser implementados
 * nos objetos do tipo AlgoritmoTransformacao, de modo que esses funcionem no controlador TransformaTexto.
 * Oferece uma generalização para direcionar o usuário durante criação de seus próprios objetos,
 * definindo genéricamente os novos tipos de transformação.
 *
 * @author Leticia Ramos Rodrigues
 */
public interface AlgoritmoTransformacao {
    /**
     * Método implementado com a responsabilidade de transformar o texto conforme com a funcionalidade desejada.
     *
     * @param textoOriginal    O texto original para transformação.
     * @return                 A String do texto após a transformação.
     */
    String transformaTexto(String textoOriginal);

    /**
     * Método implementado com a responsabilidade de retornar o nome da transformação, bem como, cadastrado na classe.
     *
     * @return      A String que representa o nome da transformação.
     */
    String getNomeTransformacao();

    /**
     * Método implementado com a responsabilidade de descrever o objeto, através de um número inteiro, possivelmente único.
     *
     * @return   O inteiro que representa o objeto.
     */
    int hashCode();

    /**
     *
     * Método implementado com a responsabilidade de compara a igualdade entre objetos.
     *
     * @param o    O objeto para comparação.
     * @return     O boolean que sinaliza a igualdade.
     */
    boolean equals(Object o);

    /**
     *
     * Método implementado com a responsabilidade de definir a representação textual do objeto.
     *
     * @return        A String que representa o objeto.
     */
    String toString();
}
