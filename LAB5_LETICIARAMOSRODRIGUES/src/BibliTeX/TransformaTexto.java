package BibliTeX;

import BibliTeX.Algoritmos.*;
import BibliTeX.Loggers.Logger;

import java.util.*;

/**
 * Classe responsável por atuar como uma biblioteca que, ao receber entradas do usuário,
 * realiza as manipulações especificadas sob os textos originais.
 * Mantém também informações sobre o uso da bibiotéca, bem como, um histórico de textos,
 * a quantificação de uso e listagens de dados armazenados.
 *
 * @author Leticia Ramos Rodrigues
 */
public class TransformaTexto {

    private boolean loggerDefinido;
    private BibliTeX.Loggers.Logger logger;
    private int quantidadeTransformacoes;
    private Set<String> setTextosOriginais;
    private List<String> listaHistoricoTransformacoes;
    private Map<String, AlgoritmoTransformacao> mapaTransformacoes;

    /**
     * Constrói um controlador responsável por realizar as operações desejadas
     * pelo usuário, com base, nos parâmetros passados na entrada.
     */
    public TransformaTexto(){
        this.setTextosOriginais = new LinkedHashSet<>();
        this.mapaTransformacoes = new LinkedHashMap<>();
        this.listaHistoricoTransformacoes = new ArrayList<>();
        adicionaTransformacoesBase();
    }

    /**
     * Constrói um controlador responsável por realizar as operações desejadas
     * pelo usuário, com base, nos parâmetros passados na entrada. Esse controlador
     * emite registros no terminal, conforme as características do logger escolhido.
     *
     *
     * @param loggerEntrada     O logger que emite resgistros sobre as operações.
     */
    public TransformaTexto(Logger loggerEntrada){
        if (loggerEntrada == null){
            throw new NullPointerException("LOGGER NULO");
        }
        this.loggerDefinido = true;
        this.logger = loggerEntrada;
        this.setTextosOriginais = new LinkedHashSet<>();
        this.mapaTransformacoes = new LinkedHashMap<>();
        this.listaHistoricoTransformacoes = new ArrayList<>();
        adicionaTransformacoesBase();
    }

    /**
     * Retorna o algoritmo de transformação, a partir do nome cadastrado como critério de identificação.
     *
     * @param nomeCadastrado  O nome da transformação cadastrada no controlador.
     * @return                O algoritmo de transformação associado ao nome cadastrado.
     */
    public AlgoritmoTransformacao getTransformacao(String nomeCadastrado){
        AlgoritmoTransformacao algoritmo = mapaTransformacoes.get(nomeCadastrado);
        if (algoritmo == null){
            throw new NullPointerException("TRANSFORMAÇÃO NÃO CADASTRADA");
        }
        utilizaLogger("[GET] TRANSFORMAÇÃO", nomeCadastrado);
        return algoritmo;
    }

    /**
     *
     * Verifica a existência de um algoritmo de transformação específico no sistema,
     * por meio do nome cadastrado como critério de identificação.
     * Retorna "true" se o algoritmo está cadastrado e "false" se algoritmo não está cadastrado no sistema.
     *
     * @param nomeCadastrado     O nome do algoritmo de transformação para a verificação.
     * @return                   O boolean que informa o estado de algoritmo de transformação no sistema.
     */
    public boolean existeTransformacao(String nomeCadastrado){
        if (nomeCadastrado == null){
            throw new NullPointerException("NOME NULO");
        }
        if (nomeCadastrado.isEmpty()){
            throw new IllegalArgumentException("NOME VAZIO");
        }
        if (nomeCadastrado.isBlank()){
            throw new IllegalArgumentException("NOME EM BRANCO");
        }

        utilizaLogger("EXISTE TRANSFORMAÇÃO", nomeCadastrado);
        if(mapaTransformacoes.keySet().contains(nomeCadastrado)){
            return true;
        }
        return false;
    }

    /**
     *
     *  Retorna o dado cadastrado no histórico, a partir da sua posição
     *
     * @param posicaoDado     A posição do dado desejado.
     * @return                A String que representa o dado registrado no histórico.
     */
    public String getDadoHistorico(int posicaoDado){
        utilizaLogger("[GET] DADO NO HISTÓRICO", String.valueOf(posicaoDado));

        String dadoResultado = listaHistoricoTransformacoes.get(posicaoDado);
        if (dadoResultado == null){
            return "DADO NÃO CADASTRADO NO HISÓRICO";
        }
        else{
            return dadoResultado;
        }
    }


    /**
     *
     * Cadastra um novo algoritmo de transformação no sistema, identificado pela chave correspondente ao nome dado pelo usuário.
     *
     * @param nomeTransformacao    O nome do algoritmo de transformação.
     * @param novoAlgoritmo        O algoritmo de transformação para cadastro.
     */
    public void cadastraTransformacao(String nomeTransformacao, AlgoritmoTransformacao novoAlgoritmo){
        if (nomeTransformacao == null){
            throw new NullPointerException("NOME NULO");
        }
        if (nomeTransformacao.isEmpty()){
            throw new IllegalArgumentException("NOME VAZIO");
        }
        if (nomeTransformacao.isBlank()){
            throw new IllegalArgumentException("NOME EM BRANCO");
        }

        if(novoAlgoritmo == null){
            throw new NullPointerException("ALGOTIMO");
        }

        utilizaLogger("CADASTRA TRASNFORMAÇÃO");
        this.mapaTransformacoes.put(nomeTransformacao, novoAlgoritmo);
    }

