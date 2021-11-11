package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus de texto para manipular uma agenda de contatos.
 *
 * @author Nazareno Andrade
 * @author Leticia Ramos Rodrigues
 *
 */
public class Menu {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        System.out.println("Carregando agenda inicial");
        try {
            carregaAgenda("agenda_inicial.csv", agenda);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }


        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, agenda, scanner);
        }

    }

    /**
     * Exibe o menu da interface e captura a escolha de operação do usuário.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                "\nMENU\n" +
                        "----\n" +
                        "(C) Cadastrar Contato\n" +
                        "(AT) Alterar Telefone\n" +
                        "(RC) Remover Contato\n" +
                        "(E) Exibir Contato\n" +
                        "(L) Listar Contatos\n" +
                        "(A) Adicionar Favorito\n" +
                        "(RF) Remover Favorito\n" +
                        "(F) Listar Favoritos\n" +
                        "(T) Adicionar Tag(s)\n" +
                        "(RT) Remover Tag\n" +
                        "(CA) Consultar Agenda\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
        return scanner.nextLine().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param agenda  A agenda para manipulação.
     * @param scanner Objeto scanner para a entrada de dados, se necessária.
     */
    private static void comando(String opcao, Agenda agenda, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraContato(agenda, scanner);
                break;
            case "AT":
                alteraTelefone(agenda, scanner);
                break;
            case "RC":
                removeContato(agenda, scanner);
                break;
            case "E":
                exibeContato(agenda, scanner);
                break;
            case "L":
                listaContatos(agenda);
                break;
            case "A":
                adicionaContatoFavorito(agenda, scanner);
                break;
            case "RF":
                removeFavorito(agenda, scanner);
                break;
            case "F":
                listaContatosFavoritos(agenda);
                break;
            case "T":
                adicionaTag(agenda, scanner);
                break;
            case "RT":
                removeTag(agenda, scanner);
                break;
            case "CA":
                consultaAgenda(agenda, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }


    /**
     *
     * Cadastra as informações do contato na posição especifficada da agenda.
     * O contato é criado com os dados nome, sobrenome e telefone.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void cadastraContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nPosição na agenda> ");

        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }

        System.out.print("\nNome> ");
        String nome = scanner.nextLine();
        if (nome.isEmpty()){
            System.out.print("CONTATO INVÁLIDO\n");
            return;
        }

        System.out.print("\nSobrenome> ");
        String sobrenome = scanner.nextLine();

        System.out.print("\nTelefone> ");
        String telefone = scanner.nextLine();
        if (telefone.isEmpty()){
            System.out.print("CONTATO INVÁLIDO\n");
            return;
        }

        if (agenda.existeContato(nome + sobrenome)){
            System.out.print("CONTATO JÁ CADASTRADO\n");
            return;
        }
        String resultado = agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Altera o telefone do contato cadastrado na posição especificada.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
     private static void alteraTelefone(Agenda agenda, Scanner scanner) {
        System.out.print("\nContato> ");
        int posicao = Integer.parseInt(scanner.nextLine());
         if (posicao < 1 || posicao > 100) {
             System.out.print("POSIÇÃO INVÁLIDA\n");
             return;
         }
        System.out.print("\nNovo Telefone> ");
        String novoTelefone = scanner.nextLine();
        String resultado = agenda.alterarTelefone(posicao, novoTelefone);
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Remove o contato cadastrado na posição especificada.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void removeContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nContato> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        String resultado = agenda.removerContato(posicao);
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Exibe o contato cadastrado na posição especificada.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void exibeContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nContato> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        System.out.print("\nDados do contato:\n");
        String resultado = agenda.exibirContato(posicao);
        System.out.print(resultado + "\n");

    }

    /**
     *
     * Lista todos os contatos existentes na agenda.
     * Cada contato é exibido em uma linha no formato "POSIÇÃO NA AGENDA - NOME SOBRENOME".
     *
     * @param agenda A agenda para manipulação.
     */
    private static void listaContatos(Agenda agenda) {
        System.out.print("\nLista de contatos:\n");
        System.out.print(agenda.listarContatos() + "\n");
    }

    /**
     * Adiciona um contato à categoria de favoritos na posição especificada.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void adicionaContatoFavorito(Agenda agenda, Scanner scanner){
        System.out.print("\nContato> ");
        int contato = Integer.parseInt(scanner.nextLine());
        if (contato < 1 || contato > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        System.out.print("\nPosição> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 10) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        String resultado = agenda.adicionarFavorito(contato, posicao);
        System.out.print(resultado + "\n" );
    }

    /**
     * Remove o contato da categoria de favoritos na posição especificada..
     * O contato permanece na agenda.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void removeFavorito(Agenda agenda, Scanner scanner) {
        System.out.print("\nFavorito> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        String resultado = agenda.removerFavorito(posicao);
        System.out.print(resultado + "\n");
    }

    /**
     *
     * Lista todos os contatos favoritos da agenda.
     * Cada contato é exibido em uma linha no formato "POSIÇÃO NOS FAVORITOS - NOME SOBRENOME".
     *
     * @param agenda A agenda para manipulação.
     */
    private static void listaContatosFavoritos(Agenda agenda){
        System.out.print("\nLista de contatos favoritos:\n");
        System.out.print(agenda.listarContatosFavoritos() + "\n");
    }

    /**
     * Adiciona a tag especificada aos contatos selecionados.
     * Apenas uma tag é inserida à vários contatos.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void adicionaTag(Agenda agenda, Scanner scanner){
        System.out.print("\nContato (s)> ");
        String contato = scanner.nextLine();
        String[] arrayNumeroContatos = contato.split(" ");
        for (String numero : arrayNumeroContatos){
            int numeroInt = Integer.parseInt(numero);
            if (numeroInt < 1 || numeroInt > 100){
                System.out.print("POSIÇÃO INVÁLIDA\n");
                return;
            }
        }
        System.out.print("\nTag> ");
        String tag = scanner.nextLine();
        System.out.print("\nPosição> ");
        int posicao = Integer.parseInt(scanner.nextLine());
        if (posicao < 1 || posicao > 5) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        String resultado = agenda.adicionarTags(arrayNumeroContatos, tag, posicao);
        System.out.print(resultado + "\n");
    }

    /**
     * Remove a tag especificada do contato selecionado.
     * Apenas uma tag é removida de um contato por vez.
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void removeTag(Agenda agenda, Scanner scanner) {
        System.out.print("\nContato> ");
        int posicaoContato = Integer.parseInt(scanner.nextLine());
        if (posicaoContato < 1 || posicaoContato > 100) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        System.out.print("\nPosição Tag> ");
        int posicaoTag = Integer.parseInt(scanner.nextLine());
        if (posicaoTag < 1 || posicaoTag > 5) {
            System.out.print("POSIÇÃO INVÁLIDA\n");
            return;
        }
        String resultado = agenda.removerTag(posicaoContato, posicaoTag);
        System.out.println(resultado + "\n");

    }

    /**
     * Consulta a agenda por meio de um parâmetro de busca especificado.
     * Imprime uma lista com todos os contatos que obedecem a especificação.
     * A lista está no formato "POSICAO NA AGENDA - NOME SOBRENOME".
     *
     * @param agenda   A agenda para manipulação.
     * @param scanner  O objeto scanner para a entrada de dados.
     */
    private static void consultaAgenda(Agenda agenda, Scanner scanner) {
        System.out.print("\nOpcões de consulta: \nNOME\nSOBRENOME\nTAG\n");
        System.out.print("\nModo de consulta> ");
        String modo = scanner.nextLine().toUpperCase();
        System.out.print("\nAlvo da consulta> ");
        String target = scanner.nextLine();
        if (target == null){
            throw new NullPointerException("PESQUISA IMPOSSÍVEL!");
        }
        if (modo.equals("NOME")){
            System.out.print("\nPesquisa: " + target + "\n");
            System.out.print("\nLista de contatos:\n");
            System.out.print(agenda.consultarNome(target) + "\n");
        }
        else if (modo.equals("SOBRENOME")){
            System.out.print("\nPesquisa: " + target + "\n");
            System.out.print("\nLista de contatos:\n");
            System.out.print(agenda.consultarSobrenome(target) + "\n");
        }
        else if (modo.equals("TAG")){
            System.out.print("\nPesquisa: " + target + "\n");
            System.out.print("\nLista de contatos:\n");
            System.out.print(agenda.consultarTag(target) + "\n");
        }
        else{
            System.out.print("CONSULTA INVÁLIDA");
        }
    }

    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\n Fim da execução.");
        System.exit(0);
    }

    /**
     * Lê uma agenda de um arquivo csv.
     *
     * @param arquivoContatos O caminho para o arquivo.
     * @param agenda A agenda que deve ser populada com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
        LeitorDeAgenda leitor = new LeitorDeAgenda();

        int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
        System.out.println("Carregamos " + carregados + " registros.");
    }


}
