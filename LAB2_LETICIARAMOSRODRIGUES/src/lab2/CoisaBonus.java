package lab2;

public class CoisaBonus {

    public static void main(String[] args) {
        descanso();
        System.out.println("-----");
        disciplina();
        System.out.println("-----");
        financas();
        System.out.println("-----");
    }

    public static void descanso() {
        Descanso descanso = new Descanso();
        System.out.println(descanso.getStatusGeral());

        descanso.defineHorasDescanso(30);
        descanso.defineNumeroSemanas(1);
        descanso.definirEmoji("u.u");
        System.out.println(descanso.getStatusGeral());

        descanso.defineHorasDescanso(40);
        descanso.defineNumeroSemanas(4);
        System.out.println(descanso.getStatusGeral());

    }


    public static void disciplina() {
        Disciplina prog2 = new Disciplina("PROGRAMACAO 2", 5);
        System.out.println(prog2.aprovado());

        prog2.cadastraNota(1, 10.0);
        prog2.cadastraNota(2, 10.0);
        prog2.cadastraNota(3, 10.0);
        prog2.cadastraNota(4, 10.0);
        System.out.println(prog2.aprovado());
        System.out.println(prog2.toString());

        Disciplina prog1 = new Disciplina("PROGRAMACAO 1", 4, new int[] {1, 2, 4, 3});
        System.out.println(prog1.aprovado());
        prog1.cadastraNota(1, 10.0);
        prog1.cadastraNota(2, 10.0);
        prog1.cadastraNota(3, 10.0);
        System.out.println(prog1.aprovado());
        System.out.println(prog1.toString());

    }

    public static void financas() {
        int dinheiroGuardado = 10000;
        int historicoDeGanhos = 4;
        RegistroFinancas minhaFinanca = new RegistroFinancas(dinheiroGuardado, historicoDeGanhos);

        minhaFinanca.adicionaGanhos(12000, 1);
        minhaFinanca.adicionaGanhos(72100, 2);
        minhaFinanca.pagaDespesa(20000, "ABC");
        System.out.println(minhaFinanca.listarDetalhes());

        minhaFinanca.adicionaGanhos(7210, 2);
        minhaFinanca.pagaDespesa(5000, "OMG");
        minhaFinanca.pagaDespesa(20000, "UHU");
        minhaFinanca.pagaDespesa(20000, "SCR");
        minhaFinanca.pagaDespesa(20000, "CRY");
        System.out.println(minhaFinanca.listarDetalhes());
    }

}

