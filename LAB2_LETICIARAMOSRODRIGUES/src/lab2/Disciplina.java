package lab2;

/**
 * Representação do status geral de uma disciplina cursada pelo estudante no EAD.
 * Responsável por armazenar as notas e calcular a média aritmética dessas,
 * contabilizar a quantidade de horas de estudo e informar o status da aprovação por média.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Disciplina {

    /**
     * Representação do nome da disciplina.
     *
     */
    private String nomeDisciplina;
    /**
     * Representação numérica do total de horas de estudo contabilizadas.
     *
     */
    private int horasContadas;
    /**
     * Representação booleana do staus da aprovação por média do estudante.
     *
     */
    private boolean statusDisciplina;
    /**
     * Representação em array das notas do estudante cadastradas.
     *
     */
    private double[] arrayNotas;
    /**
     * Representação em array dos pesos das notas do estudante.
     *
     */
    private int[] arrayPesos;

    /**
     * Constrói uma disciplina a partir de seu nome.
     * Toda disciplina começa com o campo: horasContadas como nulo, arrayNotas como um
     * array de tamanho 4 com variáveis nulas e arrayNotasPesos como um array que garante peso 1 à todas as notas.
     * O campo statusDisciplina é inicializado como false.
     *
     * @param nomeDisciplina nome da respectiva disciplina.
     */
    public Disciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
        this.horasContadas = 0;
        this.statusDisciplina = false;
        this.arrayNotas = new double[4];
        this.arrayPesos = new int[] {1, 1, 1, 1};
    }
    /**
     * Constrói uma disciplina a partir de seu nome e da quantidade de notas.
     * Toda disciplina começa com o campo horasContadas como nulo e arrayPesos
     * como um array que garante peso 1 à todas as notas.
     * O campo statusDisciplina é inicializado como false.
     *
     * @param nomeDisciplina nome da disciplina.
     * @param numeroNotas quantidade de notas da disciplina.
     */
    public Disciplina(String nomeDisciplina, int numeroNotas) {
        this.nomeDisciplina = nomeDisciplina;
        this.horasContadas = 0;
        this.statusDisciplina = false;
        this.arrayNotas = new double[numeroNotas];
        this.arrayPesos = new int[numeroNotas];
        for (int i = 0; i < this.arrayPesos.length; i ++){
            this.arrayPesos[i] = 1;
        }
    }

    /**
     * Constrói uma disciplina a partir de seu nome, da quantidade número de notas e do peso dessas notas.
     * Toda disciplina começa com o campo horasContadas como nulo e
     * arrayPesos como um array que garante peso 1 à todas as notas.
     * O campo statusDisciplina é inicializado como false.
     *
     * @param nomeDisciplina nome da disciplina.
     * @param numeroNotas quantidade de notas da disciplina.
     * @param pesosNotas pesos de cada nota da disciplina.
     */
    public Disciplina(String nomeDisciplina, int numeroNotas, int[] pesosNotas) {
        this.nomeDisciplina = nomeDisciplina;
        this.horasContadas = 0;
        this.statusDisciplina = false;
        this.arrayNotas = new double[numeroNotas];
        this.arrayPesos = pesosNotas;
    }


    /**
     * Atualiza o campo horasContadas.
     * Adiciona o valor informado como parâmetro ao valor total já contabilizado.
     *
     * @param horas quantidade de horas adicionadas ao total.
     */
    public void cadastraHoras(int horas){
        this.horasContadas += horas;
    }

    /**
     * Atualiza o campo arrayNotas.
     * Define o valor da respectiva nota informada pelos parâmetros.
     *
     * @param nota nota especificada para modificação.
     * @param valorNota valor atualizado da nota.
     */
    public void cadastraNota(int nota, double valorNota){
        this.arrayNotas[nota - 1] = valorNota;
    }

    /**
     * Calcula a média ponderada de todas as notas registradas de acordo com o peso fornecido.
     *
     * @return a média ponderada das notas.
     */
    private double calculaMedia(){
        double soma = 0;
        int quant = this.arrayNotas.length;
        int pesos = 0;
        for (int i = 0; i < this.arrayNotas.length; i ++) {
            soma += this.arrayNotas[i] * this.arrayPesos[i];
            pesos += this.arrayPesos[i];
        }
        if (quant != pesos){
            quant = pesos;
        }
        return soma / quant;
    }

    /**
     * Atualiza o campo statusDisciplina.
     * Verifica se a média aritmética das notas é maior ou igual a necessária à aprovação na disciplina.
     *
     * @return a representação em boolean do status da aprovação por média.
     */
    public boolean aprovado(){
        if (calculaMedia() >= 7){
            this.statusDisciplina = true;
        }
        return this.statusDisciplina;
    }

    /**
     * Constroi uma string contendo todas as notas do estudante na disciplia.
     * A representação segue o formato "[nota 1, nota 2, ... notaN]".
     *
     * @return a representação em String de uma lista com todas as notas do estudante.
     */
    private String listaNotas(){
        String strLista = "[";
        for (int i = 0; i < this.arrayNotas.length; i ++) {
            if (i < this.arrayNotas.length - 1) {
                strLista += this.arrayNotas[i] + ", ";
            }
            else {
                strLista += this.arrayNotas[i] + "]";
            }
        }
        return strLista;
    }

    /**
     * Retorna a String que informa o nome da disciplina, as horas de estudo contadas,
     * a média das notas do estudante e uma lista com todas as notas do estudante..
     * A representação segue o formato “NOME DA DISCIPLINA horas de estudo média lista de notas".
     *
     * @return a representação em String do status geral de êxito do estudante na disciplina.
     */
    public String toString(){
        return this.nomeDisciplina + " " + this.horasContadas + " " + this.calculaMedia() + " " + this.listaNotas();
    }

}

