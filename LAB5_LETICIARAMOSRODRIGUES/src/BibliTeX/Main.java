package BibliTeX;

import BibliTeX.Algoritmos.TransformaParaKatakana;
import BibliTeX.Loggers.*;

public class Main {
    public static void main(String[] args) {

        Logger bl = new BarbieLogger();
        TransformaTexto tt = new TransformaTexto(bl);
        tt.cadastraTransformacao("foneticaJapao", new TransformaParaKatakana());
        tt.transforma("CaMeLcAsEfY", "oi, como vc vai?");
        tt.transforma("clean", "oi, como vc vai?");
        tt.quantidadeTransformacoesRealizadas();
        tt.listagemTransformacoesAlfabetica();
        tt.listagemHistoricoTransformacoes();
        tt.listagemTextosOriginais();
        bl.toString();
        System.out.println("\n");

        imprimir();
        imprimirLoggers();
    }


    private static void imprimir(){
        TransformaTexto transformaTexto = new TransformaTexto();
        System.out.println(transformaTexto.listagemTransformacoesAlfabetica());
        System.out.println(transformaTexto.transforma("InterrogaPraPontos", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("upperCase", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("cleanSpaces", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("CaMeLcAsEfY", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("clean", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("foneticaJapao", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
        System.out.println(transformaTexto.transforma("foneticaJapao", "oi, como vc vai?"));
        System.out.println(transformaTexto.transforma("semVogais", "oi, como vc vai?"));
    }

    private static void imprimirLoggers(){
        Logger ctl = new ContagemLogger();
        TransformaTexto tt = new TransformaTexto(ctl);
        tt.cadastraTransformacao("foneticaJapao", new TransformaParaKatakana());
        System.out.println(tt.transforma("CaMeLcAsEfY", "oi, como vc vai?"));
        System.out.println(tt.transforma("clean", "oi, como vc vai?"));
        System.out.println(tt.quantidadeTransformacoesRealizadas());
        System.out.println(tt.listagemTransformacoesAlfabetica());
        System.out.println(tt.listagemHistoricoTransformacoes());
        System.out.println(tt.listagemTextosOriginais());
        System.out.println(ctl.toString());
        System.out.println("\n");


        Logger cl = new ConsoleLogger();
        TransformaTexto tt2 = new TransformaTexto(cl);
        tt2.cadastraTransformacao("foneticaJapao", new TransformaParaKatakana());
        System.out.println(tt2.transforma("CaMeLcAsEfY", "oi, como vc vai?"));
        System.out.println(tt2.transforma("clean", "oi, como vc vai?"));
        System.out.println(tt2.quantidadeTransformacoesRealizadas());
        System.out.println(tt2.listagemTransformacoesAlfabetica());
        System.out.println(tt2.listagemHistoricoTransformacoes());
        System.out.println(tt2.listagemTextosOriginais());
        System.out.println(cl.toString());
        System.out.println("\n");

        Logger bpl = new BreakPointLogger("CONTAGEM DE TRANSFORMAÇÕES");
        TransformaTexto tt3 = new TransformaTexto(bpl);
        tt3.cadastraTransformacao("foneticaJapao", new TransformaParaKatakana());
        System.out.println(tt3.transforma("CaMeLcAsEfY", "oi, como vc vai?"));
        System.out.println(tt3.transforma("clean", "oi, como vc vai?"));
        System.out.println(tt3.quantidadeTransformacoesRealizadas());
        System.out.println(tt3.listagemTransformacoesAlfabetica());
        System.out.println(tt3.listagemHistoricoTransformacoes());
        System.out.println(tt3.listagemTextosOriginais());
        System.out.println(bpl.toString());
        System.out.println("\n");

        Logger tcl = new TimeConsoleLogger();
        TransformaTexto tt4 = new TransformaTexto(tcl);
        tt4.cadastraTransformacao("foneticaJapao", new TransformaParaKatakana());
        System.out.println(tt4.transforma("CaMeLcAsEfY", "oi, como vc vai?"));
        System.out.println(tt4.transforma("clean", "oi, como vc vai?"));
        System.out.println(tt4.quantidadeTransformacoesRealizadas());
        System.out.println(tt4.listagemTransformacoesAlfabetica());
        System.out.println(tt4.listagemHistoricoTransformacoes());
        System.out.println(tt4.listagemTextosOriginais());
        System.out.println(tcl.toString());
        System.out.println("\n");

    }
}
