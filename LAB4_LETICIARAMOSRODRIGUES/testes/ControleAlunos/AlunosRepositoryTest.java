package ControleAlunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunosRepositoryTest {

    private AlunosRepository alunos;

    @BeforeEach
    void testBeforeEach(){
        this.alunos = new AlunosRepository();
    }

    @Test
    void testGetAluno() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        Aluno alunoResultado = this.alunos.getAluno("1234");
        Aluno alunoEsperado = new Aluno("1234", "kazuha", "anemo");
        assertEquals(alunoResultado, alunoEsperado);
    }

    @Test
    void testCadastrar() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        assertTrue(this.alunos.existeAluno("1234"));
    }

    @Test
    void testConsultar() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        String resultado = this.alunos.consultar("1234");
        assertEquals(resultado, "1234 - kazuha - anemo");
    }

    @Test
    void testExisteAlunoTrue() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        assertTrue(this.alunos.existeAluno("1234"));
    }

    @Test
    void testExisteAlunoFalse() {
        assertFalse(this.alunos.existeAluno("1234"));
    }

    @Test
    void testRegistrarResposta() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.registrarResposta("1234");
        assertEquals(this.alunos.getAluno("1234").getNumeroRespostas(), 1);
    }

    @Test
    void testExibirRespostas() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.cadastrar("0712", "kokomi", "hydro");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("0712");
        String esperado = "1. 5678 - venti - anemo\n2. 1234 - kazuha - anemo\n3. 0712 - kokomi - hydro";
        assertEquals(this.alunos.exibirRespostas(), esperado);

    }

    @Test
    void testAlunosMaisChamadosUnico() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.cadastrar("0712", "kokomi", "hydro");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        String esperado = "Aluno(s) foram chamados 3 vezes\n0712 - kokomi - hydro";
        assertEquals(this.alunos.alunosMaisChamados(), esperado);
    }

    @Test
    void testAlunosMaisChamadosVarios() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.cadastrar("0712", "kokomi", "hydro");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        String esperado = "Aluno(s) foram chamados 3 vezes\n1234 - kazuha - anemo\n0712 - kokomi - hydro";
        assertEquals(this.alunos.alunosMaisChamados(), esperado);
    }

    @Test
    void testAlunosMaisChamadosVazio() {
        assertTrue(this.alunos.alunosMaisChamados().isBlank());
    }

    @Test
    void testAlunosSemRespostas() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.cadastrar("0712", "kokomi", "hydro");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        String esperado = "0712 - kokomi - hydro";
        assertEquals(this.alunos.alunosSemRespostas(), esperado);
    }

    @Test
    void testAlunosSemRespostasVazio() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        assertTrue(this.alunos.alunosSemRespostas().isBlank());
    }

    @Test
    void testListagemRespostasPorCurso() {
        this.alunos.cadastrar("1234", "kazuha", "anemo");
        this.alunos.cadastrar("5678", "venti", "anemo");
        this.alunos.cadastrar("0712", "kokomi", "hydro");
        this.alunos.registrarResposta("5678");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("1234");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        this.alunos.registrarResposta("0712");
        String esperado = "1. anemo - 4 respostas de alunos\n2. hydro - 3 respostas de alunos";
        assertEquals(this.alunos.listagemRespostasPorCurso(), esperado);
    }

    @Test
    void testListagemRespostasPorCursoVazio() {
        assertTrue(this.alunos.listagemRespostasPorCurso().isBlank());
    }

}