package ControleAlunos;

import java.util.Scanner;

/**
 * Main responsável por exibir o menu e manipular as informações inseridas pelo usuário,
 * de modo a realizar a operação escolhida.
 *
 * @author Leticia Ramos Rodrigues.
 */
public class Main {

    public static void main(String[] args) {

        InterfaceControle interfaceControle = new InterfaceControle();

        Scanner scanner = new Scanner(System.in);
        String escolha;
        while (true) {
            escolha = menu(scanner);
            comando(escolha, interfaceControle, scanner);
        }

    }

    /**
     * Exibe o menu da interface e captura a escolha de operação do usuário.
     *
     * @param scanner Para captura da opção do usuário.
     * @return        O comando escolhido para operação dos dados.
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                        "\n" +
                        "(C)adastrar Aluno\n" +
                        "(E)xibir Aluno\n" +
                        "(N)ovo Grupo\n" +
                        "(A)locar Aluno no Grupo e Verificar pertinência a Grupos\n" +
                        "(R)egistrar Aluno que Respondeu\n" +
                        "(I)mprimir Alunos que Responderam\n" +
                        "(O)lhaí quais Grupos o Aluno Tá\n" +
                        "(D)ados Estátisticos\n" +
                        "(S)im, quero Fechar o Programa!" +
                        "\n" +
                        "Opção: ");
        return scanner.nextLine().toUpperCase();
    }


    /**
     * Interpreta a opção escolhida pelo usuário do sistema.
     * Quando a operação é identificada a Interface de Controle tenta realizar essa.
     * Em caso de opção inválida imprime uma mensagem informando o usuário.
     *
     * @param opcao                A opção digitada indicativa da operação desejada.
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados, se necessária.
     */
    private static void comando(String opcao,
                                InterfaceControle interfaceControle,
                                Scanner scanner) {
        switch (opcao) {
            case "C" -> cadastraAluno(interfaceControle, scanner);
            case "E" -> consultaAluno(interfaceControle, scanner);
            case "N" -> cadastraGrupo(interfaceControle, scanner);
            case "A" -> manipulaGrupo(interfaceControle, scanner);
            case "R" -> registraResposta(interfaceControle, scanner);
            case "I" -> exibeRespostas(interfaceControle);
            case "O" -> exibeGruposDoAluno(interfaceControle, scanner);
            case "D" -> exibirDadosEstatisticos(interfaceControle, scanner);
            case "S" -> encerra();
            default -> System.out.println("Opção inválida!");
        }
    }

