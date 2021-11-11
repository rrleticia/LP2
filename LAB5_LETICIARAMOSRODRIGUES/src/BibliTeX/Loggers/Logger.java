package BibliTeX.Loggers;

/**
 * Interface responsável por definir os métodos abstratos que devem ser implementados
 * nos objetos do tipo Logger.
 * Oference uma generalização para que o usuário consiga criar seus próprios objetos,
 * a fim definir novos tipos de loggers.
 *
 * @author Leticia Ramos Rodrigues
 */
public interface Logger {

    /**
     * Método implementado com a responsabilidade de descrever o objeto, através de um número inteiro, possivelmente único.
     *
     * @return   O inteiro que representa o objeto.
     */
    int hashCode();

    /**
     *
     * Método implementado com a responsabilidade de definir a representação textual do objeto.
     *
     * @return        A String que representa o objeto.
     */
    String toString();


    /**
     * Método implementado com a responsabilidade de retornar o nome do logger, bem como, cadastrado na classe.
     *
     * @return      A String que representa o nome do logger.
     */
    String getNomeLogger();

    /**
     *
     * Método implementado com a responsabilidade de compara a igualdade entre objetos.
     *
     * @param o    O objeto para comparação.
     * @return     O boolean que sinaliza a igualdade.
     */
    boolean equals(Object o);

    /**
     * Método implementado com a responsabilidade de imprimir registros para o usuário dos métodos, sem parâmetros,
     * utilizados no controlador TransformaTexto.
     *
     * @param nomeMetodo     O nome do método utilizado.
     */
    void log(String nomeMetodo);

    /**
     * Método implementado com a responsabilidade de imprimir registros para o usuário dos métodos, com parâmetros,
     * utilizados no controlador TransformaTexto.
     *
     * @param nomeMetodo            O nome do método utilizado.
     * @param parametroInserido     O parâmetor inserido no método utilizado.
     */

    void log(String nomeMetodo, String parametroInserido);
}
