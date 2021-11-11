package ControleAlunos;

import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * Grupo responsável por armazenar e manipular as informações e as pessoas alocadas em grupo.
 * A identificação única do grupo é o seu nome.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Grupo {
    private String nomeGrupo;
    private int numeroPessoas;
    private boolean tamanhoDefinido;
    private LinkedHashSet<String> grupoAlunos;

    /**
     *
     * Constrói um grupo, a partir do nome do grupo, com tamanho indefinido e infinito de pessoas.
     *
     * @param nomeGrupo   O nomeGrupo do grupo inserido.
     */
    public Grupo(String nomeGrupo){
        if (nomeGrupo.equals(null)){
            throw new NullPointerException("NOME NULO OU INVÁLIDO");
        }
        if(nomeGrupo.isEmpty() || nomeGrupo.isBlank()){
            throw new IllegalArgumentException("NOME VAZIO OU EM BRANCO");
        }

        this.nomeGrupo = nomeGrupo;
        this.grupoAlunos = new LinkedHashSet<String>();
    }

    /**
     * Constrói um grupo, a partir do nome do grupo e do número limite de pessoas definido.
     *
     * @param nomeGrupo            O nome do grupo inserido.
     * @param numeroPessoas        O número máximo de pessoas que podem ser alocadas no grupo.
     */
    public Grupo(String nomeGrupo, int numeroPessoas){
        if (nomeGrupo.equals(null)){
            throw new NullPointerException("NOME NULO OU INVÁLIDO");
        }
        if(nomeGrupo.isEmpty() || nomeGrupo.isBlank()){
            throw new IllegalArgumentException("NOME VAZIO OU EM BRANCO");
        }

        this.nomeGrupo = nomeGrupo;
        this.numeroPessoas = numeroPessoas;
        this.tamanhoDefinido = true;
        this.grupoAlunos = new LinkedHashSet<>();
    }

    /**
     * Retorna o nome do grupo cadastrado no sistema.
     *
     * @return  A String que representa o nome do grupo.
     */
    public String getNomeGrupo(){
        return this.nomeGrupo;
    }

    /**
     * Retorna o limite de número de pessoas que podem compor o grupo.
     *
     * @return     O inteiro que representa o limite de pessoas do grupo.
     */
    public int getNumeroPessoas(){
        return this.numeroPessoas;
    }

    /**
     * Retorna "true" se o grupo tem tamanho definido e "false" se o grupo tem tamanho indefinido.
     *
     * @return  O boolean que representa se o grupo tem tamanho definido.
     */
    public boolean getTamanhoDefinido(){
        return this.tamanhoDefinido;
    }

    /**
     * Retorna o conjunto de alunos cadastrados no grupo.
     *
     * @return  O LinkedHashSet que contém os alunos cadastrados no grupo.
     */
    public LinkedHashSet getGrupoAlunos(){
        return this.grupoAlunos;
    }

    /**
     * Adiciona um aluno no grupo, por meio da identificação única do aluno "matrícula".
     *
     * @param matriculaAluno  A matrícula do aluno para inserção no grupo.
     */
    public void adicionaAluno(String matriculaAluno){
        this.grupoAlunos.add(matriculaAluno);
    }

    /**
     * Retorna uma String que representa textualmente o grupo.
     * A organização é no formato "NOME DO GRUPO".
     *
     * @return A String que representa o aluno textualmente.
     */
    @Override
    public String toString(){
        return this.nomeGrupo;
    }

    /**
     * Compara dois objetos, com o critério de igualdade estabelecido como o nome do grupo.
     * O critério de igualdade não diferencia letras maiúsculas e minúsculas.
     * Retorna "true" em caso de igualdade e "false" em caso de diferença.
     *
     * @param o  O objeto alvo da comparação.
     * @return   O boolean que representa a igualdade entre os objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(this.nomeGrupo.toUpperCase(), grupo.nomeGrupo.toUpperCase());
    }

    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "nome".
     *
     * @return    A representação em hashcode do objeto grupo.
     */
    @Override
    public int hashCode() {
        return 31 * Objects.hash(this.nomeGrupo.toUpperCase());
    }
}
