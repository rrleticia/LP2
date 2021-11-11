package BibliTeX.Loggers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe responsável por emitir registros sobre as operações do controlador TransformaTexto.
 * As informações são descritas conforme uma padronização que informa a quantidade de vezes que os métodos foram utilizados.
 * A mensagem informa sobre o método utilizado e a quantidade de vezes de utilizações, nota-se que se exibem apenas
 * os métodos com ao menos uma utilização.
 * @author Leticia Ramos Rodrigues
 */
public class ContagemLogger implements Logger {
    private final String nomeLogger;
    private Map<String, Integer> mapaMetodoContagem;

    /**
     * Constrói um ContagemLogger ao definir o seu nome e ao inicializar um mapa de armazenamento de dados.
     */
    public ContagemLogger() {
        this.nomeLogger = "ContagemLogger";
        this.mapaMetodoContagem = new LinkedHashMap<>();
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
     *
     * Registra a operação realizada pelo usuário, que não possuir parâmetros, de modo a também emitir uma exibição sobre essa.
     *
     * @param nomeMetodo         O nome do método invocado no controlador.
     */
    @Override
    public void log(String nomeMetodo) {
        loggarInformacao(nomeMetodo);
    }

    /**
     *
     * Registra a operação realizada pelo usuário.
     *
     * @param nomeMetodo         O nome do método invocado no controlador.
     * @param parametroInserido  O parâmetro inserido para uso no método.
     */
    @Override
    public void log(String nomeMetodo, String parametroInserido) {
        loggarInformacao(nomeMetodo);
    }

    /**
     * Constrói ao atualizar um mapa que contém as informações referentes
     * ao método, como chave, associado ao número de vezes de chamadas desse, como valor.
     *
     * @param nomeMetodo       O nome do método invocado no controlador
     */
    private void loggarInformacao(String nomeMetodo){
        if(this.mapaMetodoContagem.containsKey(nomeMetodo)){
            Integer quantidadeAtual = mapaMetodoContagem.get(nomeMetodo);
            this.mapaMetodoContagem.put(nomeMetodo, (quantidadeAtual + 1));
        }
        else{
            this.mapaMetodoContagem.put(nomeMetodo, 1);
        }
    }

    /**
     *
     * Constrói uma String que informa a quantidade de vezes que os métodos foram chamados,
     * incluindo apenas aqueles com pelo menos uma chamada.
     * As informações estão no formato "[LOGGER CONTAGEM: AÇÃO - NOME DO MÉTODO #QUANTIDADE DE CHAMADAS: NÚMERO DE CHAMADAS]".
     *
     * @return     A String que contém a listagem do número de chamadas de cada método.
     */
    @Override
    public String toString(){
        String listaContagem = "";
        for (String metodo : mapaMetodoContagem.keySet()){
            Integer quantidade = mapaMetodoContagem.get(metodo);
            listaContagem += String.format("%s\n", formataString(metodo, quantidade));
        }
        return listaContagem.trim();
    }

    /**
     *
     * Formata a forma de exibição dos dados recebidos do usuário para o toString.
     * Os dados estão na seguinte formatação: "[LOGGER CONTAGEM: AÇÃO - NOME DO MÉTODO]
     *                                         QUANTIDADE DE CHAMADAS: QUANTIDADE".
     *
     * @param nomeMetodo           O nome do método invocado no controlador.
     * @param quantidadeChamadas   A quantidade de chamadas do método.
     * @return                     A String que contém os dados das informações.
     */
    private String formataString(String nomeMetodo, Integer quantidadeChamadas) {
        return String.format("[LOGGER CONTAGEM: AÇÃO - %s] QUANTIDADE DE CHAMADAS: %d", nomeMetodo, quantidadeChamadas);
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
        ContagemLogger that = (ContagemLogger) o;
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
