package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes reponsáveis por testar funcionalidades gerais da agenda de listagem de contatos.
 *
 * @author Leticia Ramos Rodrigues
 */
public class AgendaTestListagem {

    // contato inválido = contato com posição fora dos limites estabelecidos, ou seja, menor que 1 ou maior que 100,
    // ou ainda um contato nunca definido antes, ou seja, igual a null.

    private Agenda agenda;

    /**
     * Cria uma nova agenda antes da execução de todos os testes.
     */
    @BeforeEach
    public void preparaAgenda(){
        this.agenda = new Agenda();
    }

    /**
     * Testa a exibição para o usuário de um contato cadastrado.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void exibeContatoExistente(){
        String mensagem = "Exibe um contato existente";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.exibirContato(1);
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Tenta exibir para o usuário um contato inexistente.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void exibeContatoInexistente(){
        try {
            agenda.exibirContato(1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta exibir para o usuário um contato fora das posições limite da agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void exibeContatoInvalido(){
        try {
            agenda.exibirContato(0);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa a exibição para o usuário de um contato favorito.
     * Na formatação "❤ NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void exibeContatoFavoritado(){
        String mensagem = "Exibe um contato favoritado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1,1);
        String resultado = agenda.exibirContato(1);
        assertEquals(resultado, "❤️ Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato desfavoritado.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void exibeContatoDesfavoritado(){
        String mensagem = "Exibe um contato favoritado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1,1);
        agenda.removerFavorito(1);
        String resultado = agenda.exibirContato(1);
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato com tags.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE
     *                TAGS".
     */
    @Test
    public void exibeContatoComTags(){
        String mensagem = "Exibe um contato favoritado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarTags(new String[]{"1"}, "estudante", 1);
        agenda.adicionarTags(new String[]{"1"}, "uepb", 3);
        agenda.adicionarTags(new String[]{"1"}, "direito", 5);
        String resultado = agenda.exibirContato(1);
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\nestudante uepb direito", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato com tags removidas.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void exibeContatoComTagsRemovidas(){
        String mensagem = "Exibe um contato favoritado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarTags(new String[]{"1"}, "estudante", 1);
        agenda.removerTag(1, 1);
        String resultado = agenda.exibirContato(1);
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Testa a exibição dos contatos da lista de contatos.
     * Na formatação de "POSIÇÃO NA AGENDA - NOME SOBRENOME" com um contato por linha,
     */
    @Test
    public void testListaContatos(){
        String mensagem = "Lista todos os contatos da agenda";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(60, "Maria", "Bezerra", "8888-8888");
        String esperado = "1 - Leticia Rodrigues\n60 - Maria Bezerra";
        assertEquals(agenda.listarContatos(), esperado, mensagem);
    }

    /**
     * Testa a exibição dos contatos da lista de contatos vazia.
     * Espera-se como retorno a string "SEM CONTATOS CADASTRADOS".
     */
    @Test
    public void testListaContatosVazia(){
        String mensagem = "Lista todos os contatos da agenda";
        String resultado = agenda.listarContatos();
        assertEquals(resultado, "SEM CONTATOS CADASTRADOS", mensagem);
    }

    /**
     * Testa a exibição dos contatos da lista de favoritos.
     * Na formatação de "POSIÇÃO NA AGENDA - NOME SOBRENOME" com um contato por linha.
     */
    @Test
    public void testListaContatosFavoritos(){
        String mensagem = "Lista todos os contatos favoritos da agenda";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
        agenda.adicionarFavorito(1, 1);
        agenda.adicionarFavorito(2, 4);
        String esperado = "1 - Leticia Rodrigues\n4 - Maria Bezerra";
        assertEquals(agenda.listarContatosFavoritos(), esperado, mensagem);
    }

    /**
     * Testa a exibição dos contatos da lista de favortios vazia.
     * Espera-se como retorno a string "SEM CONTATOS FAVORITADOS".
     */
    @Test
    public void testListaContatosFavoritosVazio(){
        String mensagem = "Lista todos os contatos favoritos";
        String resultado =  agenda.listarContatosFavoritos();
        assertEquals(resultado, "SEM CONTATOS FAVORITADOS", mensagem);
    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de nome.
     * Na formatação de "POSIÇÃO NA AGENDA - NOME SOBRENOME" com um contato por linha.
     */
    @Test
    public void testConsultaNomePositiva(){
        String mensagem = "Lista todos os contatos com o mesmo nome procurado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
        String esperado = "1 - Leticia Rodrigues";
        assertEquals(agenda.consultarNome("Leticia"), esperado, mensagem);
    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de nome.
     * Espera-se como retorno uma string vazia.
     */
    @Test
    public void testConsultaNomeNegativa(){
        String mensagem = "Lista vazia de nomes procurados";
        String esperado = "CONSULTA SEM RESULTADOS";
        assertEquals(agenda.consultarNome("Leticia"), esperado, mensagem);
    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de sobenome.
     * Na formatação de "POSIÇÃO NA AGENDA - NOME SOBRENOME" com um contato por linha.
     */
    @Test
    public void testConsultaSobrenomePositiva(){
        String mensagem = "Lista todos os contatos com o mesmo sobrenome procurado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(5, "Maria", "Bezerra", "8888-8888");
        String esperado = "1 - Leticia Rodrigues";
        assertEquals(agenda.consultarSobrenome("Rodrigues"), esperado, mensagem);
    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de sobrenome.
     * Espera-se como retorno uma string vazia.
     */
    @Test
    public void testConsultaSobrenomeNegativa(){
        String mensagem = "Lista vazia de sobrenomes procurados";
        String esperado = "CONSULTA SEM RESULTADOS";
        assertEquals(agenda.consultarSobrenome("Rodrigues"), esperado, mensagem);

    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de tags.
     * Na formatação de "POSIÇÃO NA AGENDA - NOME SOBRENOME" com um contato por linha.
     */
    @Test
    public void testConsultaTagPositiva(){
        String mensagem = "Lista todos os contatos com a tag procurado";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(4, "Maria", "Bezerra", "8888-8888");
        String[] contato = {"1"};
        agenda.adicionarTags(contato, "estudante", 1);
        String[] contatos = {"1", "4"};
        agenda.adicionarTags(contatos, "computação", 2);
        String esperado = "1 - Leticia Rodrigues\n4 - Maria Bezerra";
        assertEquals(agenda.consultarTag("computação"), esperado, mensagem);

    }

    /**
     * Testa a exibição da lista de contatos coletados durante a consulta de tags.
     * Espera-se como retorno uma string vazia.
     */
    @Test
    public void testConsultaTagNegativa(){
        String mensagem = "Lista todos os contatos com a tag procurado";
        String esperado = "CONSULTA SEM RESULTADOS";
        assertEquals(agenda.consultarTag("computação"), esperado, mensagem);
    }
}
