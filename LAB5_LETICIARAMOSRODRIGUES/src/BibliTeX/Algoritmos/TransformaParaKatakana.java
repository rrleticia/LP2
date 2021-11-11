package BibliTeX.Algoritmos;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Classe responsável por transformar o texto original, passado como parâmetro pelo usuário,
 * de modo que as letras são transformadas com base na sua fonética para o alfabeto japonês de katakana.
 *
 * @author Leticia Ramos Rodrigues
 */
public class TransformaParaKatakana implements AlgoritmoTransformacao {
    private final String nomeTransformacao;
    private Map<String, String> mapaLetraKatakana;

    /**
     * Constrói um algoritmo de transformação do tipo TransformaParaKatakana, ao definir o nome que o identifica.
     */
    public TransformaParaKatakana(){
        this.nomeTransformacao = "foneticaJapao";
        this.mapaLetraKatakana = new LinkedHashMap<>();
        montaMapaLetraKatakana();
    }

    /**
     * Constroí um mapa que contém as letras associadas à fonética da língua portuguesa adaptada ao japonês.
     */
    private void montaMapaLetraKatakana() {
        mapaLetraKatakana.put("a", "ア");
        mapaLetraKatakana.put("b", "ベ");
        mapaLetraKatakana.put("c", "セ");
        mapaLetraKatakana.put("d", "デ");
        mapaLetraKatakana.put("e", "エ");
        mapaLetraKatakana.put("f", "エフィ");
        mapaLetraKatakana.put("g", "ゲ");
        mapaLetraKatakana.put("h", "アハ");
        mapaLetraKatakana.put("i", "イ");
        mapaLetraKatakana.put("j", "ジタ");
        mapaLetraKatakana.put("k", "カ");
        mapaLetraKatakana.put("l", "レイ");
        mapaLetraKatakana.put("m", "エメ");
        mapaLetraKatakana.put("n", "エネ");
        mapaLetraKatakana.put("o", "オ");
        mapaLetraKatakana.put("p", "ペ");
        mapaLetraKatakana.put("q", "ゲ");
        mapaLetraKatakana.put("r", "リ");
        mapaLetraKatakana.put("s", "エシ");
        mapaLetraKatakana.put("t", "テ");
        mapaLetraKatakana.put("u", "ウ");
        mapaLetraKatakana.put("v", "ウェ");
        mapaLetraKatakana.put("w", "ダビユ");
        mapaLetraKatakana.put("x", "シベユ");
        mapaLetraKatakana.put("y", "ユピソ");
        mapaLetraKatakana.put("z", "ゼ");
    }

    /**
     * Retorna o nome do algoritmo, bem como, definido na classe.
     *
     * @return     A String que representa o nome da transformação.
     */
    @Override
    public String getNomeTransformacao() {
        return this.nomeTransformacao;
    }


    /**
     * Transforma o texto original do usuário, de forma que as letras da língua portuguesa são
     * convertidas para a sua fonética base no alfabeto de katakana do japão.
     *
     * @param textoOriginal     O texto original do usuário.
     * @return                  A String que representa o texto transformado.
     */
    @Override
    public String transformaTexto(String textoOriginal) {
        String textoResultado = textoOriginal.toLowerCase();
        for (String letra : mapaLetraKatakana.keySet()){
            textoResultado = textoResultado.replace(letra, mapaLetraKatakana.get(letra));
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
        TransformaParaKatakana that = (TransformaParaKatakana) o;
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
