package BibliTeX.Algoritmos;

import java.util.Objects;

/**
 * Classe responsável por transformar o texto original, passado como parâmetro pelo usuário,
 * de modo que os sinais de pontuação são removidos.
 *
 * @author Leticia Ramos Rodrigues
 */
public class RemoveSinaisPontuacao implements AlgoritmoTransformacao {
    private final String nomeTransformacao;

    /**
     * Constrói um algoritmo de transformação do tipo RemoveSinaisPontuacao ao definir do nome que o identifica.
     */
    public RemoveSinaisPontuacao(){
        this.nomeTransformacao = "clean";
    }

    /**
     * Retorna o nome do algoritmo, bem como, definido na classe.
     *
     * @return     A String que representa o nome da transformação.
     */
    @Override
    public String getNomeTransformacao(){
        return this.nomeTransformacao;
    }

    /**
     * Transforma o texto original do usuário, com base na remoção dos sinais de pontuação.
     *
     * @param textoOriginal     O texto original do usuário.
     * @return                  A String que representa o texto transformado.
     */
    @Override
    public String transformaTexto(String textoOriginal) {
        return textoOriginal.replaceAll("\\p{Punct}", "");
    }

    /**
     * Compara dois objetos, com o critério de igualdade estabelecido como o nome da transformação.
     * Retorna "true" em caso de igualdade e "false" em caso de diferença.
     *
     * @param o  O objeto alvo da comparação.
     * @return   O boolean que representa a igualdade entre os objetos.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveSinaisPontuacao that = (RemoveSinaisPontuacao) o;
        return Objects.equals(nomeTransformacao, that.nomeTransformacao);
    }


    /**
     * Gera o hashcode do objeto, a partir do critério de identificação única definido como o atributo "nomeTransformacao".
     *
     * @return    A representação em hashcode do objeto.
     */
    @Override
    public int hashCode() {
        return 31 * Objects.hash(nomeTransformacao);
    }

}
