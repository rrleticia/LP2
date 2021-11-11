package ControleAlunos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    private Aluno aluno;

    @BeforeEach
    void testBeforeEach(){
        this.aluno = new Aluno("123456", "Kazuha", "Inazuma");
    }

    @Test
    void testConstrutor(){
        Aluno aluno = new Aluno("123456", "Kaeya", "Mondstat");
    }

    @Test
    void testConstrutorMatriculaNull(){
        try{
            Aluno aluno = new Aluno(null, "Kaeya", "Mondstat");
            fail("MATRICULA NULA");
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testConstrutorMatriculaVazia(){
        try{
            Aluno aluno = new Aluno("", "Kaeya", "Mondstat");
            fail("MATRICULA VAZIA");
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testConstrutorNomeNull(){
        try{
            Aluno aluno = new Aluno("123456", null, "Mondstat");
            fail("NOME DE ALUNO NULO");
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testConstrutorNomeVazio(){
        try{
            Aluno aluno = new Aluno("123456", "", "Mondstat");
            fail("NOME DE ALUNO VAZIO");
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testConstrutorCursoNull(){
        try{
            Aluno aluno = new Aluno("123456", "kaeya", null);
            fail("NOME DE CURSO NULO");
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }
    }

    @Test
    void testConstrutorCursoVazio(){
        try{
            Aluno aluno = new Aluno("123456", "kaeya", "");
            fail("NOME DE CURSO VAZIO");
        }catch (IllegalArgumentException iae){
            iae.printStackTrace();
        }
    }

    @Test
    void testGetNomeAluno() {
        assertEquals(this.aluno.getNomeAluno(), "Kazuha");
    }

    @Test
    void testGetCurso() {
        assertEquals(this.aluno.getCurso(), "Inazuma");
    }

    @Test
    void testGetMatricula() {
        assertEquals(this.aluno.getMatricula(), "123456");
    }

    @Test
    void testGetNumeroRespostas() {
        this.aluno.adicionaResposta();
        this.aluno.adicionaResposta();
        assertEquals(this.aluno.getNumeroRespostas(), 2);
    }

    @Test
    void testAdicionaResposta() {
        this.aluno.adicionaResposta();
        assertEquals(this.aluno.getNumeroRespostas(), 1);
    }

    @Test
    void testToString() {
        String strAluno = this.aluno.toString();
        assertEquals(strAluno, "123456 - Kazuha - Inazuma");
    }

    @Test
    void testEqualsTrue() {
        Aluno kaeya = new Aluno("123456", "Kaeya", "Mondstat");
        assertEquals(this.aluno, kaeya);
    }

    @Test
    void testEqualsFalse() {
        Aluno kaeya = new Aluno("123457", "Kazuha", "Inazuma");
        assertNotEquals(this.aluno, kaeya);
    }

    @Test
    void testHashCodeTrue() {
        Aluno kaeya = new Aluno("123456", "Kaeya", "Mondstat");
        assertEquals(this.aluno.hashCode(), kaeya.hashCode());
    }

    @Test
    void testHashCodeFalse() {
        Aluno kaeya = new Aluno("789012", "Kaeya", "Mondstat");
        assertNotEquals(this.aluno.hashCode(), kaeya.hashCode());
    }

}