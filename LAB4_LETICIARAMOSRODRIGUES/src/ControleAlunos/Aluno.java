package ControleAlunos;

import java.util.Objects;

/**
 * Aluno responsável por armazenar e manipular as informações de um aluno.
 * A identificação única do aluno é a sua matrícula.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Aluno {
    private String nome;
    private String curso;
    private String matricula;
    private int numeroRespostas;

    /**
     * Constrói um aluno a partir da matrícula do aluno, nome do aluno e curso em que esse está matriculado.
     *
     * @param matricula    A matrícula do aluno.
     * @param nomeAluno    O nomeAluno do aluno.
     * @param curso        O curso em que o aluno está matriculado.
     */
    public Aluno(String matricula, String nomeAluno, String curso){
        if (matricula.equals(null)){
            throw new NullPointerException("MATRÍCULA NULA");
        } else if(matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("MATRÍCULA VAZIA OU EM BRANCO");
        }

        if (nomeAluno.equals(null)){
            throw new NullPointerException("NOME DE ALUNONULO");
        } else if(nomeAluno.isEmpty() || nomeAluno.isBlank()){
            throw new IllegalArgumentException("NOME DE ALUNO VAZIO OU EM BRANCO");
        }

        if (curso.equals(null)){
            throw new NullPointerException("NOME DE CURSO NULO");
        } else if(curso.isEmpty() || curso.isBlank()){
            throw new IllegalArgumentException("NOME DE CURSO VAZIO OU EM BRANCO");
        }

        this.nome = nomeAluno;
        this.curso = curso;
        this.matricula = matricula;
        this.numeroRespostas = 0;
    }

    /**
     * Retorna o nome do aluno cadastrado no sistema.
     *
     * @return  A String que representa o nome do aluno.
     */
    public String getNomeAluno(){
        return this.nome;
    }

    /**
     * Retorna o curso em que o aluno está matriculado o sistema.
     *
     * @return  A String que representa o curso.
     */
    public String getCurso(){
        return this.curso;
    }

    /**
     * Retorna a matrícula do aluno.
     *
     * @return  A String que representa a matrícula do aluno.
     */
    public String getMatricula(){
        return this.matricula;
    }

    /**
     * Retorna o número de respostas total realizadas pelo aluno.
     *
     * @return  O inteiro que representa o número de respostas do aluno.
     */
    public int getNumeroRespostas() {
        return this.numeroRespostas;
    }

    /**
     * Adiciona uma resposta à quantidade total de respostas já realizada pelo aluno.
     */
    public void adicionaResposta(){
        this.numeroRespostas++;
    }

    /**
     * Constrói uma String que representa textualmente o aluno.
     * A organização é no formato "MATRÍCULA - NOME - CURSO".
     *
     * @return A String que representa o aluno textualmente.
     */
    @Override
    public String toString(){
        return String.format("%s - %s - %s", this.matricula, this.nome, this.curso);
    }

    /**
     * Compara dois objetos, com o critério de igualdade estabelecido como a matrícula do aluno.
     * Retorna "true" em caso de igualdade e "false" em caso de diferença.
     *
     * @param o  O objeto alvo da comparação.
     * @return   O boolean que representa a igualdade entre os objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(this.matricula, aluno.matricula);
    }

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "matrícula".
     *
     * @return    A representação em hashcode do objeto aluno.
     */
    @Override
    public int hashCode() {
        return 31 * Objects.hash(this.matricula);
    }

}
