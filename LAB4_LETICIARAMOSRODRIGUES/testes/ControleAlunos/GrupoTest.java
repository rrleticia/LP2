package ControleAlunos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrupoTest {

    private Grupo grupoTamanhoDefinido;
    private Grupo grupoTamanhoIndefinido;

    @BeforeEach
    void testBeforeEach(){
        this.grupoTamanhoDefinido = new Grupo("Mondstat", 5);
        this.grupoTamanhoIndefinido = new Grupo("Liyue");
    }

    @Test
    void testConstrutorComTamanho(){
        Grupo inazuma = new Grupo("Inazuma", 100);
    }

    @Test
    void testConstrutorCTNomeNull(){
        try{
            Grupo inazuma = new Grupo(null, 100);
            fail("NOME DE GRUPO NULO");
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testConstrutorCTNomeVazio(){
        try{
            Grupo inazuma = new Grupo("", 100);
            fail("NOME DE GRUPO NULO");
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testConstrutorSemTamanho(){
        Grupo snezhnaya = new Grupo("Snezhnaya");
    }

    @Test
    void testConstrutorSTNomeNull(){
        try{
            Grupo inazuma = new Grupo(null);
            fail("NOME DE GRUPO NULO");
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testConstrutorSTNomeVazio(){
        try{
            Grupo inazuma = new Grupo("");
            fail("NOME DE GRUPO NULO");
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testGetNomeGrupo() {
        assertEquals(this.grupoTamanhoDefinido.getNomeGrupo(), "Mondstat");
    }

    @Test
    void testGetNumeroPessoasDefinido() {
        assertEquals(this.grupoTamanhoDefinido.getNumeroPessoas(), 5);
    }

    @Test
    void testGetNumeroPessoasIndefinido() {
        assertEquals(this.grupoTamanhoIndefinido.getNumeroPessoas(), 0);
    }

    @Test
    void testGetTamanhoDefinidoGTD() {
        assertTrue(this.grupoTamanhoDefinido.getTamanhoDefinido());
    }

    @Test
    void testGetTamanhoDefinidoGTI() {
        assertFalse(this.grupoTamanhoIndefinido.getTamanhoDefinido());
    }

    @Test
    void testGetGrupoAlunos() {
        assertEquals(this.grupoTamanhoDefinido.getGrupoAlunos().size(), 0);
    }

    @Test
    void testAdicionaAluno() {
        this.grupoTamanhoDefinido.adicionaAluno("1234");
        this.grupoTamanhoDefinido.adicionaAluno("5670");
        this.grupoTamanhoDefinido.adicionaAluno("9813");
        assertTrue(this.grupoTamanhoDefinido.getGrupoAlunos().contains("1234"));
        assertTrue(this.grupoTamanhoDefinido.getGrupoAlunos().contains("5670"));
        assertTrue(this.grupoTamanhoDefinido.getGrupoAlunos().contains("9813"));
        assertFalse(this.grupoTamanhoDefinido.getGrupoAlunos().contains("5643"));
    }

    @Test
    void testAdicionaMesmoAluno() {
        this.grupoTamanhoDefinido.adicionaAluno("1234");
        this.grupoTamanhoDefinido.adicionaAluno("1234");
        this.grupoTamanhoDefinido.adicionaAluno("1234");
        assertTrue(this.grupoTamanhoDefinido.getGrupoAlunos().contains("1234"));
        assertEquals(this.grupoTamanhoDefinido.getGrupoAlunos().size(), 1);
    }

    @Test
    void testToString() {
        assertEquals(this.grupoTamanhoDefinido.toString(), "Mondstat");
        assertEquals(this.grupoTamanhoIndefinido.toString(), "Liyue");
    }

    @Test
    void testEqualsTrue() {
        Grupo mondstat = new Grupo("Mondstat");
        assertEquals(this.grupoTamanhoDefinido, mondstat);
    }

    @Test
    void testEqualsFalse() {
        Grupo snezhnaya = new Grupo("Snezhnaya");
        assertNotEquals(this.grupoTamanhoDefinido, snezhnaya);
    }

    @Test
    void testHashCodeTrue() {
        Grupo mondstat = new Grupo("Mondstat");
        assertEquals(this.grupoTamanhoDefinido.hashCode(), mondstat.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Grupo snezhnaya = new Grupo("snezhnaya");
        assertNotEquals(this.grupoTamanhoDefinido.hashCode(), snezhnaya.hashCode());
    }

}