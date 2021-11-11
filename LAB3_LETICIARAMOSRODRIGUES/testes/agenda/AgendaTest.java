package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes reponsáveis por testar funcionalidades gerais da agenda de manipulação de contatos.
 *
 * @author Leticia Ramos Rodrigues
 */
class AgendaTest {

    // contato inválido = contato com posição fora dos limites estabelecidos, ou seja, menor que 1 ou maior que 100,
    // ou ainda um contato nunca definido antes, ou seja, igual a null.

    private Agenda agenda;

    /**
     * Cria uma nova agenda antes da execução de todos os testes.
     */
    @BeforeEach
    void preparaAgenda() {
        this.agenda = new Agenda();
    }

    /**
     * Testa construir uma agenda.
     */
    @Test
    public void testConstrutor(){
        Agenda agendinha = new Agenda();
    }

    /**
     * Testa cadastrar um contato na posição limite 1 da agenda.
     * Espera-se como retorno a string "CADASTRO REALIZADO".
     */
    @Test
    public void testCadastraContatoPosicaoUm() {
        String mensagem = "Cadastra o contatoTest na posição 1";
        String resultado = agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        assertEquals(resultado, "CADASTRO REALIZADO", mensagem);
    }

    /**
     * Testa cadastrar um contato na posição limite 100 da agenda.
     * Espera-se como retorno a string "CADASTRO REALIZADO".
     */
    @Test
    public void testCadastraContatoPosicaoCem() {
        String mensagem = "Cadastra o contatoTest na posição 100";
        String resultado = agenda.cadastraContato(100, "Leticia", "Rodrigues", "9999-9999");
        assertEquals(resultado, "CADASTRO REALIZADO", mensagem);
    }

    /**
     * Testa cadastrar um contato sobre outro contato cadastrado anteiormente.
     * Espera-se como retorno a string "CADASTRO REALIZADO".
     */
    @Test
    public void testCadastraContatoSobrescreve() {
        String mensagem = "Cadastra outro contato na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.cadastraContato(1, "Maria", "Bezerra", "8888-8888");
        assertEquals(resultado, "CADASTRO REALIZADO", mensagem);
    }