    /**
     *
     * Transforma o texto original do usuário, de acordo com o algoritmo de transformação especificado.
     *
     * @param nomeTransformacao      O nome do algoritmo de transformação.
     * @param textoOriginal          O texto para transformar.
     * @return                       A string que resulta da transformação do texto oeiginal.
     */
    public String transforma(String nomeTransformacao, String textoOriginal){
        if (nomeTransformacao == null){
            throw new NullPointerException("NOME NULO");
        }
        if (nomeTransformacao.isEmpty()){
            throw new IllegalArgumentException("NOME VAZIO");
        }
        if (nomeTransformacao.isBlank()){
            throw new IllegalArgumentException("NOME EM BRANCO");
        }

        if (textoOriginal == null){
            throw new NullPointerException("TEXTO NULO");
        }
        if (textoOriginal.isEmpty()){
            throw new IllegalArgumentException("TEXTO VAZIO");
        }
        if (textoOriginal.isBlank()){
            throw new IllegalArgumentException("TEXTO EM BRANCO");
        }

        utilizaLogger("TRANSFORMA", textoOriginal);
        AlgoritmoTransformacao tipoTransformacao = mapaTransformacoes.get(nomeTransformacao);
        String resultado = tipoTransformacao.transformaTexto(textoOriginal);
        adiconaTransformacao(textoOriginal, nomeTransformacao, resultado);
        return resultado;
    }

    /**
     *
     * Retorna o número de vezes total que o usuário implementou o método de transformação sobre um texto.
     *
     * @return    O inteiro que representa o numeo de transformações realizadas no sistema.
     */
    public int quantidadeTransformacoesRealizadas(){
        utilizaLogger("CONTAGEM DE TRANSFORMAÇÕES");
        return quantidadeTransformacoes;
    }

    /**
     *
     *  Constrói uma String que lista todos os textos originais manipulados pelo sistema.
     *  A lista omite repetições de textos iguais, de modo que apenas textos diferentes são inclusos.
     *
     * @return   A String que contém a listagem dos textos originais passados como parâmetro pelo usuário.
     */
    public String listagemTextosOriginais(){
        utilizaLogger("LISTAGEM DE TEXTOS ORIGINAIS");

        String lista = "";
        for (String textoOriginal : setTextosOriginais){
            lista += String.format("%s\n", textoOriginal);
        }

        if (listagemVazia(lista)){
            return "NÃO FORAM REALIZADAS TRANSFORMAÇÕES";
        }
        return String.format("LISTAGEM DE TEXTOS ORIGINAIS:\n%s", lista.trim());
    }

    /**
     * Constrói uma String que lista todos os dados do histórico atual do sistema.
     *
     * @return   A String que contém a listagem dos dados do histórico do sistema.
     */
    public String listagemHistoricoTransformacoes(){
        utilizaLogger("LISTAGEM DO HISTÓRICO DE TRANSFORMAÇÕES");

        String lista = "";
        for (int i = 0; i < listaHistoricoTransformacoes.size(); i++){
            lista += String.format("%d - %s\n", (i + 1), listaHistoricoTransformacoes.get(i));
        }

        if (listagemVazia(lista)){
            return "NÃO FORAM REALIZADAS TRANSFORMAÇÕES";
        }
        return String.format("LISTAGEM DO HISTÓRICO DE TRANSFORMAÇÕES:\n%s",lista.trim());
    }

    /**
     * Constrói uma String que lista todas as transformações cadastradas no sistema em ordem alfabética.
     * A listagem não considera duas transformações como diferentes, caso sejam objetos de uma mesma classe.
     * A lista está no formato "POSIÇÃO DA TRANSFORMAÇÃO - NOME TRASNFORMAÇÃO", em que, a posição está conforme a ordem alfabética.
     *
     * @return   A String que contém a listagem das transformações cadastradas no sistema.
     */
    public String listagemTransformacoesAlfabetica(){
        utilizaLogger("LISTAGEM DAS TRANSFORMAÇÕES CADASTRADAS");

        Set<String> setNomesTranformacoes = reunirNomesTransformacoes();
        List<String> listaNomesTransformacoes = new ArrayList<>();
        listaNomesTransformacoes.addAll(setNomesTranformacoes);
        listaNomesTransformacoes.sort(String::compareToIgnoreCase);

        String lista = "";
        for (int i = 0; i < listaNomesTransformacoes.size(); i ++){
            lista += String.format("%d - %s\n", (i + 1), listaNomesTransformacoes.get(i));
        }
        return String.format("LISTAGEM DAS TRANSFORMAÇÕES CADASTRADAS:\n%s",lista.trim());
    }


