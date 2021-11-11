package agenda;

/**
 *
 * Contato responsável por armazenar e manipular as informações inseridas para cadastro na agenda.
 * As informaçõe incluem nome, sobrenome, telefone, tags e favorito.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Contato {
    private String nome;
    private String sobrenome;
    private String telefone;
    private boolean favorito;
    private String[] arrayTags;

    /**
     * Constroí um contato a partir do nome, do sobrenome e do telefone.
     * Os atributos nome e telefone são obrigátiorios, enquanto sobrenome é opcional.
     * Todo contato é incializado com 5 tags e como não favorito.
     *
     * @param nome            O nome do contato.
     * @param sobrenome       O sobrenome do contato.
     * @param numeroTelefone  O telefone do contato.
     */
    public Contato(String nome, String sobrenome, String numeroTelefone) {
        if (nome == null){
            throw new NullPointerException("NOME NULO!");
        }
        else if(sobrenome == null){
            throw new NullPointerException("SOBRENOME NULO!");
        }
        else if (numeroTelefone == null){
            throw new NullPointerException("TELEFONE NULO!");
        }


        if (nome.isEmpty()){
            throw new IllegalArgumentException("NOME VAZIO!");
        }
        else if(nome.isBlank()){
            throw new IllegalArgumentException("NOME EM BRANCO!");
        }


        if (numeroTelefone.isEmpty()){
            throw new IllegalArgumentException("TELEFONE VAZIO!");
        }
        else if(numeroTelefone.isBlank()){
            throw new IllegalArgumentException("TELEFONE EM BRANCO!");
        }


        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = numeroTelefone;
        this.favorito = false;
        this.arrayTags = new String[5];
    }

    /**
     *
     * Retorna o nome do contato.
     *
     * @return A string do nome do contato.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     *
     * Retorna o sobrenome do contato.
     *
     * @return A string do sobrenome do contato.
     */
    public String getSobrenome() {
        return this.sobrenome;
    }

    /**
     *
     * Retorna o nome completo do contato, incluindo nome e sobrenome.
     * A string é no formato "NomeSobrenome".
     *
     * @return A string do nome completo do contato.
     */
    public String getNomeCompleto() {
        return nome + sobrenome;
    }

    /**
     *
     * Retorna o telefone do contato.
     *
     * @return A string do telefone do contato.
     */
    public String getTelefone() {
        return this.telefone;
    }

    /**
     *
     * Retorna estado de favorito do contato.
     *
     * @return O boolean de representação do favorito.
     */
    public boolean getFavorito() {
        return this.favorito;
    }

    /**
     * Retorna o array contendo as tags do contato.
     *
     * @return O array de tags do contato.
     */
    public String[] getArrayTags() {
        return this.arrayTags;
    }

    /**
     *
     * Define o campo telefone do contato.
     *
     * @param novoTelefone O novo numero de telefone do contato.
     */
    public void setTelefone(String novoTelefone) {
        this.telefone = novoTelefone;
    }

    /**
     *
     * Altera o campo arrayTags. Adiciona no contato a tag especificada, na posição desejada.
     *
     * @param posicaoTag A posição para a adição da tag.
     * @param tag        A tag para adicionar ao contato
     */
    public void definirTags(int posicaoTag, String tag) {
        this.arrayTags[posicaoTag - 1] = tag;
    }

    /**
     *
     * Altera o campo arrayTags. Remove do contato a tag da posição especificada.
     *
     * @param posicaoTag A posição para a remoção da tag.
     */
    public void retirarTag(int posicaoTag) {
        this.arrayTags[posicaoTag - 1] = null;
    }

    /**
     * Define o contato como favorito.
     */
    public void definirFavorito() {
        this.favorito = true;
    }

    /**
     * Retira o favorito do contato.
     */
    public void retirarFavorito() {
        this.favorito = false;
    }

    /**
     *
     * Retorna o valor indicativo da existência da tag na posição especificada.
     * Caso o contato esteja marcado com uma tag na posição o retorno é "true",
     * caso o contrário o retorno é "false".
     *
     * @param posicao   A posição da tag para verficação de existência.
     * @return          O boolean indicativo do estado de existência da tag.
     */
    public boolean nullTag(int posicao){
        if (this.arrayTags[posicao - 1] == null){
            return true;
        }
        return false;
    }

    /**
     *
     * Retorna o valor indicativo da existência da tag no contato.
     * Caso o contato esteja marcado com a tag especificada o retorno é "true",
     * caso o contrário o retorno é "false".
     *
     * @param targetTag A tag para verificação de existência.
     * @return O boolean indicativo do estado de existência da tag.
     */
    public boolean existeTag(String targetTag) {
        for (String tag : arrayTags) {
            if (targetTag.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * Formata a exebição do contato para listagem da agenda.
     * A exebição é no formato "POSIÇÃO NA AGENDA - NOME SOBRENOME".
     *
     * @param posicao A posicao do contato na agenda.
     * @return A string de exebição do contato em lista.
     */
    public String formataLista(int posicao) {
        return String.format("%d - %s %s\n", posicao, this.nome, this.sobrenome);
    }

    /**
     *
     * Constroí uma string que lista todas as tags de um contato.
     * A string é no formato "tag 1, tag 2, tag 3, tag 4, tag 5", caso todas as tags existam.
     *
     * @return A string de listagem das tags do contato.
     */
    private String formataTags() {
        String listaTags = "";
        for (String tag : arrayTags) {
            if (tag != null) {
                listaTags += String.format("%s ", tag);
            }
        }
        return listaTags.trim();
    }

    /**
     *
     * Formata a exebição do contato para exebição ao usuário.
     * A exebição é no formato "NOME SOBRENOME
     *                          TELEFONE
     *                          TAGS"
     * , com o adicional anterior de um coração no caso de favoritos.
     *
     * @return A string de exebição do contato na agenda.
     */
    public String toString() {
        String contato = String.format("%s %s\n%s\n%s", this.nome, this.sobrenome, this.telefone, formataTags());
        String exibicaoContato = "";
        if (this.favorito) {
            exibicaoContato = "❤️ " + contato;
        } else {
            exibicaoContato = contato;
        }
        return exibicaoContato;
    }

}


