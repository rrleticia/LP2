package lab2;

/**
 * Representação da rotina de descanso de um estudante.
 * Todo estudante começa a rotina cansado, precisando contabilizar ao
 * menos 26 horas de lazer por semana para, efetivamente, descansar.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Descanso {

    /**
     * Representação numérica do total de horas de descanso contabilizadas.
     *
     */
    private int horasDescanso;
    /**
     * Representação numérica do total de semanas decorridas.
     *
     */
    private int numeroSemanas;
    /**
     * Representação do status geral do aluno, de acordo com
     * o critério estabelecido, como "cansado" ou "descansado".
     *
     */
    private String statusGeral;
    /**
     * Representação em emoji da última sensação em geral do aluno.
     *
     */
    private String emoji;

    /**
     * Constrói o status de descanso de um aluno.
     * Todo aluno começa com o campo horasDescanso e numeroSemanas como nulos.
     * O campo statusGeral é inicializado como "cansado".
     *
     */
    public Descanso() {
        this.horasDescanso = 0;
        this.numeroSemanas = 0;
        this.statusGeral = "cansado";
        this.emoji = "";
    }

    /**
     * Atualiza e define o valor total contabilizado em horasDescanso.
     * Atualiza e limpa o último emoji de sensação.
     *
     * @param valor a quantidade de horas de descanso total.
     */
    public void defineHorasDescanso(int valor) {
        this.horasDescanso = valor;
        this.emoji = "";
    }

    /**
     * Atualiza e define o valor total contabilizado em numeroSemanas.
     * Atualiza e limpa o último emoji de sensação.
     *
     * @param valor a quantidade de numero de semanas.
     */
    public void defineNumeroSemanas(int valor) {
        this.numeroSemanas = valor;
        this.emoji = "";
    }

    /**
     * Atualiza e define a representação de como o estudante sente-se .
     *
     * @param valor emoji que representa a última sensação do estudante.
     */
    void definirEmoji(String valor){
        this.emoji = " - " + valor;
    }

    /**
     * Retorna um inteiro que informa a quantidade de horas de descanso por semanas.
     *
     * @return quantidade semanal de descanso.
     */
    private int calculoStatusGeral(){
        int quantidadeSemanal = 0;
        if (this.horasDescanso > 0 && this.numeroSemanas > 0) {
            quantidadeSemanal = horasDescanso / numeroSemanas;
        }
        return quantidadeSemanal;
    }

    /**
     * Retorna uma string que informa o status geral do aluno. O status varia unicamente
     * entre "cansado" ou "descansado".
     *
     * @return o status de descanso do aluno.
     */
    public String getStatusGeral() {
        if (calculoStatusGeral() >= 26) {
           this.statusGeral = "descansado";
        }
        else {
            this.statusGeral = "cansado";
        }
        return this.statusGeral + this.emoji;
    }
}

