package ControleAlunos;

/**
 * Interface que controla e realiza operações sob os alunos e os grupos cadastrados no sistema.
 * Essa retorna para o Main as informações sobre a realização das operações em forma de String.
 *
 * @author Leticia Ramos Rodrigues
 */
public class InterfaceControle {
    private AlunosRepository alunosRepository;
    private GruposRepository gruposRepository;

    /**
     * Constrói uma interface de controle.
     * Tem como base os repositórios de alunos e de grupos para uso do sistema.
     */
    public InterfaceControle(){
        this.alunosRepository = new AlunosRepository();
        this.gruposRepository = new GruposRepository();
    }

    /**
     *
     * Verifica a existência de um aluno na interface por meio do critério de identificação, estabelecido como a matrícula do aluno.
     * Retorna "true" se o grupo está cadastrado e "false" se o grupo não está cadastrado na interface
     *
     * @param matricula    A matrícula para a verificação.
     * @return                    O boolean que informa o estado de existência do grupo na interface.
     */
    private boolean verificaExisteAluno(String matricula){
        return this.alunosRepository.existeAluno(matricula);
    }

    /**
     *
     * Verifica a existência de um grupo na interface por meio do critério de identificação, estabelecido como o nome do grupo.
     * Retorna "true" se o grupo está cadastrado e "false" se o grupo não está cadastrado na interface.
     *
     * @param nomeGrupo    O nome do grupo para a verificação.
     * @return             O boolean que informa o estado de existência do grupo na interface.
     */
    private boolean verificaExisteGrupo(String nomeGrupo){
        return this.gruposRepository.existeGrupo(nomeGrupo);
    }

    /**
     * Cadastra um aluno no sistema, por meio do uso da matrícula de identificação,
     * do nome do aluno e do curso, no qual, esse está matriculado.
     * O cadastro não é realizado caso o aluno já exista na interface.
     *
     * @param matricula   A matrícula do aluno.
     * @param nomeAluno   O nome do aluno.
     * @param nomeCurso   O nome do curso em que o aluno está matriculado.
     * @return            A String que informa situação da realização do cadastro.
     */
    public String cadastrarAluno(String matricula, String nomeAluno, String nomeCurso) {
        if (verificaExisteAluno(matricula)){
            return "ALUNO JÁ CADASTRADO!";
        }
        this.alunosRepository.cadastrar(matricula, nomeAluno, nomeCurso);
        return "CADASTRO REALIZADO!";
    }

    /**
     *
     * Cadastra um grupo no sistema, por meio do uso do nome do grupo.
     * O grupo é cadastrado somente com o nome de identificação e sem o limite de tamanho definido.
     * O cadastro não é realizado caso o grupo já exista na interface.
     *
     * @param nomeGrupo    O nome do grupo para cadastro.
     * @return             A String que informa situação da realização do cadastro.
     */
    public String cadastrarGrupo(String nomeGrupo) {
        if (verificaExisteGrupo(nomeGrupo)){
            return "GRUPO JÁ CADASTRADO!";
        }

        this.gruposRepository.cadastrar(nomeGrupo);
        return "CADASTRO REALIZADO!";
    }

    /**
     * Cadastra um grupo no sistema, por meio do uso do nome e do tamanho do grupo.
     * O grupo é cadastrado com, ambos, o nome de identificação e o limite de tamanho definido.
     * O cadastro não é realizado caso o grupo já exista na interface.
     *
     * @param nomeGrupo       O nome do grupo para cadastro.
     * @param tamanhoGrupo    A quantidade máxima definido para o grupo.
     * @return                A String que informa situação da realização do cadastro.
     */
    public String cadastrarGrupo(String nomeGrupo, int tamanhoGrupo) {
        if (verificaExisteGrupo(nomeGrupo)){
            return "GRUPO JÁ CADASTRADO!";
        }
        this.gruposRepository.cadastrar(nomeGrupo, tamanhoGrupo);
        return "CADASTRO REALIZADO!";
    }

    /**
     * Consulta o estado de existência de um aluno no repositório de alunos.
     *
     * @param matricula   A matrícula para a consulta.
     * @return            A String que informa o estado de existência do aluno no sistema.
     */
    public String consultarAluno(String matricula) {
        if (!verificaExisteAluno(matricula)){
            return "ALUNO NÃO CADASTRADO.";
        }
        return "Aluno: " + this.alunosRepository.consultar(matricula);
    }

