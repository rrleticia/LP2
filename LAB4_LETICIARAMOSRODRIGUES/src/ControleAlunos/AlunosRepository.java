package ControleAlunos;

import java.util.*;

/**
 * Repositório de alunos responsável por manipular os dados e as operações realizadas sob os alunos cadastrados.
 *
 * @author Leticia Ramos Rodrigues
 */
public class AlunosRepository {
    private LinkedHashMap<String, Aluno> mapaRepostorioAlunos;
    private ArrayList<Aluno> listaRespostasResgitradas;
    private LinkedHashMap<String, Integer> mapaCursoRespostas;

    /**
     * Constrói um repositório de alunos.
     * O repositório contém conjuntos de respostas e mapas de alunos de cursos.
     * No campo "alunos" mapeia as chaves e os valores no formato "Matrícula do Aluno: Objeto aluno".
     * No campo "mapaCursoQuantidadeRespostas" mapeia as chaves e os valores no formato
     * "Nome do Curso: Quantidade de Repostas Totais".
     */
    public AlunosRepository(){
        this.mapaRepostorioAlunos = new LinkedHashMap<String, Aluno>();
        this.listaRespostasResgitradas = new ArrayList<Aluno>();
        this.mapaCursoRespostas = new LinkedHashMap<String, Integer>();
    }

    /**
     * Retorna o aluno cadastrado a partir da matrícula como critério de identificação.
     *
     * @param matricula     A matrícula do aluno para busca.
     * @return              O objeto aluno associado a chave com a matrícula do aluno.
     */
    public Aluno getAluno(String matricula) {
        return this.mapaRepostorioAlunos.get(matricula);
    }

    /**
     * Cadastra um novo aluno no repositório de alunos, identificado pela chave correspondente à matrícula do aluno.
     *
     * @param matricula   A matrícula de identificação do aluno.
     * @param nome        O nome do aluno.
     * @param curso       O curso em que o aluno está matriculado.
     */
    public void cadastrar(String matricula, String nome, String curso){
        this.mapaRepostorioAlunos.put(matricula, new Aluno(matricula, nome, curso));
    }

    /**
     * Consulta a existência de um aluno específico no repositório.
     *
     * @param matriculaEntrada     A matrícula de identificação do aluno.
     * @return                     A String que representa textualmente o aluno.
     */
    public String consultar(String matriculaEntrada){
        return getAluno(matriculaEntrada).toString();
    }


    /**
     *
     * Verifica a existência de um aluno no repositório por meio do critério de identificação, estabelecido como a matrícula do aluno.
     * Retorna "true" se o grupo está cadastrado e "false" se o grupo não está cadastrado no repositório.
     *
     * @param matriculaEntrada    A matrícula para a verificação.
     * @return                    O boolean que informa o estado de existência do grupo no repositório.
     */
    public boolean existeAluno(String matriculaEntrada) {
        if (this.mapaRepostorioAlunos.containsKey(matriculaEntrada)) {
            return true;
        }
        return false;
    }

    /**
     * Registra a resposta do aluno na listagem de repostas.
     *
     * @param matricula  A matrícula de identificação do aluno.
     */
    public void registrarResposta(String matricula) {
        Aluno aluno = getAluno(matricula);
        String curso = aluno.getCurso();

        this.listaRespostasResgitradas.add(aluno);
        aluno.adicionaResposta();
        adicionaRespostaCurso(curso);
    }

    /**
     *
     * Adiciona a nova resposta cadastrada ao curso do aluno chamado.
     *
     * @param nomeCurso    O nome do curso para incremento da quantidade de respostas total.
     */
    private void adicionaRespostaCurso(String nomeCurso){
        if (this.mapaCursoRespostas.containsKey(nomeCurso)){
            mapaCursoRespostas.put(nomeCurso, (mapaCursoRespostas.get(nomeCurso) + 1));
        } else{
            mapaCursoRespostas.put(nomeCurso, 1);
        }
    }