    /**
     *
     * Cadastra um aluno na Interface de Controle.
     * Imprime no terminal a informação sobre a situação do cadastrado,
     * indica a rejeição de dados já cadastrados ou o cadastrado realizado efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void cadastraAluno(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nMatrícula: ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        System.out.print("Nome: ");
        String nomeAluno = scanner.nextLine();
        if (nomeAluno == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (nomeAluno.isEmpty() || nomeAluno.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        System.out.print("Curso: ");
        String curso = scanner.nextLine();
        if (curso == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (curso.isEmpty() || curso.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        String resultado = interfaceControle.cadastrarAluno(matricula, nomeAluno, curso);
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Cadastra um grupo na Interface de Controle.
     * Imprime no terminal a informação sobre a situação do cadastrado,
     * indica a rejeição de dados já cadastrados ou o cadastrado realizado efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void cadastraGrupo(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.nextLine();
        if (nomeGrupo == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (nomeGrupo.isEmpty() || nomeGrupo.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        System.out.print("Tamanho: ");
        String strTamanho = scanner.nextLine();
        if (strTamanho == null || strTamanho.isEmpty() || strTamanho.isBlank()){
            String resultado = interfaceControle.cadastrarGrupo(nomeGrupo);
            System.out.print(resultado+ "\n");
        }
        else{
            int intTamanho = Integer.parseInt(strTamanho);
            String resultado = interfaceControle.cadastrarGrupo(nomeGrupo, intTamanho);
            System.out.print(resultado + "\n");
        }

    }

    /**
     *
     * Consulta a existência de um aluno específico na interface de controle.
     * Imprime no terminal a informação sobre a situação do cadastrado,
     * indica a rejeição de dados inválidos ou a consulta realizado efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void consultaAluno(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nMatrícula: ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        System.out.print(interfaceControle.consultarAluno(matricula) + "\n");
    }

    /**
     *
     * Recebe a operação a ser realizada sob um grupo específico na interface de controle.
     * Em caso de opção inválida imprime no terminal uma mensagem informando o usuário.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void manipulaGrupo(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\n(A)locar Aluno ou (P)ertinência a Grupo?: ");
        String opcao = scanner.nextLine().toUpperCase();
        if (opcao == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (opcao.isEmpty() || opcao.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }
        switch (opcao) {
            case "A" -> alocaAluno(interfaceControle, scanner);
            case "P" -> verificaExistencia(interfaceControle, scanner);
            default -> System.out.println("Opção inválida!");
        }
    }

    /**
     *
     * Aloca o aluno especificado em um grupo escolhido.
     * Imprime no terminal a informação sobre a situação do alocação,
     * indica a rejeição de dados inválidos ou a alocação realizada efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void alocaAluno(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nMatrícula: ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }
        System.out.print("Grupo: ");
        String nomeGrupo = scanner.nextLine();
        if (nomeGrupo == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (nomeGrupo.isEmpty() || nomeGrupo.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        String resultado = interfaceControle.alocarAlunoEmGrupo(matricula, nomeGrupo);
        System.out.print(resultado+ "\n");
    }

    /**
     *
     * Verifica a existência de um aluno em um grupo específico.
     * Imprime no terminal a informação sobre a situação da verificação,
     * indica a rejeição de dados inválidos ou a verificação realizada efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void verificaExistencia(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nGrupo: ");
        String nomeGrupo = scanner.nextLine();
        if (nomeGrupo == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (nomeGrupo.isEmpty() || nomeGrupo.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }
        System.out.print("Aluno (Matrícula): ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        String resultado = interfaceControle.verificarExistencia(nomeGrupo, matricula);
        System.out.print(resultado+ "\n");
    }

    /**
     *
     * Exibe uma lista com os grupos em que um aluno especificado está alocado.
     * Imprime no terminal a informação sobre a situação do cadastrado,
     * indica a rejeição de dados inválidos ou o cadastrado realizado efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void exibeGruposDoAluno(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nAluno (Matrícula): ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        String resultado = interfaceControle.exibirGruposDoAluno(matricula);
        System.out.print(resultado+ "\n");
    }

    /**
     *
     * Resgitra a reposta de um aluno específico na interface de controle.
     * Imprime no terminal a informação sobre a situação do cadastrado,
     * indica a rejeição de dados inválidos ou o cadastrado realizado efetivamente.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void registraResposta(InterfaceControle interfaceControle, Scanner scanner){
        System.out.print("\nMatrícula: ");
        String matricula = scanner.nextLine();
        if (matricula == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (matricula.isEmpty() || matricula.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }

        String resultado = interfaceControle.registrarResposta(matricula);
        System.out.print(resultado+ "\n");

    }

    /**
     *
     * Exibe os grupos em que um aluno especificado está alocado.
     * Se o aluno está alocado em nenhum grupo, imprime a mensagem indicativa dessa situação.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     */
    public static void exibeRespostas(InterfaceControle interfaceControle){
        String resultado = interfaceControle.exibirRespostas();
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Recebe a operação a ser realizada para imprimir informações da interface de controle.
     * Em caso de opção inválida imprime uma mensagem informando o usuário.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     * @param scanner              Objeto scanner para a entrada de dados.
     */
    private static void exibirDadosEstatisticos(InterfaceControle interfaceControle, Scanner scanner) {
        System.out.print("\n(M)ais Chamado (Alunos)\n" +
                         "(S)em Chamada (Alunos)\n" +
                         "(L)ista de Cursos Chamados");
        String opcao = scanner.nextLine().toUpperCase();
        if (opcao == null){
            throw new NullPointerException("ENTRADA NULA");
        }
        else if (opcao.isEmpty() || opcao.isBlank()){
            throw new IllegalArgumentException("ENTRADA INVÁLIDA");
        }
        switch (opcao) {
            case "M" -> estatisticaAlunosMaisChamados(interfaceControle);
            case "S" -> estatisticaAlunosNaoChamados(interfaceControle);
            case "L" -> estatisticaQuantidadeRespostasPorCurso(interfaceControle);
            default -> System.out.println("Opção inválida!");
        }
    }

    /**
     *
     * Exibe os alunos que tem o maior número de respostas cadastradas.
     * Se não existir cadastro de resposta de alunos, imprime a mensagem indicativa dessa situação.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     */
    private static void estatisticaAlunosMaisChamados(InterfaceControle interfaceControle){
        String resultado = interfaceControle.estatisticaAlunosMaisChamados();
        System.out.print("\n" + resultado + "\n");
    }

    /**
     *
     * Exibe os alunos que não tem alguma resposta cadastrada.
     * Se não existir aluno sem cadastro de resposta, imprime a mensagem indicativa dessa situação.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     */
    private static void estatisticaAlunosNaoChamados(InterfaceControle interfaceControle){
        String resultado = interfaceControle.estatisticaAlunosNaoChamados();
        System.out.print("\n" + resultado + "\n");
    }

    /**
     *
     * Exibe os cursos cadastrados no sistema ordenada em quesitos do número de respostas cadastradas pelos alunos pertencentes.
     * Cursos sem cadastros de resposta não entram na listagem.
     * Se não existem cursos com respostas registradas, imprime a mensagem indicativa dessa situação.
     *
     * @param interfaceControle    A interface responsável por realizar a operação.
     */
    private static void estatisticaQuantidadeRespostasPorCurso(InterfaceControle interfaceControle){
        String resultado = interfaceControle.estatisticaRespostasPorCurso();
        System.out.print("\n" + resultado + "\n");
    }

    /**
     * Encerra a aplicação.
     */
    public static void encerra(){
        System.out.println("\n Fim da execução.");
        System.exit(0);
    }

}