    /**
     * Aloca um aluno existente no sistema em um grupo cadastrado no sistema.
     * Caso o aluno ou o grupo não exista na interface a alocação não é realizada.
     *
     * @param matricula    A matrícula do aluno que vai ser alocado.
     * @param nomeGrupo    O nome do grupo especificado para a alocação.
     * @return             A String que informa situação da realização da alocação.
     */
    public String alocarAlunoEmGrupo(String matricula, String nomeGrupo) {
        if (!verificaExisteAluno(matricula)){
            return "ALUNO NÃO CADASTRADO.";
        }

        if (!verificaExisteGrupo(nomeGrupo)){
            return "GRUPO NÃO CADASTRADO.";
        }

        if (this.gruposRepository.alocarAluno(matricula, nomeGrupo)){
            return "ALUNO ALOCADO.";
        }

        return "GRUPO CHEIO!";
    }

    /**
     * Verifica a existência de um aluno no grupo alvo especificado.
     * Caso o aluno ou o grupo não existam na interface a verificação não é realizada.
     *
     * @param nomeGrupo   O nome do grupo alvo para a verificação.
     * @param matricula   A matrícula do aluno para verificação de existência no grupo.
     * @return            A String que informa o estado de existência do aluno no grupo especificado.
     */
    public String verificarExistencia(String nomeGrupo, String matricula) {
        if (!verificaExisteAluno(matricula)){
            return "ALUNO NÃO CADASTRADO.";
        }

        if (!verificaExisteGrupo(nomeGrupo)){
            return "GRUPO NÃO CADASTRADO.";
        }

        if (this.gruposRepository.verificarExistencia(matricula, nomeGrupo)){
            return "ALUNO PERTENCE AO GRUPO!";
        }

        return "ALUNO NÃO PERTENCE AO GRUPO!";
    }

    /**
     *
     * Retorna as representações textuais dos grupos em que o aluno está alocado.
     *
     * @param matricula     A matrícula do aluno para procura de existência em grupos.
     * @return              A String que informa o usuário sobre os grupos em que o aluno está alocado.
     */
    public String exibirGruposDoAluno(String matricula) {
        String retorno = this.gruposRepository.exibirGruposDoAluno(matricula);
        if (retorno.isBlank()){
            return "ALUNO NÃO FAZ PARTE DE GRUPOS!";
        }
        return "Grupos:\n" + retorno;
    }

    /**
     * Registra uma resposta de um aluno no quadro.
     * Caso o aluno não exista na interface a resposta não é registada.
     *
     * @param matricula    A matrícula do aluno para registro da resposta
     * @return             A String que informa situação da realização registro de resposta.
     */
    public String registrarResposta(String matricula) {
        if (!verificaExisteAluno(matricula)){
            return "ALUNO NÃO CADASTRADO.";
        }

        this.alunosRepository.registrarResposta(matricula);
        return "ALUNO CADASTRADO!";
    }

    /**
     * Retorna as representações textuais dos alunos que responderam no quadro.
     * As representações textuais estão ordenadas conforme a ordem de registro.
     *
     * @return  A String que informa o usuário sobre os alunos que responderam no quadro.
     */
    public String exibirRespostas(){
        String retorno = this.alunosRepository.exibirRespostas();
        if (retorno.isBlank()){
            return "SEM RESPOSTAS REGISTRADAS!";

        }
        return "Alunos:\n" + retorno;
    }

    /**
     * Retorna as representações textuais dos alunos cadastrados com maior número de respostas registradas.
     *
     * @return    A String que informa o usuário sobre os alunos mais chamados para responder no quadro.
     */
    public String estatisticaAlunosMaisChamados(){
        String retorno = this.alunosRepository.alunosMaisChamados();
        if (retorno.isBlank()){
            return "NENHUM ALUNO FOI CHAMADO!";
        }
        return String.format("Alunos Mais Chamados:\n%s", retorno);
    }

    /**
     * Retorna representações textuais dos alunos cadastrados sem respostas registradas.
     *
     * @return    A String que informa o usuário sobre os alunos não chamados para responder no quadro.
     */
    public String estatisticaAlunosNaoChamados(){
        String retorno = this.alunosRepository.alunosSemRespostas();
        if (retorno.isBlank()){
            return "TODOS OS ALUNOS FORAM CHAMADOS!";
        }
        return String.format("Alunos Não Chamados:\n%s", retorno);
    }

    /**
     * Retorna as representações textuais dos cursos cadastrados, ordenadas do mais ao menos chamado para responder.
     * Os cursos estão listados de forma decrescente em quesitos de quantidade de respostas registadas.
     * Apenas grupos com pelo menos um registro de resposta estão na listagem.
     *
     * @return     A String que informa os cursos segundo o número de respostas registradas.
     */
    public String estatisticaRespostasPorCurso(){
        String retorno = this.alunosRepository.listagemRespostasPorCurso();
        if (retorno.isBlank()){
            return "NENHUM CURSO FOI CHAMADO!";
        }
        return String.format("Listagem de Cursos:\n%s", retorno);
    }

}
