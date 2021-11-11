package lab2;

/**
 * Representação da rotina de estudo de um estudante no EAD.
 * Responsável por informar a quantidade de horas de internet que o
 * estudante dedicou à disciplina e o status de progesso da meta
 * estabelecida para essa.
 *
 * @author Leticia Ramos Rodrigues
 */
public class RegistroTempoOnline {

    /**
     * Representação do nome da disciplina.
     *
     */
    private String nomeDisciplina;
    /**
     * Representação numérica do total de horas online contabilizadas.
     *
     */
    private int tempoOnlineContado;
    /**
     * Representação númerica da meta de horas online da disciplina.
     *
     */
    private int tempoOnlineMeta;
    /**
     * Representação booleana do status de progesso da meta estabelecida.
     *
     */
    private boolean atingiuMeta;

    /**
     * Constrói uma disciplina a partir de seu nome.
     * Toda disciplina começa com o campo tempoOnlineContabilizado como
     * nulo e o campo tempoOnlineMeta com o padrão de 120 horas.
     * O campo atingiuMetaTempoOnline é inicializado como false.
     *
     * @param nomeDisciplina o nome da respectiva disciplina.
     */
    public RegistroTempoOnline(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineContado = 0;
        this.tempoOnlineMeta = 120;
        this.atingiuMeta = false;
    }

    /**
     * Constrói uma disciplina a partir de seu nome e da sua meta estabelecida.
     * Toda disciplina começa com o campo tempoOnlineContabilizado como nulo.
     * O campo atingiuMetaTempoOnline é inicializado como false.
     *
     * @param nomeDisciplina o nome da respectiva disciplina.
     * @param tempoOnlineEsperado a quantidade de tempo online definida como meta.
     */
    public RegistroTempoOnline(String nomeDisciplina, int tempoOnlineEsperado) {
        this.nomeDisciplina = nomeDisciplina;
        this.tempoOnlineContado = 0;
        this.tempoOnlineMeta = tempoOnlineEsperado;
        this.atingiuMeta = false;
    }

    /**
     * Atualiza o campo tempoOnlineContabilizado.
     * Adiciona o valor informado como parâmetro ao valor total já contabilizado.
     *
     * @param tempo a quantidade de tempo adicionada ao total.
     */
    public void adicionaTempoOnline(int tempo){
        this.tempoOnlineContado += tempo;
    }

    /**
     * Atualiza o campo atingiuMetaTempoOnline.
     * Verifica se o tempo online contabilizado atingiu a meta estabelecida da disciplina.
     *
     * @return a representação em boolean do status de progresso da meta.
     */
    public boolean atingiuMetaTempoOnline(){
        if (tempoOnlineContado >= tempoOnlineMeta){
            this.atingiuMeta = true;
        }
        return this.atingiuMeta;
    }

    /**
     * Retorna a String que informa o nome da disciplina e o status de progresso da meta de tempo online.
     * A representação segue o formato “NOME DA DISCIPLINA tempo online contabilizado/meta de tempo online”.
     *
     * @return a representação em String do status da meta de tempo online.
     */
    public String toString(){
        return this.nomeDisciplina + " " + this.tempoOnlineContado + "/" + this.tempoOnlineMeta;
    }

}

