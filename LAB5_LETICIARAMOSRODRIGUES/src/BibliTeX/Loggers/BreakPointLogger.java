package BibliTeX.Loggers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe responsável por emitir registros sobre as operações do controlador TransformaTexto.
 * As informações são descritas conforme uma padronização, em forma de mensagem.
 * A mensagem informa sobre o método utilizado e o parâmetro, caso exista.
 *
 * @author Leticia Ramos Rodrigues
 */
public class BreakPointLogger implements Logger {
    private final String nomeLogger;
    private int quantidadeChamadas;
    private String chamadaBreakPoint;

    /**
     * Constrói um BreakPointLogger ao definir o seu nome e o método alvo de breakpoint.
     */
    public BreakPointLogger(String nomeTransformacao){
        this.quantidadeChamadas= 0;
        this.nomeLogger = "BreakPointLogger";
        this.chamadaBreakPoint = nomeTransformacao;
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
     * Retorna o nome do método de breakpoint.
     *
     * @return     A String que informa o nome do método de breakpoint.
     */
    public String getChamadaBreakPoint(){
        return this.chamadaBreakPoint;
    }

    /**
     * Retorna a quantidade de vezes que o método de breakpoint foi chamado pelo usuário.
     *
     * @return   O inteiro que informa quantas vezes o método de breakpoint foi chamado.
     */
    public int getQuantidadeChamadas(){
        return this.quantidadeChamadas;
    }

    /**
     *
     * Registra a operação realizada pelo usuário, que não possui parâmetros, ao emitir uma exibição sobre essa.
     *
     * @param nomeMetodo         O nome do método invocado no controlador.
     */
    @Override
    public void log(String nomeMetodo) {
        if(nomeMetodo.equals(chamadaBreakPoint)){
            this.quantidadeChamadas++;
            imprimir(formataMensagem(nomeMetodo));
        }
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
        if(nomeMetodo.equals(chamadaBreakPoint)){
            this.quantidadeChamadas++;
            imprimir(formataMensagem(nomeMetodo, parametroInserido));
        }
    }

    /**
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, dado que o método não possui parâmetros:
     * "[BREAKPOINT LOGGER: AÇÃO - NOME DO MÉTODO]"
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @return                     A String que contém os dados das informações.
     */
    private String formataMensagem(String nomeMetodo) {
        return String.format("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - %s]", nomeMetodo);
    }

    /**
     * Formata a forma de exibição dos dados recebidos do usuário.
     * Os dados estão na seguinte formatação, incluindo os parâmetros do método:
     * "[BREAKPOINT LOGGER: AÇÃO - NOME DO MÉTODO] PARÂMETRO"
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @param parametroInserido    O parâmetro inserido para uso no método.
     * @return                     A String que contém os dados das informações.
     */
    private String formataMensagem(String nomeMetodo, String parametroInserido) {
        return String.format("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - %s] %s", nomeMetodo, parametroInserido);
    }

    /**
     * Constrói uma String que informa a quantidade de vezes que o BrekaPointLogger foi chamado pelo usuário.
     * A exibição dos dados está do mesmo modo, como descrito, no método formataString.
     *
     * @return      A String que informa a quantidade de chamadas método de break do logger.
     */
    @Override
    public String toString(){
        return formataString(this.chamadaBreakPoint, quantidadeChamadas);
    }


    /**
     *
     * Formata a forma de exibição dos dados recebidos do usuário para o toString.
     * Os dados estão na seguinte formatação:
     * "[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT- NOME DO MÉTODO] QUANTIDADE DE CHAMADAS: QUANTIDADE".
     *
     * @param nomeMetodo           O nome do método invocado no controlador
     * @param quantidadeChamadas   A quantidade de chamadas do método de breakpoint.
     * @return                     A String que contém os dados das informações.
     */
    private  String formataString(String nomeMetodo, Integer quantidadeChamadas) {
        return String.format("[BREAKPOINT LOGGER: MÉTODO DE BREAKPOINT - %s] QUANTIDADE DE CHAMADAS: %d", nomeMetodo, quantidadeChamadas);
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
        BreakPointLogger that = (BreakPointLogger) o;
        return Objects.equals(nomeLogger, that.nomeLogger) && Objects.equals(chamadaBreakPoint, that.chamadaBreakPoint);
    }

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "nomeLogger".
     *
     * @return    A representação em hashcode do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nomeLogger, chamadaBreakPoint);
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
