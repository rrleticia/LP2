package BibliTeX.Loggers;

import java.util.Objects;

/**
 * Classe responsável por emitir registros sobre as operações do controlador TransformaTexto.
 * As informações são descritas conforme uma padronização, em forma de mensagem.
 * A mensagem informa sobre o método utilizado e o parâmetro, caso exista, segundo a Barbie.
 *
 * @author Leticia Ramos Rodrigues
 */
public class BarbieLogger implements Logger {
    private final String nomeLogger;
    private int quantidadeChamadas;

    /**
     * Constrói um BarbieLogger ao definir o seu nome.
     */
    public BarbieLogger(){
        this.nomeLogger = "BarbieLogger";
        this.quantidadeChamadas = 0;
    }

    /**
     * Retorna o nome do logger, bem como, definido na classe.
     *
     * @return     A String que representa o nome do logger.
     */
    @Override
    public String getNomeLogger(){
        return this.nomeLogger;
    }

    /**
     *
     * Registra a operação realizada pelo usuário, que não possui parâmetros, ao emitir uma exibição sobre essa.
     *
     * @param nomeMetodo         O nome do método invocado no controlador.
     */
    @Override
    public void log(String nomeMetodo) {
        this.quantidadeChamadas++;
        imprimir(formataMensagem(nomeMetodo));
    }

    /**
     *
     * Registra a operação realizada pelo usuário, incluindo os parâmetros, ao emitir uma exibição sobre essa.
     *
     * @param nomeMetodo         O nome do método invocado no controlador.
     * @param parametroInserido  O parâmetro inserido para uso no método.
     */
    @Override
    public void log(String nomeMetodo, String parametroInserido) {
        this.quantidadeChamadas++;
        imprimir(formataMensagem(nomeMetodo, parametroInserido));
    }

    /**
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, dado que o método não possui parâmetros:
     * "[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU: 'NOME DO MÉTODO']"
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @return                     A String que contém os dados das informações.
     */
    private String formataMensagem(String nomeMetodo) {
        return String.format("[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU': '%s']", nomeMetodo);
    }

    /**
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, incluindo os parâmetros do método:
     * "[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU: 'NOME DO MÉTODO'] PARÂMETRO"
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @param parametroInserido    O parâmetro inserido para uso no método.
     * @return                     A String que contém os dados das informações.
     */
    private String formataMensagem(String nomeMetodo, String parametroInserido) {
        return String.format("[BARBIE PERGUNTOU: 'O QUE VOCÊ FEZ KEN?' >KEN RESPONDEU: '%s'] %s", nomeMetodo,  parametroInserido);
    }

    /**
     *
     * Define a representação textual do logger como a informação sobre o nome desse e quantidade total de usos.
     *
     * @return      A String que representa o Logger textualmente.
     */
    @Override
    public String toString(){
        return String.format("[%s] QUANTIDADE DE LOGS: %d", this.nomeLogger, this.quantidadeChamadas);
    }

    /**
     * Compara dois objetos, com o critério de igualdade estabelecido como o nome do logger.
     * Retorna "true" em caso de igualdade e "false" em caso de diferença.
     *
     * @param o  O objeto alvo da comparação.
     * @return   O boolean que representa a igualdade entre os objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BarbieLogger that = (BarbieLogger) o;
        return Objects.equals(nomeLogger, that.nomeLogger);
    }

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "nomeLogger".
     *
     * @return    A representação em hashcode do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeLogger);
    }

    /**
     * Imprime para o usuário a mensagem de registro do Logger.
     *
     * @param saida    A String que registra a mensagem do logger.
     */
    private void imprimir(String saida){
        System.out.println(saida);
    }

}
