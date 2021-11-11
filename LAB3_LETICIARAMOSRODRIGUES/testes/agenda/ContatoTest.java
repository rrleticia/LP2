package agenda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes reponsáveis por testar funcionalidades gerais de get, de set e de toString dos contatos.
 *
 * @author Leticia Ramos Rodrigues
 */
class ContatoTest {

    private Contato contatoTest;

    /**
     * Cria antes da execução de todos os testes um contato de teste.
     */
    @BeforeEach
    public void preparaContato(){
        this.contatoTest = new Contato("Leticia", "Rodrigues", "9999-9999");
    }

    /**
     * Testa construir uma agenda
     */
    @Test
    public void tesConstrutor(){
        Contato contatinho = new Contato("Leticia", "Rorigues", "9999-9999");
    }

    /**
     * Testa a criação de um contato com o parâmetro nome igual a null.
     * Espera-se como retorno um erro de exceção "NullPointerException".
     */
    @Test
    public void testNomeNull(){
        try {
            Contato contatoInvalido = new Contato(null, "Rodrigues", "9999-9999");
            fail("INFORMAÇÕES NULAS SÃO EXCEÇÕES!");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro sobrenome igual a null.
     * Espera-se como retorno um erro de exceção "NullPointerException".
     */
    @Test
    public void testSobrenomeNNull(){
        try {
            Contato contatoInvalido = new Contato("Leticia", null, "9999-9999");
            fail("INFORMAÇÕES NULAS SÃO EXCEÇÕES!");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro telefone igual a null.
     * Espera-se como retorno um erro de exceção "NullPointerException".
     */
    @Test
    public void testTelefoneNull(){
        try {
            Contato contatoInvalido = new Contato("Leticia", "Rodrigues", null);
            fail("INFORMAÇÕES NULAS SÃO EXCEÇÕES!");
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro nome vazio.
     * Espera-se como retorno um erro de exceção "IllegalArgumentException".
     */
    @Test
    public void testNomeVazio(){
        try {
            Contato contatoInvalido = new Contato("", "Rodrigues", "9999-9999");
            fail("NOMES VAZIOS SÃO EXCEÇÕES!");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro nome em branco.
     * Espera-se como retorno um erro de exceção "IllegalArgumentException".
     */
    @Test
    public void testNomeBranco(){
        try {
            Contato contatoInvalido = new Contato("    ", "Rodrigues", "9999-9999");
            fail("NOMES BRANCOS SÃO EXCEÇÕES!");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro telefone vazio.
     * Espera-se como retorno um erro de exceção "IllegalArgumentException".
     */
    @Test
    public void testTelefoneVazio(){
        try {
            Contato contatoInvalido = new Contato("Leticia", "Rodrigues", "");
            fail("TELEFONES VAZIOS SÃO EXCEÇÕES!");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa a criação de um contato com o parâmetro telefone em branco.
     * Espera-se como retorno um erro de exceção "IllegalArgumentException".
     */
    @Test
    public void testTelefoneBranco(){
        try {
            Contato contatoInvalido = new Contato("Leticia", "Rodrigues", "       ");
            fail("TELEFONES BRANCOS SÃO EXCEÇÕES!");
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }

    /**
     * Testa o retorno do nome de um contato.
     */
    @Test
    public void testGetNome(){
        String mensagem = "Retorna o nome do contato";
        assertEquals(contatoTest.getNome(), "Leticia", mensagem);
    }

    /**
     * Testa o retorno do sobrenome de um contato.
     */
    @Test
    public void testGetSobrenome(){
        String mensagem = "Retorna o sobrenome do contato";
        assertEquals(contatoTest.getSobrenome(), "Rodrigues", mensagem);
    }

    /**
     * Testa o retorno do nome completo de um contato.
     * Na formatação "NomeSobrenome".
     */
    @Test
    public void testGetNomeCompleto(){
        String mensagem = "Retorna o nome completo do contato";
        assertEquals(contatoTest.getNomeCompleto(), "LeticiaRodrigues", mensagem);
    }

    /**
     * Testa o retorno do telefone de um contato.
     */
    @Test
    public void testGetTelefone(){
        String mensagem = "Retorna o telefone do contato";
        assertEquals(contatoTest.getTelefone(), "9999-9999", mensagem);
    }

    /**
     * Testa o retorno o status de favorito de um contato quando "true".
     */
    @Test
    public void testFavoritoTrue(){
        String mensagem = "Retorna se o contato é um favorito";
        contatoTest.definirFavorito();
        assertTrue(contatoTest.getFavorito(), mensagem);
    }

    /**
     * Testa o retorno o status de favorito de um contato quando "false".
     */
    @Test
    public void testFavoritoFalse(){
        String mensagem = "Retorna se o contato é um favorito";
        assertFalse(contatoTest.getFavorito(), mensagem);
    }

    /**
     * Testa a alteração das tags pertecentes ao contato.
     */
    @Test
    public void testGetArrayTags(){
        String mensagem = "Retorna o array com as tags do contato";
        contatoTest.definirTags(1, "linda");
        contatoTest.definirTags(2, "baixa");
        contatoTest.definirTags(3, "cansada");
        contatoTest.definirTags(4, "morta");
        contatoTest.definirTags(5, "scr");
        assertEquals(contatoTest.getArrayTags()[0], "linda", mensagem);
        assertEquals(contatoTest.getArrayTags()[1],"baixa", mensagem);
        assertEquals(contatoTest.getArrayTags()[2],"cansada", mensagem);
        assertEquals(contatoTest.getArrayTags()[3], "morta", mensagem);
        assertEquals(contatoTest.getArrayTags()[4],"scr", mensagem);
    }

    /**
     * Testa a alteração realizada no campo telefone do contato.
     */
    @Test
    public void testSetTelefone(){
        String mensagem = "Altera o número de telefone de um celular";
        contatoTest.setTelefone("8888-8888");
        assertEquals(contatoTest.getTelefone(), "8888-8888", mensagem);
    }

    /**
     * Testa como o retorno a verificação da adição de favorito ao contato.
     * Espera-se como retorno "true".
     */
    @Test
    public void testDefineFavorito(){
        String mensagem = "Define um contato como favorito";
        contatoTest.definirFavorito();
        assertTrue(contatoTest.getFavorito(), mensagem);
    }

    /**
     * Testa como o retorno a verificação da remoção de favorito ao contato.
     * Espera-se como retorno "false".
     */
    @Test
    public void testRemoveFavorito(){
        String mensagem = "Remove o favorito do contato";
        contatoTest.retirarFavorito();
        assertFalse(contatoTest.getFavorito(), mensagem);
    }

    /**
     * Testa como o retorno a verificação da adição de uma tag ao contato.
     * Espera-se como retorno "true".
     */
    @Test
    public void testDefineTag(){
        String mensagem = "Define uma tag para o contato";
        contatoTest.definirTags(1, "estudante");
        boolean resultado = contatoTest.existeTag("estudante");
        assertTrue(resultado, mensagem);
    }

    /**
     * Testa como o retorno a verificação da remoção de uma tag pertencente ao contato.
     * Espera-se como retorno "false".
     */
    @Test
    public void testDeletaTag(){
        String mensagem = "Define uma tag para o contato";
        contatoTest.definirTags(1, "estudante");
        contatoTest.retirarTag(1);
        boolean resultado = contatoTest.existeTag("estudante");
        assertFalse(resultado, mensagem);
    }

    /**
     * Testa a verificação da existeência de um tag pertencente ao contato.
     * Espera-se como retorno "true".
     */
    @Test
    public void testExisteTagTrue(){
        String mensagem = "Procura se a tag está associada ao contato";
        contatoTest.definirTags(1, "estudante");
        boolean resultado = contatoTest.existeTag("estudante");
        assertTrue(resultado, mensagem);
    }

    /**
     * Testa a verificação da existência de um tag não pertencente ao contato.
     * Espera-se como retorno "false".
     */
    @Test
    public void testExisteTagFalse(){
        String mensagem = "Procura se a tag está associada ao contato";
        contatoTest.definirTags(1, "");
        boolean resultado = contatoTest.existeTag("estudante");
        assertFalse(resultado, mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato não favorito.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void testExibeContato(){
        String mensagem = "Exibe um contato";
        String resultado = contatoTest.toString();
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato favoritado".
     * Na formatação "❤ NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void testExibeContatoFavorito(){
        String mensagem = "Exibe um contato";
        contatoTest.definirFavorito();
        String resultado = contatoTest.toString();
        assertEquals(resultado, "❤️ Leticia Rodrigues\n9999-9999\n", mensagem);
    }

    /**
     * Testa a exibição de um contato em lista.
     * Na formataçao "POSIÇÃO NA AGENDA - NOME SOBRENOME".
     */
    @Test
    public void testToLista(){
        String mensagem = "Formata a string da lista";
        String resultado = contatoTest.formataLista(1);
        assertEquals(resultado, "1 - Leticia Rodrigues\n", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato com tags.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE
     *                TAGS".
     */
    @Test
    public void testToStringComTags(){
        String mensagem = "Exibe a string do método toString";
        contatoTest.definirTags(1, "estudante");
        contatoTest.definirTags(3, "uepb");
        contatoTest.definirTags(4, "direito");
        String resultado = contatoTest.toString();
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\nestudante uepb direito", mensagem);
    }

    /**
     * Testa a exibição para o usuário de um contato sem tags.
     * Na formatação "NOME SOBRENOME
     *                TELEFONE".
     */
    @Test
    public void testToStringSemTags(){
        String mensagem = "Exibe a string do método toString";
        String resultado = contatoTest.toString();
        assertEquals(resultado, "Leticia Rodrigues\n9999-9999\n", mensagem);
    }

}