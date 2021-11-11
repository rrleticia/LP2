package ControleAlunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfaceControleTest {

    private InterfaceControle interfaceControle;

    @BeforeEach
    void testBeforeEach(){
        this.interfaceControle= new InterfaceControle();
    }

    @Test
    void testCadastraAluno() {
        String resultado = this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        assertEquals(resultado, "CADASTRO REALIZADO!");
    }

    @Test
    void testCadastraAlunoJaRealizado() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        String resultado = this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        assertEquals(resultado, "ALUNO JÁ CADASTRADO!");
    }

    @Test
    void testCadastrarGrupoCT() {
        String resultado = this.interfaceControle.cadastrarGrupo("inazuma", 0);
        assertEquals(resultado, "CADASTRO REALIZADO!");
    }

    @Test
    void testCadastrarGrupoJaRealizadoCT() {
        this.interfaceControle.cadastrarGrupo("inazuma", 0);
        String resultado = this.interfaceControle.cadastrarGrupo("inazuma", 0);
        assertEquals(resultado, "GRUPO JÁ CADASTRADO!");
    }

    @Test
    void testCadastrarGrupoST() {
        String resultado = this.interfaceControle.cadastrarGrupo("inazuma");
        assertEquals(resultado, "CADASTRO REALIZADO!");
    }

    @Test
    void testCadastrarGrupoJaRealizadoST() {
        this.interfaceControle.cadastrarGrupo("inazuma");
        String resultado = this.interfaceControle.cadastrarGrupo("inazuma");
        assertEquals(resultado, "GRUPO JÁ CADASTRADO!");
    }

    @Test
    void testConsultarAlunoExiste() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        String resultado = this.interfaceControle.consultarAluno("1234");
        assertEquals(resultado, "Aluno: 1234 - kazuha - anemo");
    }

    @Test
    void testConsultarAlunoNaoExiste() {
        String resultado = this.interfaceControle.consultarAluno("1234");
        assertEquals(resultado, "ALUNO NÃO CADASTRADO.");
    }

    @Test
    void testAlocarNaoExisteAluno() {
        this.interfaceControle.cadastrarGrupo("Inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO NÃO CADASTRADO.");
    }

    @Test
    void testAlocarNaoExisteGrupo() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"GRUPO NÃO CADASTRADO.");
    }

    @Test
    void testAlocarAlunoGTD() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma", 5);
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO ALOCADO.");
    }

    @Test
    void testAlocarMesmoAlunoGTD() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma", 5);
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO ALOCADO.");
    }

    @Test
    void testAlocarAlunoGTDCheio() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0721", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("1313", "eula", "cyro");
        this.interfaceControle.cadastrarAluno("0976", "yanfei", "pyro");
        this.interfaceControle.cadastrarGrupo("inazuma", 4);
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("5678", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("0721", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1313", "inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("0976", "inazuma");
        assertEquals(resultado,"GRUPO CHEIO!");
    }

    @Test
    void testAlocarMesmoAlunoGTDCheio() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0721", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("1313", "eula", "cyro");
        this.interfaceControle.cadastrarAluno("0976", "yanfei", "pyro");
        this.interfaceControle.cadastrarGrupo("inazuma", 4);
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("5678", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("0721", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1313", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO ALOCADO.");
    }

    @Test
    void testAlocarAlunoGTI() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO ALOCADO.");
    }

    @Test
    void testAlocarMesmoAlunoGTI() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        String resultado = this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        assertEquals(resultado,"ALUNO ALOCADO.");
    }

    @Test
    void testVerificarExistenciaGTDTrue() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma", 10);
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        String resultado = this.interfaceControle.verificarExistencia("inaZuma", "1234");
        assertEquals(resultado, "ALUNO PERTENCE AO GRUPO!");
    }

    @Test
    void testVerificarExistenciaGTDFalse() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma", 10);
        String resultado = this.interfaceControle.verificarExistencia("inaZuma", "1234");
        assertEquals(resultado, "ALUNO NÃO PERTENCE AO GRUPO!");
    }

    @Test
    void testVerificarExistenciaGTITrue() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        String resultado = this.interfaceControle.verificarExistencia("inaZuma", "1234");
        assertEquals(resultado, "ALUNO PERTENCE AO GRUPO!");
    }

    @Test
    void testVerificarExistenciaGTIFalse() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        String resultado = this.interfaceControle.verificarExistencia("inaZuma", "1234");
        assertEquals(resultado, "ALUNO NÃO PERTENCE AO GRUPO!");
    }

    @Test
    void testExibirGruposDoAluno() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        this.interfaceControle.cadastrarGrupo("anemo");
        this.interfaceControle.cadastrarGrupo("cute");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "inazuma");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "anemo");
        this.interfaceControle.alocarAlunoEmGrupo("1234", "cute");
        String resultado = this.interfaceControle.exibirGruposDoAluno("1234");
        assertEquals(resultado, "Grupos:\n- inazuma\n- anemo\n- cute");
    }

    @Test
    void testExibirGruposDoAlunoVazio() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        this.interfaceControle.cadastrarGrupo("inazuma");
        this.interfaceControle.cadastrarGrupo("anemo");
        this.interfaceControle.cadastrarGrupo("cute");
        String resultado = this.interfaceControle.exibirGruposDoAluno("1234");
        assertEquals(resultado, "ALUNO NÃO FAZ PARTE DE GRUPOS!");
    }

    @Test
    void testRegistrarRespostaAluno() {
        this.interfaceControle.cadastrarAluno("1234", "venti", "anemo");
        String resultado = this.interfaceControle.registrarResposta("1234");
        assertEquals(resultado, "ALUNO CADASTRADO!");
    }

    @Test
    void testRegistrarRespostaNaoExisteAluno() {
        String resultado = this.interfaceControle.registrarResposta("1234");
        assertEquals(resultado, "ALUNO NÃO CADASTRADO.");
    }

    @Test
    void testExibirRespostas() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Alunos:\n1. 5678 - venti - anemo\n2. 1234 - kazuha - anemo\n3. 0712 - kokomi - hydro\n4. 0712 - kokomi - hydro";
        assertEquals(this.interfaceControle.exibirRespostas(), esperado);
    }

    @Test
    void testExibirRespostasVazio() {
        assertEquals(this.interfaceControle.exibirRespostas(), "Alunos:\nSEM RESPOSTAS REGISTRADAS!");
    }


    @Test
    void testEstatisticaAlunosMaisChamadosUnico() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Alunos Mais Chamados:\nAluno(s) foram chamados 2 vezes\n0712 - kokomi - hydro";
        assertEquals(this.interfaceControle.estatisticaAlunosMaisChamados(), esperado);
    }

    @Test
    void testEstatisticaAlunosMaisChamadosVarios() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Alunos Mais Chamados:\nAluno(s) foram chamados 2 vezes\n1234 - kazuha - anemo\n0712 - kokomi - hydro";
        assertEquals(this.interfaceControle.estatisticaAlunosMaisChamados(), esperado);
    }

    @Test
    void testEstatisticaAlunosMaisChamadosVazio() {
        assertEquals(this.interfaceControle.estatisticaAlunosMaisChamados(), "NENHUM ALUNO FOI CHAMADO!");
    }

    @Test
    void testEstatisticaAlunosNaoChamados() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("4444", "qiqi", "cyro");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0712");
        String esperado  = "Alunos Não Chamados:\n4444 - qiqi - cyro";
        assertEquals(this.interfaceControle.estatisticaAlunosNaoChamados(),esperado);
    }

    @Test
    void testEstatisticaAlunosNaoChamadosVazio() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0712");
        assertEquals(this.interfaceControle.estatisticaAlunosNaoChamados(), "TODOS OS ALUNOS FORAM CHAMADOS!");
    }

    @Test
    void testEstatisticaRespostasPorCursoSemEmpate() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("0098", "sara", "electro");
        this.interfaceControle.cadastrarAluno("7766", "yanfei", "pyro");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("7766");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Listagem de Cursos:\n1. anemo - 4 respostas de alunos\n2. hydro - 3 respostas de alunos\n3. electro - 2 respostas de alunos\n4. pyro - 1 respostas de alunos";
        assertEquals(this.interfaceControle.estatisticaRespostasPorCurso(), esperado);
    }

    @Test
    void testEstatisticaRespostasPorCursoComEmpate() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("0098", "sara", "electro");
        this.interfaceControle.cadastrarAluno("7766", "yanfei", "pyro");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("7766");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Listagem de Cursos:\n1. anemo - 4 respostas de alunos\n2. hydro - 3 respostas de alunos\n2. electro - 3 respostas de alunos\n3. pyro - 1 respostas de alunos";
        assertEquals(this.interfaceControle.estatisticaRespostasPorCurso(), esperado);
    }

    @Test
    void testEstatisticaRespostasPorCurso() {
        this.interfaceControle.cadastrarAluno("1234", "kazuha", "anemo");
        this.interfaceControle.cadastrarAluno("5678", "venti", "anemo");
        this.interfaceControle.cadastrarAluno("0712", "kokomi", "hydro");
        this.interfaceControle.cadastrarAluno("0098", "sara", "electro");
        this.interfaceControle.cadastrarAluno("7766", "yanfei", "pyro");
        this.interfaceControle.cadastrarAluno("9900", "eula", "cyro");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("1234");
        this.interfaceControle.registrarResposta("5678");
        this.interfaceControle.registrarResposta("0712");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("0098");
        this.interfaceControle.registrarResposta("7766");
        this.interfaceControle.registrarResposta("0712");
        String esperado = "Listagem de Cursos:\n1. anemo - 4 respostas de alunos\n2. hydro - 3 respostas de alunos\n3. electro - 2 respostas de alunos\n4. pyro - 1 respostas de alunos";
        assertEquals(this.interfaceControle.estatisticaRespostasPorCurso(), esperado);
    }

    @Test
    void testEstatisticaRespostasPorCursoVazio() {
        assertEquals(this.interfaceControle.estatisticaRespostasPorCurso(), "NENHUM CURSO FOI CHAMADO!");
    }
}