    /**
     *
     * Verifica se um Logger foi definido para a operação do sistema, caso verdade utiliza
     * o Logger para emitir os registros conforme as características desse.
     *
     * @param nomeMetodo          O nome do método utilizado pelo usuário.
     */
    private void utilizaLogger(String nomeMetodo) {
        if(loggerDefinido){
            logger.log(nomeMetodo);
        }
    }

    /**
     *
     * Verifica se um Logger foi definido para a operação do sistema, caso verdade utiliza
     * o Logger para emitir os registros conforme as características desse.
     *
     * @param nomeMetodo          O nome do método utilizado pelo usuário.
     * @param parametroInserido   O parâmetro passado pelo usuário para uso do método.
     */
    private void utilizaLogger(String nomeMetodo, String parametroInserido) {
        if(loggerDefinido){
            logger.log(nomeMetodo, parametroInserido);
        }
    }

    /**
     * Adiciona no sistema os dados decorrentes, como consequência, do uso do método responsável por transformar os textos da entrada.
     * Incrementa em apenas uma quantidade à contagem do total de transformações realizadas.
     * Registra o texto original no conjunto de todos os textos originais passados como parâmetro para transformação.
     * Registra no histórico os dados referentes à essa operação realizada.
     *
     * @param textoOriginal         O texto original entrado pelo usuário.
     * @param nomeTransformacao     O nome da transformação realizada sob o texto original.
     * @param textoResultado        O texto resultado da transformação do original.
     */
    private void adiconaTransformacao(String textoOriginal, String nomeTransformacao, String  textoResultado){
        this.quantidadeTransformacoes++;
        setTextosOriginais.add(textoOriginal);
        listaHistoricoTransformacoes.add(formataListaHistorico(textoOriginal, nomeTransformacao, textoResultado));
    }

    /**
     * Define o formato de exibição dos dados cadastrados no histórico,
     * utiliza-se o texto original, o nome da transformação realizada e o resultado do texto após a transformação.
     * A formatação segue o seguinte formato "TRANSFORMAÇÃO REALIZADA: NOME DO ALGORITMO.
     *                                        ORIGINAL: TEXTO ORIGINAL DA ENTRADA DO USUÁRIO.
     *                                        RESULTADO: O RESULTADO DO TEXTO APÓS A TRANSFORMAÇÃO."
     *
     * @param textoOriginal           O texto original entrado pelo usuário.
     * @param nomeTransformacao       O nome da transformação realizada sob o texto original.
     * @param textoResultado          O texto resultado da transformação do original.
     * @return                        A String que define como o dado é guardado no histórico.
     */
    private String formataListaHistorico(String textoOriginal, String nomeTransformacao, String textoResultado) {
        return String.format("TRANSFORMAÇÃO REALIZADA: %s. ORIGINAL: %s. RESULTADO: %s.", nomeTransformacao, textoOriginal, textoResultado);
    }

    /**
     *
     * Forma um HashSet responsável por reunir os nomes dos algoritmos de transformações cadastrados no sistema.
     * O HashSet tem a função de evitar a repetição de nomes de algoritmos, ou seja, de objetos de uma mesma classe.
     *
     * @return    O conjunto que contém os nomes instanciados às transformações.
     */
    private Set reunirNomesTransformacoes(){
        Set<String> setNomesTranformacoes = new LinkedHashSet<>();
        for (String chave : mapaTransformacoes.keySet()){
            String nomeTransformacao = mapaTransformacoes.get(chave).getNomeTransformacao();
            setNomesTranformacoes.add(nomeTransformacao);
        }
        return setNomesTranformacoes;
    }

    /**
     * Verifica se a String responsável por listar dados está vazia, indicando
     * a ausência de dados cadastrados no sistema.
     * Retorna true se a lista estiver vazia, ou seja, sem dados, caso contrário retorna false.
     *
     * @param listaEntrada      A lista resultado do método de listagem.
     * @return                  O boolean que indica se a lista está vazia.
     */
    private boolean listagemVazia(String listaEntrada){
        if (listaEntrada.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * Adiciona no sistema as transformações básicas ja disponíveis.
     * Os nomes cadastrados no sistema são iguais aos nomes instanciados aos algoritmos.
     */
    private void adicionaTransformacoesBase(){
        this.mapaTransformacoes.put("CaMeLcAsEfY", new CamelCasefy());
        this.mapaTransformacoes.put("clean", new RemoveSinaisPontuacao());
        this.mapaTransformacoes.put("InterrogaPraPontos", new ConverteInterrogacoesParaPontos());
        this.mapaTransformacoes.put("upperCase", new TransformaParaCaixaAlta());
        this.mapaTransformacoes.put("cleanSpaces", new RemoveEspacosEmBranco());
        this.mapaTransformacoes.put("foneticaJapao", new TransformaParaKatakana());
        this.mapaTransformacoes.put("semVogais", new RemoveVogais());
    }

}
