package BibliTeX.Algoritmos;

import java.lang.String;
import java.util.Objects;

/**
 * Classe responsável por transformar o texto original, passado como parâmetro pelo usuário,
 * de modo que as letras em posições pares ficam em caixa alta e as letras em posições ímpares ficam em caixa baixa.
 *
 * @author Leticia Ramos Rodrigues
 */
public class CamelCasefy implements AlgoritmoTransformacao {
    private final String nomeTransformacao;

    /**
     * Constrói um algoritmo de transformação do tipo CamelCasely ao definir o nome que o identifica.
     */
    public CamelCasefy(){
        this.nomeTransformacao = "CaMeLcAsEfY";
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
     * Transforma o texto original do usuário, de forma que as letras em posições pares (incluindo o zero)
     * convertam-se à formatação em caixa alta e as demais, em posições ímpares, convertam-se à formatação em caixa baixa.
     *
     * @param textoOriginal     O texto original do usuário.
     * @return                  A String que representa o texto transformado.
     */
    @Override
    public String transformaTexto(String textoOriginal) {
        String textoResultado = "";
        for (int i = 0; i < textoOriginal.length(); i++){
            if (i % 2 == 0){
                textoResultado += Character.toUpperCase(textoOriginal.charAt(i));
            }
            else {
                textoResultado += Character.toLowerCase(textoOriginal.charAt(i));
            }
        }
        return textoResultado;
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
        CamelCasefy that = (CamelCasefy) o;
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
