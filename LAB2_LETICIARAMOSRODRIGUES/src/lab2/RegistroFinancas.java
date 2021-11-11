package lab2;

/**
 * Representação do registro de finanças de um estudante.
 * Responsável por armazenar o histórico de ganhos, a quantidade total de ganhos, a quantidade gasta com despesas,
 * e o valor ainda disponível para uso.
 *
 * @author Leticia Ramos Rodrigues
 */
public class RegistroFinancas {
    /**
     * Representação númerica da quantidade de dinheiro depositada inicialmente.
     *
     */
    private int dinheiroGuardado;
    /**
     * Representação númerica da quantidade de dinheiro disponível após o pagamento de despesas.
     *
     */
    private int dinheiroDisponivel;
    /**
     * Representação númerica da quantidade de dinheiro total gasto com despesas.
     *
     */
    private int dinheiroDespesas;
    /**
     * Representação númerica da quantidade de despesas pagas com detalhes.
     *
     */
    private int quantidadeDespesasDetalhes;
    /**
     * Representação em array das quantidades de dinheiro depositadas nos alocamentos disponíveis no registro.
     *
     */
    private int[] arrayHistoricoDeGanhos;
    /**
     * Representação em array dos detalhes das últimas cinco despesas pagas.
     *
     */
    private String[] arrayDespesasDetalhes = new String[5];


    /**
     * Constrói um registro a partir da quantidade inicial depositada
     * e da quantidade de alocamentos disponíveis no histórico.
     * Todo registro começa com o campo dinheiroDespesas e quantidadeDespesas nulo.
     * O campo dinheiroDisponivel é igual ao campo dinheiroGuardado.
     *
     * @param ganhosIniciais quantidade inicial depositada.
     * @param totalDeAlocamentos quantidade de alocamentos para depositos.
     */
    public RegistroFinancas(int ganhosIniciais, int totalDeAlocamentos){
        this.dinheiroGuardado = ganhosIniciais;
        this.dinheiroDisponivel = ganhosIniciais;
        this.dinheiroDespesas = 0;
        this.quantidadeDespesasDetalhes = 0;
        this.arrayHistoricoDeGanhos = new int[totalDeAlocamentos];
    }

    /**
     * Atualiza o campo arrayHistoricoDeGanhos.
     * Define o valor depositdado no  alocamento especificado.
     *
     * @param valorCentavos valor atualizado do alocamento.
     * @param posicaoGanho alocamento especifico no histórico.
     */
    public void adicionaGanhos(int valorCentavos, int posicaoGanho){
        arrayHistoricoDeGanhos[posicaoGanho - 1] = valorCentavos;
    }

    /**
     * Atualiza os campos dinheiroDespesas e dinheiroDisponivel.
     * Adiciona o valor informado como parâmetro ao valor total de despesas já contabilizado.
     * Calcula o valor disponível, a partir da subtração do valor de despesas do valor total no registro.
     *
     * @param valorCentavos quantidade de despesas adicionada ao total.
     */
    public void pagaDespesa(int valorCentavos){
        this.dinheiroDespesas += valorCentavos;
        this.dinheiroDisponivel = calculaDinheiroTotal() - dinheiroDespesas;
    }

    /**
     * Atualiza os campos dinheiroDespesas, dinheiroDisponivel, quantidadeDespesasDetalhes e arrayDespesasDetalhes.
     * Adiciona o valor informado como parâmetro ao valor total de despesas já contabilizado e os detalhes ao array de armazenamento.
     * Calcula o valor disponível, a partir da subtração do valor de despesas do valor total no registro.
     *
     * @param valorCentavos quantidade de despesas adicionada ao total.
     * @param detalhes informa detalhes sobre as despesas.
     */
    public void pagaDespesa(int valorCentavos, String detalhes){
        this.dinheiroDespesas += valorCentavos;
        this.dinheiroDisponivel = calculaDinheiroTotal() - dinheiroDespesas;

        int posicaoDetalhe = this.quantidadeDespesasDetalhes % 5;
        this.arrayDespesasDetalhes[posicaoDetalhe] = detalhes;
        this.quantidadeDespesasDetalhes ++;
    }


    /**
     * Calcula a quantidade total de dinheiro no registro financeiro.
     *
     * @return o valor númerico do total de dinheiro no registro financeiro.
     */
    private int calculaDinheiroTotal(){
        int soma = 0;
        for (int i = 0; i < this.arrayHistoricoDeGanhos.length; i ++){
            soma += this.arrayHistoricoDeGanhos[i];
        }
        soma += this.dinheiroGuardado;
        return soma;
    }

    /**
     * Constroi uma string contendo os dados dos alocamentos no histórico de ganhos.
     * A representação segue o formato "Número do alocamento - Dinheiro atualmente armazendo no alocamento".
     *
     * @return a representação em string dos registros no histórico de ganhos.
     */
    public String exibeGanhos() {
        String registroGanhos = "";
        for (int i = 0; i < arrayHistoricoDeGanhos.length; i++) {
            if (i == arrayHistoricoDeGanhos.length - 1){
                registroGanhos += (i + 1) + " - " + arrayHistoricoDeGanhos[i];
            }
            else {
                registroGanhos += (i + 1) + " - " + arrayHistoricoDeGanhos[i] + "\n";
            }
        }
        return registroGanhos;
    }

    /**
     * Constroi uma string contendo detalhes sobre os pagamentos das despesas. A rep
     * A representação segue o formato "Número da despesa - Detalhe especificado".
     *
     * @return a representaem string dos detalhes registrados das despesas.
     */
    public String listarDetalhes(){
        int quantidadeLinhas;
        if (this.quantidadeDespesasDetalhes < 5){
            quantidadeLinhas = this.quantidadeDespesasDetalhes % 5;
        }
        else{
            quantidadeLinhas = 5;
        }

        String strDetalhes = "";
        for (int i = 0; i < quantidadeLinhas; i++){
            if (i == 0){
                strDetalhes += (i + 1) + " - " + this.arrayDespesasDetalhes[i];
            }
            else {
                strDetalhes += "\n" + (i + 1) + " - " + this.arrayDespesasDetalhes[i];
            }
        }
        return strDetalhes;
    }

    /**
     * Retorna a String que informa o status geral do registro financeiro do estudante.
     * A representação segue o formato “Total recebidos: dinheiro recebido, Despesas totais: dinheiro
     * gasto, Total disponível: dinhiero disponível".
     *
     * @return a representação em String do status geral do registro das finanças.
     */
    public String toString(){
        String strDinheiroTotal = "Total recebidos: " + calculaDinheiroTotal() + ", ";
        String strDinhieroDespesas = "Despesas totais: " + dinheiroDespesas + ", ";
        String strDinhieroDisponivel = "Total disponível: " + dinheiroDisponivel;
        return strDinheiroTotal + strDinhieroDespesas + strDinhieroDisponivel;
    }

}


