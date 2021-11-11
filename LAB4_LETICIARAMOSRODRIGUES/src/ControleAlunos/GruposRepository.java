package ControleAlunos;

import java.util.LinkedHashMap;

/**
 * Repositório de grupos responsável por manipular os dados e as operações realizadas sob os grupos cadastrados.
 *
 * @author Leticia Ramos Rodrigues
 */
public class GruposRepository {
    private LinkedHashMap<String, Grupo> mapaRepositorioGrupos;

    /**
     * Constrói um repositório de grupos. Mapeia as chaves e os valores no formato "Nome do Grupo: Objeto grupo".
     */
    public GruposRepository(){
        this.mapaRepositorioGrupos = new LinkedHashMap<String, Grupo>();
    }

    /**
     * Retorna o grupo cadastrado a partir do nome como critério de identificação.
     *
     * @param nomeGrupo   O nome do grupo para busca.
     * @return            O objeto grupo associado a chave com o nome do grupo.
     */
    public Grupo getGrupo(String nomeGrupo) {
        return this.mapaRepositorioGrupos.get(nomeGrupo.toUpperCase());
    }

    /**
     * Cadastra um novo grupo no repositório de grupos, identificado pela chave correspondente ao nome do grupo.
     * O grupo não tem tamanho definido, bem como, desejado pelo usuário.
     *
     * @param nomeGrupo    O nome do grupo para cadastro.
     */
    public void cadastrar(String nomeGrupo){
        this.mapaRepositorioGrupos.put(nomeGrupo.toUpperCase(), new Grupo(nomeGrupo));
    }

    /**
     * Cadastra um novo grupo no repositório de grupos, identificado pela chave correspondente ao nome do grupo.
     * O grupo tem tamanho definido, bem como, desejado pelo usuário.
     *
     * @param nomeGrupo        O nome do grupo para cadastro.
     * @param tamanhoGrupo     O limite máximo de pessoas pertencentes ao grupo.
     */
    public void cadastrar(String nomeGrupo, int tamanhoGrupo) {
        this.mapaRepositorioGrupos.put(nomeGrupo.toUpperCase(), new Grupo(nomeGrupo, tamanhoGrupo));
    }

    /**
     * Aloca um aluno no grupo especificado.
     * Retorna "true" se o alocamento é realizado e "false" o alocamento não é realizado.
     *
     * @param matriculaAluno   A matrícula de identificação do aluno.
     * @param nomeGrupo        O nome do grupo em que o aluno deve ser alocado.
     * @return                 O boolean que presenta o estado da realização da alocação do aluno no grupo.
     */
    public boolean alocarAluno(String matriculaAluno, String nomeGrupo) {
        Grupo grupo = getGrupo(nomeGrupo);
        if (grupo.getTamanhoDefinido()){
            return alocarGrupoTamanhoDefinido(matriculaAluno, grupo);
        }
        else{
            grupo.adicionaAluno(matriculaAluno);
            return true;
        }
    }

    /**
     * Aloca um aluno no grupo especificado caso o limite de tamanho ainda não foi atingido.
     * Retorna "true" se o alocamento é realizado e "false" o alocamento não é realizado.
     *
     * @param matriculaAluno  A matrícula de identificação do aluno.
     * @param grupo           O grupo em que o aluno deve ser alocado.
     * @return                O boolean que presenta o estado da realização da alocação do aluno no grupo.
     */
    private boolean alocarGrupoTamanhoDefinido(String matriculaAluno, Grupo grupo) {
        if(grupo.getGrupoAlunos().contains(matriculaAluno)){
            return true;
        }
        if(grupo.getGrupoAlunos().size() == grupo.getNumeroPessoas()){
            return false;
        }
        grupo.adicionaAluno(matriculaAluno);
        return true;
    }


    /**
     *
     * Verifica a existência de um aluno específico no grupo.
     * Retorna "true" se o aluno está alocado no grupo e "false" se o aluno não está alocado no grupo.
     *
     * @param matriculaAluno    A matrícula de identificação do aluno.
     * @return                  O boolean que informa o estado de existência do aluno no grupo.
     */
    public boolean verificarExistencia(String matriculaAluno, String nomeGrupo) {
        Grupo grupo = getGrupo(nomeGrupo);
        if(grupo.getGrupoAlunos().contains(matriculaAluno)){
            return true;
        }
        return false;
    }

    /**
     * Constrói uma String que contém as representações textuais dos grupos em que aluno pertence.
     *
     * @param matriculaAluno    A matrícula do aluno para consulta.
     * @return                  A String de listagem dos grupos do aluno.
     */
    public String exibirGruposDoAluno(String matriculaAluno){
        String listaGrupos = "";
        for (String grupoNome: this.mapaRepositorioGrupos.keySet()){
            Grupo grupo = getGrupo(grupoNome);
            if (grupo.getGrupoAlunos().contains(matriculaAluno)){
                listaGrupos += String.format("- %s\n", grupo.toString());
            }
        }

        return listaGrupos.trim();
    }

    /**
     *
     * Verifica a existência de um grupo no repositório por meio do critério de identificação, estabelecido como o nome do grupo.
     * Retorna "true" se o grupo está cadastrado e "false" se o grupo não está cadastrado no repositório.
     *
     * @param nomeGrupo    O nome do grupo para a verificação.
     * @return             O boolean que informa o estado de existência do grupo no repositório.
     */
    public boolean existeGrupo(String nomeGrupo) {
        if (this.mapaRepositorioGrupos.keySet().contains(nomeGrupo.toUpperCase())) {
            return true;
        }
        return false;
    }

}
