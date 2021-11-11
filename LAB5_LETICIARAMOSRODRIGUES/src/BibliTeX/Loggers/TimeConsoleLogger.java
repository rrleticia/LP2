package BibliTeX.Loggers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por emitir registros sobre as operações do controlador TransformaTexto.
 * As informações são descritas conforme uma padronização que informa sobre o método utilizado e o parâmetro, caso exista,
 * em conjunto, com o tempo decorrido desde a criação do logger.
 *
 * @author Leticia Ramos Rodrigues
 */
public class TimeConsoleLogger implements Logger {
    private final String nomeLogger;
    private int quantidadeChamadas;
    private long momentoTempoCriacao;

    /**
     * Constrói um BreakPointLogger ao definir o seu nome e o momento exato da criação do logger.
     */
    public TimeConsoleLogger() {
        this.nomeLogger = "TimeConsoleLogger";
        this.quantidadeChamadas = 0;
        this.momentoTempoCriacao = System.currentTimeMillis();
    }

    /**
     * Retorna o nome do logger, bem como, definido na classe.
     *
     * @return     A String que representa o nome do logger.
     */
    @Override
    public String getNomeLogger() {
        return this.nomeLogger;
    }

    /**
     * Retorna o momento de criação do logger em milissegundos.
     *
     * @return    O long que representa o momento de criação do logger.
     */
    public long getMomentoTempoCriacao(){
        return this.momentoTempoCriacao;
    }

    /**
     * Calcula quanto tempo decorre desde a criação do logger até a invocação do método de registro.
     *
     * @return      O long que presenta o tempo decorrido.
     */
    public long getTempoDecorrido(){
        return System.currentTimeMillis() - getMomentoTempoCriacao();
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
        imprimir(formataMensagem(nomeMetodo, getTempoDecorrido()));
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
        imprimir(formataMensagem(nomeMetodo, getTempoDecorrido(), parametroInserido));
    }

    /**
     *
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, dado que o método não possui parâmetros:
     * "[LOGGER TIME CONSOLE: INVOCADO - NOME DO MÉTODO (TEMPO PERCORRIDOms)]".
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @return                     A String que contém os dados das informações.
     */
    private String formataMensagem(String nomeMetodo, long tempo) {
        return String.format("[LOGGER TIME CONSOLE: INVOCADO - %s (%dms)]", nomeMetodo, tempo);
    }

    /**
     *
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, incluindo os parâmetros do método:
     * "[LOGGER TIME CONSOLE: INVOCADO - NOME DO MÉTODO (TEMPO DECORRIDOms)] PARÂMETRO".
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @param parametroInserido    O parâmetro inserido para uso no método.
     * @return                     A String que contém os dados das informações.
     */
     private String formataMensagem(String nomeMetodo,  long tempo, String parametroInserido) {
        return String.format("[LOGGER TIME CONSOLE: INVOCADO - %s (%dms)] %s", nomeMetodo, tempo, parametroInserido);
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
        TimeConsoleLogger that = (TimeConsoleLogger) o;
        return momentoTempoCriacao == that.momentoTempoCriacao && Objects.equals(nomeLogger, that.nomeLogger);
    }

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "nomeLogger".
     *
     * @return    A representação em hashcode do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeLogger, momentoTempoCriacao);
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
     * Imprime para o usuário a mensagem de registro do Logger.
     *
     * @param saida    A String que registra a mensagem do logger.
     */
    private void imprimir(String saida){
        System.out.println(saida);
    }
}