    /**
     * Tenta cadastrar um contato já cadastrado na mesma posição.
     * Espera-se como retorno a string "CONTATO JÁ CADASTRADO".
     */
    @Test
    public void testCadastraMesmoContatoPosicaoIgual() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            fail("CONTATO JÁ CADASTRADO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato já cadastrado em uma outra posição.
     * Espera-se como retorno a string "CONTATO JÁ CADASTRADO".
     */
    @Test
    public void testCadastraMesmoContatoPosicaoDiferente() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.cadastraContato(4, "Leticia", "Rodrigues", "9999-9999");
            fail("CONTATO JÁ CADASTRADO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato na posição 0 fora do limite da agenda.
     * Espera-se como retorno a string "POSIÇÃO DE CONTATO INVÁLIDA".
     */
    @Test
    public void testCadastraPosicaoZero() {
        try {
            agenda.cadastraContato(0, "Leticia", "Rodrigues", "9999-9999");
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato na posição 101 fora do limite da agenda.
     * Espera-se como retorno a string "POSIÇÃO DE CONTATO INVÁLIDA".
     */
    @Test
    public void testCadastraContatoPosicaoCentoUm() {
        try {
            agenda.cadastraContato(101, "Leticia", "Rodrigues", "9999-9999");
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato com o nome vazio.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testCadastraContatoNomeVazio() {
        try {
            agenda.cadastraContato(1, "", "Rodrigues", "9999-9999");
            fail("NOME VAZIO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato com o nome em branco.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testCadastraContatoNomeBranco() {
        try {
            agenda.cadastraContato(1, "   ", "Rodrigues", "9999-9999");
            fail("NOME EM BRANCO");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Tenta cadastrar um contato com o telefone vazio.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testCadastraContatoTelefone() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "");
            fail("TELEFONE VAZIO");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa alterar o telefone de um contato cadastrado na agenda.
     * Espera-se como retorno a string "O TELEFONE DO CONTATO FOI ALTERADO".
     */
    @Test
    public void testAlteraTelefone() {
        String mensagem = "Altera o telefone de um contato existente";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.alterarTelefone(1, "1111-1111");
        assertEquals(agenda.getContato(1).getTelefone(), "1111-1111", mensagem);
        assertEquals(resultado, "O TELEFONE DO CONTATO FOI ALTERADO", mensagem);
    }

    /**
     * Tenta alterar o telefone de um contato inexistente na agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testAlteraTelefoneInexistente() {
        try {
            agenda.alterarTelefone(1, "1111-1111");
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta alterar o telefone de um contato fora das posições limite da agenda.
     * Espera-se como retorno a string "POSIÇÃO DE CONTATO INVÁLIDA".
     */
    @Test
    public void testAlteraTelefoneInvalido() {
        try {
            agenda.alterarTelefone(0, "1111-1111");
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa remover um contato cadastrado na agenda.
     * Espera-se como retorno a string "CONTATO REMOVIDO".
     */
    @Test
    public void testRemoveContato() {
        String mensagem = "Remove um contato existente";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.removerContato(1);
        assertEquals(resultado, "CONTATO REMOVIDO", mensagem);
    }

    /**
     * Tenta remover um contato inexistente na agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testRemoveContatoInexistente() {
        try {
            agenda.removerContato(1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remover um contato fora das posições limite da agenda.
     * Espera-se como retorno a string "POSIÇÃO DE CONTATO INVÁLIDA".
     */
    @Test
    public void testRemoveContatoInvalido() {
        try {
            agenda.removerContato(0);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa adicionar um contato nos favoritos na posição limite 1.
     * Espera-se como retorno a string "CONTATO FAVORITADO NA POSIÇÃO 1!".
     */
    @Test
    public void testAdicionaFavoritoUm() {
        String mensagem = "Favorita um contato na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.adicionarFavorito(1, 1);
        assertEquals(resultado, "CONTATO FAVORITADO NA POSIÇÃO 1!", mensagem);
    }

    /**
     * Testa adicionar um contato nos favoritos na posição limite 10.
     * Espera-se como retorno a string "CONTATO FAVORITADO NA POSIÇÃO 10!".
     */
    @Test
    public void testAdicionaFavoritoDez() {
        String mensagem = "Favorita um contato na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String resultado = agenda.adicionarFavorito(1, 10);
        assertEquals(resultado, "CONTATO FAVORITADO NA POSIÇÃO 10!", mensagem);
    }

    /**
     * Testa alterar o contato favoritado na posição 1, sobrevendo-o com outro.
     * Espera-se como retorno a string "CONTATO FAVORITADO NA POSIÇÃO 1!".
     */
    @Test
    public void testFavoritoSobrescreve() {
        String mensagem = "Sobreescre o contato favorito na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
        agenda.adicionarFavorito(1, 1);
        String resultado = agenda.adicionarFavorito(2, 1);
        assertEquals(resultado, "CONTATO FAVORITADO NA POSIÇÃO 1!",mensagem);
        assertFalse(agenda.getContato(1).getFavorito(), mensagem);
        assertTrue(agenda.getContato(2).getFavorito(), mensagem);
    }

    /**
     * Tenta cadastrar um contato já favorito em uma posição diferente.
     */
    @Test
    public void testFavoritoOutraPosicao(){
        try{
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.adicionarFavorito(1, 1);
            agenda.adicionarFavorito(1, 2);
            fail("CONTATO JÁ FAOVIRTADO");
        } catch(IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar o favorito a um contato inexistente na agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testAdicionaFavoritoContatoInexistente() {
        try {
            agenda.adicionarFavorito(4, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar o favorito a um contato fora das posições limite da agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testAdicionaFavoritoContatoInvalido() {
        try {
            agenda.adicionarFavorito(0, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar um favorito na posição, fora do limite dos favoritos, 0 .
     * Espera-se como retorno a string "POSIÇÃO DE FAVORITO INVÁLIDA".
     */
    @Test
    public void testAdicionaFavoritoPosicaoZero() {
        try {
            agenda.adicionarFavorito(2, 0);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar um favorito na posição, fora do limite dos favoritos, 11.
     * Espera-se como retorno a string "POSIÇÃO DE FAVORITO INVÁLIDA".
     */
    @Test
    public void testAdicionaFavoritoPosicaoOnze() {
        try {
            agenda.adicionarFavorito(2, 11);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa remover o favorito da posição limite 1.
     * Espera-se como retorno a string "FAVORITO REMOVIDO DA POSIÇÃO 1!".
     */
    @Test
    public void testRemoveFavoritoUm() {
        String mensagem = "Remove o contato favoritado na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1, 1);
        String resultado = agenda.removerFavorito(1);
        assertEquals(resultado, "FAVORITO REMOVIDO DA POSIÇÃO 1!", mensagem);
    }

    /**
     * Testa remover o favorito da posição limite 10.
     * Espera-se como retorno a string "FAVORITO REMOVIDO DA POSIÇÃO 10!".
     */
    @Test
    public void testRemoveFavoritoDez() {
        String mensagem = "Remove o contato favoritado na posição 1";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1, 10);
        String resultado = agenda.removerFavorito(10);
        assertEquals(resultado, "FAVORITO REMOVIDO DA POSIÇÃO 10!", mensagem);
    }

    /**
     * Tenta remove o favorito da posição, fora do limites dos favoritos, 0.
     * Espera-se como retorno a string "POSIÇÃO DE FAVORITO INVÁLIDA".
     */
    @Test
    public void testRemoveFavoritoPosicaoZero() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.adicionarFavorito(1, 1);
            agenda.removerFavorito(0);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remove o favorito da posiçãoo, fora do limites dos favoritos, 11.
     * Espera-se como retorno a string "POSIÇÃO DE FAVORITO INVÁLIDA".
     */
    @Test
    public void testRemoveFavoritoPosicaoOnze() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.adicionarFavorito(1, 1);
            agenda.removerFavorito(11);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }


    /**
     * Tenta remover o favorito de um contato inexistente na agenda.
     * Espera-se como retorno a string "FAVORITO INVÁLIDO".
     */
    @Test
    public void testRemoveFavoritoContatoInexistente() {
        try {
            agenda.removerFavorito(1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa adicionar ao contato especificado uma tag na posição limite 1.
     * Espera-se como retorno a string "TAG(S) ADICIONADA(S)".
     */
    @Test
    public void testAdiconaTagsUm(){
        String mensagem = "Adiciona uma tag a um único contato";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String[] contatos = {"1"};
        agenda.adicionarTags(contatos, "estudante", 1);
        boolean resultado = agenda.getContato(1).existeTag("estudante");
        assertEquals(agenda.adicionarTags(contatos, "estudante", 1), "TAG(S) ADICIONADA(S)", mensagem);
        assertTrue(resultado, mensagem);

    }

    /**
     * Testa adicionar ao contato especificado uma tag na posição limite 5.
     * Espera-se como retorno a string "TAG(S) ADICIONADA(S)".
     */
    @Test
    public void testAdiconaTagsCinco() {
        String mensagem = "Adiciona uma tag a um único contato";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String[] contatos = {"1"};
        agenda.adicionarTags(contatos, "estudante", 5);
        boolean resultado = agenda.getContato(1).existeTag("estudante");
        assertEquals(agenda.adicionarTags(contatos, "estudante", 5), "TAG(S) ADICIONADA(S)", mensagem);
        assertTrue(resultado, mensagem);

    }

    /**
     * Testa adiconar uma tag a mais de um contato especificados.
     * Espera-se como retorno a string "TAG(S) ADICONADA(S)".
     */
    @Test
    public void testAdicionaTagsContatos() {
        String mensagem = "Adiciona uma tag à diferentes contatos";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
        String tag = "estudante";
        String[] contatos = {"1", "2"};
        String resultado = agenda.adicionarTags(contatos, tag, 1);
        boolean resultadoPrimeiro = agenda.getContato(1).existeTag(tag);
        boolean resultadoSegundo = agenda.getContato(2).existeTag(tag);
        assertEquals(resultado, "TAG(S) ADICIONADA(S)", mensagem);
        assertTrue(resultadoPrimeiro, mensagem);
        assertTrue(resultadoSegundo, mensagem);
    }

    /**
     * Testa alterar a tag adicionada na posição 1, sobrevendo a tag atual com outra.
     * Espera-se como retorno a string "TAG(S) ADICIONADA(S)".
     */
    @Test
    public void testAdiconaTagsSobreescreve() {
        String mensagem = "Adiciona uma tag a um único contato e sobrescreve a mesma";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String[] contatos = {"1"};
        agenda.adicionarTags(contatos, "estudante", 1);
        agenda.adicionarTags(contatos, "ccc", 1);
        boolean resultadoPrimeiro = agenda.getContato(1).existeTag("estudante");
        boolean resultadoSegundo = agenda.getContato(1).existeTag("ccc");
        assertEquals(agenda.adicionarTags(contatos, "estudante", 1), "TAG(S) ADICIONADA(S)", mensagem);
        assertEquals(agenda.adicionarTags(contatos, "ccc", 1), "TAG(S) ADICIONADA(S)", mensagem);
        assertFalse(resultadoPrimeiro, mensagem);
        assertTrue(resultadoSegundo, mensagem);

    }

    /**
     * Tenta adicionar ao contato especificado uma tag na posição, fora do limites, 0.
     * Espera-se como retorno a string "POSIÇÃO DE TAG INVÁLIDA".
     */
    @Test
    public void testAdiconaTagPosicaoZero() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
            String tag = "estudante";
            String[] contatos = {"1", "2"};
            agenda.adicionarTags(contatos, tag, 0);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adiciona ao contato especificado uma tag na posição, fora do limites, 6.
     * Espera-se como retorno a string "POSIÇÃO DE TAG INVÁLIDA".
     */
    @Test
    public void testAdiconaTagPosicaoSeis() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.cadastraContato(2, "Maria", "Bezerra", "8888-8888");
            String tag = "estudante";
            String[] contatos = {"1", "2"};
            agenda.adicionarTags(contatos, tag, 6);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar uma tag a um contato inexistente na agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testAdiconaTagContatoInexistente() {
        try {
            String tag = "estudante";
            String[] contatos = {"4", "2"};
            agenda.adicionarTags(contatos, tag, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta adicionar uma tag a um contato fora das posições limite da agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testAdiconaTagContatoInvalido() {
        try {
            String tag = "estudante";
            String[] contatos = {"0"};
            agenda.adicionarTags(contatos, tag, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa remove do contato especificado uma tag na posição limite 1.
     * Espera-se como retorno a string "TAG REMOVIDA".
     */
    @Test
    public void testRemoveTagUm() {
        String mensagem = "Remove uma tag do contato";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String[] contato = {"1"};
        agenda.adicionarTags(contato, "estudante", 1);
        String resultado = agenda.removerTag(1, 1);
        assertEquals(resultado, "TAG REMOVIDA", mensagem);
    }

    /**
     * Testa remover do contato especificado uma tag na posição limite 5.
     * Espera-se como retorno a string "TAG REMOVIDA".
     */
    @Test
    public void testRemoveTagCinco() {
        String mensagem = "Remove uma tag do contato";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        String[] contato = {"1"};
        agenda.adicionarTags(contato, "estudante", 5);
        String resultado = agenda.removerTag(1, 5);
        assertEquals(resultado, "TAG REMOVIDA", mensagem);
    }

    /**
     * Tenta remover uma tag inexistente na posição especificada.
     * Espera-se como retorno a string "TAG NÃO EXISTE".
     */
    @Test
    public void testRemoveTagInexistente() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.removerTag(1, 1);
            fail("TAG INEXISTENTE");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remover uma tag na posição, fora do limite das tags, 0.
     * Espera-se como retorno a string "POSIÇÃO DE TAG INVÁLIDA".
     */
    @Test
    public void testRemoveTagPosicaoZero() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.removerTag(1, 0);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remover uma tag na posição, fora do limite das tags, 6.
     * Espera-se como retorno a string "POSIÇÃO DE TAG INVÁLIDA".
     */
    @Test
    public void testRemoveTagPosicaoSeis() {
        try {
            agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
            agenda.removerTag(1, 6);
            fail("POSIÇÃO INVÁLIDA");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remover uma  tag de um contato inexistente na agenda.
     * Espera-se como retorno a string "CONTATO INVÁLIDO".
     */
    @Test
    public void testRemoveTagContatoInexistente() {
        try {
            agenda.removerTag(1, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Tenta remover uma tag de contato fora das posições limite da agenda.
     * Espera-se como retorno a string "POSIÇÃO DE CONTATO INVÁLIDA".
     */
    @Test
    public void testRemoveTagContatoInvalido() {
        try {
            agenda.removerTag(0, 1);
            fail("CONTATO INVÁLIDO");
        } catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    /**
     * Testa se um contato removido da agenda também é removido dos favoritos.
     */
    @Test
    public void testContatoFavoritoRemovidoAgenda() {
        String mensagem = "Retorna o null do favorito após a remoção dos agenda";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1, 1);
        agenda.removerContato(1);
        Contato resultado = agenda.getFavorito(1);
        assertNull(agenda.getContato(1), mensagem);
        assertNull(resultado, mensagem);
    }


    /**
     * Testa se  que um contato sobreescrito na agenda é removido dos favoritos.
     */
    @Test
    public void testContatoFavoritoSobrescritoAgenda(){
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1, 1);
        agenda.cadastraContato(1, "Maria", "Bezerra", "8888-8888");
        assertNull(agenda.getFavorito(1));
    }

    /**
     * Testa se que um contaot removido dos favoritos permanece na agenda.
     */
    @Test
    public void testContatoRemovidoFavoritos() {
        String mensagem = "Retorna o contato mesmo após a remoção dos favoritos";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        agenda.adicionarFavorito(1, 1);
        agenda.removerFavorito(1);
        String resultadoPrimeiro = agenda.exibirContato(1);
        Contato resultadoSegundo = agenda.getFavorito(1);
        assertEquals(resultadoPrimeiro, "Leticia Rodrigues\n9999-9999\n", mensagem);
        assertNull(resultadoSegundo, mensagem);
    }

    /**
     * Testa se existe o contato na agenda a partir do nome completo, no formato "NomeSobrenome".
     */
    @Test
    public void testExisteContatoPositivo(){
        String mensagem = "Verifica se existe o contato na agenda";
        agenda.cadastraContato(1, "Leticia", "Rodrigues", "9999-9999");
        assertTrue(agenda.existeContato("LeticiaRodrigues"), mensagem);
    }

    /**
     * Testa se existe o contato na agenda a partir do nome completo, no formato "NomeSobrenome".
     */
    @Test
    public void testExisteContatoNegativo(){
        String mensagem = "Verifica se existe o contato na agenda";
        assertFalse(agenda.existeContato("LeticiaRodrigues"), mensagem);
    }

}