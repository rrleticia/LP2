package ControleAlunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class GruposRepositoryTest {

    private GruposRepository grupos;

    @BeforeEach
    void testBeforeEach(){
        this.grupos = new GruposRepository();
    }

    @Test
    void getConstrutor() {
        GruposRepository genshin = new GruposRepository();
    }

    @Test
    void getGrupo() {
        this.grupos.cadastrar("inazuma");
        Grupo grupoResultado = this.grupos.getGrupo("inazuma");
        Grupo grupoEsperado = new Grupo("inazuma");
        assertEquals(grupoResultado, grupoEsperado);
    }

    @Test
    void testAlocarAlunoCT(){
        this.grupos.cadastrar("inazuma", 10);
        assertTrue(this.grupos.alocarAluno("12345", "inazuma"));
    }

    @Test
    void testAlocarAlunoST(){
        this.grupos.cadastrar("inazuma");
        assertTrue(this.grupos.alocarAluno("12345", "inazuma"));
    }

    @Test
    void testAlocarAlunoNovamenteCT(){
        this.grupos.cadastrar("inazuma");
        this.grupos.alocarAluno("12345", "inazuma");
        assertTrue(this.grupos.alocarAluno("12345", "inazuma"));
    }

    @Test
    void testAlocarAlunoNovamenteST(){
        this.grupos.cadastrar("inazuma");
        this.grupos.alocarAluno("12345", "inazuma");
        assertTrue(this.grupos.alocarAluno("12345", "inazuma"));
    }

    @Test
    void testAlocarAlunoCTCheio(){
        this.grupos.cadastrar("inazuma", 2);
        this.grupos.alocarAluno("12345", "inazuma");
        this.grupos.alocarAluno("09823", "inazuma");
        assertFalse(this.grupos.alocarAluno("56789", "inazuma"));
    }

    @Test
    void testVerificarExistenciaTrue() {
        this.grupos.cadastrar("inazuma", 2);
        this.grupos.alocarAluno("12345", "inazuma");
        assertTrue(this.grupos.verificarExistencia("12345", "inazuma"));
    }

    @Test
    void testVerificarExistenciaFalse() {
        this.grupos.cadastrar("inazuma", 2);
        this.grupos.verificarExistencia("12345", "inazuma");
        assertFalse(this.grupos.verificarExistencia("12345", "inazuma"));
    }

    @Test
    void testExibirGruposDoAluno() {
        this.grupos.cadastrar("inazuma", 2);
        this.grupos.alocarAluno("12345", "inazuma");
        this.grupos.cadastrar("mondstat");
        this.grupos.alocarAluno("12345", "mondstat");
        String resultado = this.grupos.exibirGruposDoAluno("12345");
        assertEquals(resultado, "- inazuma\n- mondstat");
    }

    @Test
    void testExibirGruposDoAlunoSem() {
        String resultado = this.grupos.exibirGruposDoAluno("12345");
        assertTrue(resultado.isBlank());
    }

    @Test
    void testExisteGrupoTrue() {
        this.grupos.cadastrar("inazuma", 2);
        assertTrue(this.grupos.existeGrupo("inazuma"));
    }

    @Test
    void testExisteGrupoFalse() {
        assertFalse(this.grupos.existeGrupo("inazuma"));
    }

    @Test
    void testExisteGrupoTrueCaixAlta() {
        this.grupos.cadastrar("inazuma", 2);
        assertTrue(this.grupos.existeGrupo("INAZUMA"));
        assertEquals(grupos.getGrupo("inazuma").toString(), "inazuma");
    }

    @Test
    void testExisteGrupoTrueCaixBaixa() {
        this.grupos.cadastrar("INAZUMA", 2);
        assertTrue(this.grupos.existeGrupo("inazuma"));
        assertEquals(grupos.getGrupo("inazuma").toString(), "INAZUMA");
    }

}