    /**
     * Constrói uma String que lista todas as respostas de alunos conforme a ordem de chamada registrada.
     * A listagem está no formato "Número da Chamada. Representação textual do aluno".
     *
     * @return     A String de listagem dos alunos chamados para resposta.
     */
    public String exibirRespostas() {
        String listaRespostas = "";
        if (this.listaRespostasResgitradas.size() == 0){
            return "SEM RESPOSTAS REGISTRADAS!";
        }

        for (int i = 0; i < this.listaRespostasResgitradas.size(); i++){
            listaRespostas += String.format("%d. %s\n", (i + 1), this.listaRespostasResgitradas.get(i).toString());
        }
        return listaRespostas.trim();
    }

    /**
     * Constrói uma String que lista todos os alunos que registraram o número máximo de chamadas para resposta.
     *
     * @return        A String de listagem dos alunos com maior número de chamada para resposta.
     */
    public String alunosMaisChamados(){
        LinkedHashSet<Aluno> setAlunos = new LinkedHashSet<>();
        for (Aluno aluno : this.listaRespostasResgitradas){
            setAlunos.add(aluno);
        }

        String listaAlunosMaisChamados = "";
        int maior = numeroMaximoRespostas();
        if (maior == 0){
            return listaAlunosMaisChamados;
        }

        for (Aluno aluno : setAlunos) {
            int numeroRespostasAluno = aluno.getNumeroRespostas();
            if (numeroRespostasAluno == maior) {
                listaAlunosMaisChamados += String.format("%s\n", aluno.toString());
            }
        }

        return String.format("Aluno(s) foram chamados %d vezes\n%s", maior, listaAlunosMaisChamados.trim());

    }

    /**
     * Calcula o número máximo de respostas individual de um aluno.
     *
     * @return    O inteiro que representa o maior número de respostas de um aluno.
     */
    private int numeroMaximoRespostas(){
        int maior = 0;
        for (String matricula : this.mapaRepostorioAlunos.keySet()){
            int numeroRespostasAluno = getAluno(matricula).getNumeroRespostas();
            if (numeroRespostasAluno > maior){
                maior = numeroRespostasAluno;
            }
        }
        return maior;
    }

    /**
     * Constrói uma String que lista todos os alunos que não registraram chamada para resposta.
     *
     * @return    A String de listagem dos alunos sem chamadas para resposta.
     */
    public String alunosSemRespostas(){
        String listaAlunosSemRespostas = "";
        for (String matricula : mapaRepostorioAlunos.keySet()) {
            Aluno aluno = getAluno(matricula);
            if (!this.listaRespostasResgitradas.contains(aluno)) {
                listaAlunosSemRespostas += String.format("%s\n", aluno.toString());
            }
        }

        return listaAlunosSemRespostas.trim();
    }

    /**
     * Constrói uma String que lista os cursos, com no mínimo uma resposta registrada.
     * A listagem é feita de modo decrescente, do curso com mais para o com menos registros de resposta.
     *
     * @return   A String de listagem dos cursos
     */
    public String listagemRespostasPorCurso(){
        HashSet<Integer> setQuantidades = new HashSet<Integer>();
        for (Integer quantidade : this.mapaCursoRespostas.values()){
            setQuantidades.add(quantidade);
        }

        Integer[] arrayQuantidades = setQuantidades.toArray(new Integer[setQuantidades.size()]);
        Arrays.sort(arrayQuantidades, Collections.reverseOrder());

        String listaCursos = "";
        for (int i = 0; i < arrayQuantidades.length; i++) {
            for (String curso : mapaCursoRespostas.keySet()) {
                if (mapaCursoRespostas.get(curso) == arrayQuantidades[i]){
                    listaCursos += String.format("%d. %s - %d respostas de alunos\n", (i + 1), curso, arrayQuantidades[i]);
                }
            }
        }

        return listaCursos.trim();
    }


}
