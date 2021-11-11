package agenda;

/**
 * Agenda responsável por criar e gerenciar contatos.
 * É possível cadastrar até 100 contatos, de modo que todos tem nome e sobrenome diferentes.
 * É possível favoritar apenas 10 favoritos.
 *
 * @author Leticia Ramos Rodrigues
 */
public class Agenda {
    private Contato[] contatosAgenda;
    private Contato[] contatosFavoritos;
    private static final int tamanhoAgenda = 100;
    private static final int tamanhoFavoritos = 10;

    /**
     * Constroí uma agenda de 100 contatos e 10 favoritos.
     * Agenda e os favoritos tem sempre o mesmo tamamho.
     */
    public Agenda() {
        this.contatosAgenda = new Contato[tamanhoAgenda];
        this.contatosFavoritos = new Contato[tamanhoFavoritos];
    }

    /**
     *
     * Retorna o contato cadastrado na posição especificada.
     *
     * @param posicao  A posição do contato na agenda.
     * @return         O contato cadastrado na agenda.
     */
    public Contato getContato(int posicao){
        return this.contatosAgenda[posicao - 1];
    }


    /**
     *
     * Retorna o contato favoritado na posição especificada.
     *
     * @param posicao  A posição do contato nos favoritos.
     * @return         O contato guardado nos favoritos.
     */
    public Contato getFavorito(int posicao){
        return this.contatosFavoritos[posicao - 1];
    }


    /**
     * Cadastra um contato na agenda.
     * São obrigatórios, apenas, os campos posicaoContato, nome  e telefone.
     * O campo sobrenome pode estar em branco ou vazio.
     *
     * @param posicaoContato    A posição do contato na agenda.
     * @param nome              O nome do contato.
     * @param sobrenome         O sobrenome do contato.
     * @param telefone          O telefone do contato.
     * @return                  A string que informa a validez do cadastro do contato.
     */
    public String cadastraContato(int posicaoContato, String nome, String sobrenome, String telefone) {
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }
        if (existeContato(nome + sobrenome) || nome.isBlank() || nome.isEmpty() || telefone.isEmpty()){
            throw new IllegalArgumentException("INFORMAÇÃO INVÁLIDA");
        }
        Contato contato = new Contato(nome, sobrenome, telefone);
        this.contatosAgenda[posicaoContato - 1] = contato;
        atualizaFavoritos();
        return "CADASTRO REALIZADO";
    }

    /**
     *
     * Altera o telefone do contato cadastrado na posição especificada.
     *
     * @param posicaoContato  A posição do contato na agenda.
     * @param novoTelefone    O novo telefone do contato especificado.
     * @return                A string que informa a validez da alteração de telefone.
     */
    public String alterarTelefone(int posicaoContato, String novoTelefone){
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        Contato contato = this.contatosAgenda[posicaoContato - 1];
        if (contato == null) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO");
        }

        contato.setTelefone(novoTelefone);
        return "O TELEFONE DO CONTATO FOI ALTERADO";

    }

    /**
     *
     * Remove o contato cadastrado na posição especificada.
     *
     * @param posicaoContato   A posição do contato na agenda.
     * @return                 A string que informa a validez da remoção do contato.
     */
    public String removerContato(int posicaoContato){
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        if (this.contatosAgenda[posicaoContato - 1] == null){
            throw new IllegalArgumentException("CONTATO INVÁLIDO");
        }

        this.contatosAgenda[posicaoContato - 1] = null;
        atualizaFavoritos();
        return "CONTATO REMOVIDO";

    }


    /**
     *
     * Exibe o contato da agenda na posição especificada.
     * A exebição é no formato "NOME SOBRENOME
     *                          TELEFONE
     *                          TAGS"
     * , com o adicional anterior de um coração no caso de favoritos.
     *
     * @param posicaoContato   A posição do contato na agenda.
     * @return                 A string que exibe o contato para o usuário, caso o contato exista.
     */
    public String exibirContato(int posicaoContato) {
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        Contato contato = this.contatosAgenda[posicaoContato - 1];
        if (contato == null) {
            throw new IllegalArgumentException("CONTATO INVÁLIDO");

        }

        return contato.toString();
    }

    /**
     *
     * Cria uma lista que contém todos os contatos da agenda.
     * A exebição é no formato de um contato por linha, no estilo "POSIÇÃO CONTATO - NOME SOBRENOME" para cada contato.
     *
     * @return A string da lista de todos os contatos da agenda, caso existam contatos.
     */
    public String listarContatos(){
        String listaDeContatos = "";
        if (getNumeroContatos() > 0) {
            for (int i = 0; i < this.contatosAgenda.length; i++) {
                if (this.contatosAgenda[i] != null) {
                    listaDeContatos += this.contatosAgenda[i].formataLista(i + 1);
                }
            }
            return listaDeContatos.trim();
        }
        else{
            return "SEM CONTATOS CADASTRADOS";
        }

    }

    /**
     * Adiciona um contato como um favorito na posição especificada.
     *
     * @param posicaoContato     A posição do contato na agenda.
     * @param posicaoFavorito    A posição od contato como favorito.
     * @return                   A string que informa a validez da adição do contato aos favoritos.
     */
    public String adicionarFavorito(int posicaoContato, int posicaoFavorito){
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }
        if (posicaoInvalida(posicaoFavorito, "Favorito")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        Contato contatoNovoFavorito = this.contatosAgenda[posicaoContato - 1];
        Contato contatoFavorito = this.contatosFavoritos[posicaoFavorito - 1];
        if (contatoNovoFavorito == null){
            throw new IllegalArgumentException("CONTATO INVÁLIDO");
        }

        if (existeFavorito(contatoNovoFavorito)){
            throw new IllegalArgumentException("CONTATO JÁ FAVORITADO");
        }

        if (contatoFavorito != null){
            contatoFavorito.retirarFavorito();
        }
        contatoNovoFavorito.definirFavorito();
        this.contatosFavoritos[posicaoFavorito - 1] = contatoNovoFavorito;
        return "CONTATO FAVORITADO NA POSIÇÃO " + posicaoFavorito + "!";
    }

    /**
     *
     * Remove o contato dos favoritos na posição especificada.
     *
     * @param posicaoFavorito   A posição do contato como favorito.
     * @return                  A string que informa a da remoção do contato dos favoritos.
     */
    public String removerFavorito(int posicaoFavorito){
        if (posicaoInvalida(posicaoFavorito, "Favorito")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        Contato contatoFavorito = this.contatosFavoritos[posicaoFavorito - 1];
        if (contatoFavorito == null){
            throw new IllegalArgumentException("FAVORITO INVÁLIDO");
        }

        contatoFavorito.retirarFavorito();
        this.contatosFavoritos[posicaoFavorito - 1] = null;
        return "FAVORITO REMOVIDO DA POSIÇÃO " + posicaoFavorito + "!";

    }

    /**
     *
     * Cria uma lista que contém apenas os contatos favoritos da agenda.
     * A exebição é no formato de um contato por linha, no estilo "POSIÇÃO CONTATO - NOME SOBRENOME" para cada contato.
     *
     * @return A string da lista de todos os contatos da agenda, caso existam favoritos.
     */
    public String listarContatosFavoritos() {
        String listaDeContatosFavoritos = "";
        if (getNumeroFavoritos() > 0) {
            for (int i = 0; i < this.contatosFavoritos.length; i++) {
                if (this.contatosFavoritos[i] != null) {
                    listaDeContatosFavoritos += this.contatosFavoritos[i].formataLista(i + 1);
                }
            }
            return listaDeContatosFavoritos.trim();
        }
        else{
            return "SEM CONTATOS FAVORITADOS";
        }

    }

    /**
     *
     * Adiciona uma tag na posição especificada para o contato cadastrado.
     * É possível adicionar a mesma tag em vários contatos.
     *
     * @param arrayContatos    Os contatos que recebem a tag.
     * @param tag              A tag para adicionar ao contato.
     * @param posicaoTag       A posição da tag no contato.
     * @return                 A string que informa a validez da adição da tag ao(s) contato(s).
     */
    public String adicionarTags(String[] arrayContatos, String tag, int posicaoTag){
        int j = 0;
        int[] posicaoContatos = new int[arrayContatos.length];
        for (String i : arrayContatos){
            int posicao = Integer.parseInt(i);
            posicaoContatos[j] = posicao;
            j++;

        }

        for (int posicaoContato : posicaoContatos){
            if(posicaoInvalida(posicaoContato, "Contato")){
                throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
            }
            else if (this.contatosAgenda[posicaoContato - 1] == null){
                throw new IllegalArgumentException("CONTATO INVÁLIDO");
            }
        }

        if (posicaoInvalida(posicaoTag, "Tags")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        for (int posicaoContato : posicaoContatos) {
            this.contatosAgenda[posicaoContato - 1].definirTags(posicaoTag, tag);
        }
        return "TAG(S) ADICIONADA(S)";
    }

    /**
     *
     * Remove a tag na posição especificada do contato cadastrado.
     * É possível remover apenas uma tag de um contato por vez.
     *
     * @param posicaoContato     A posição do contato na agenda.
     * @param posicaoTag         A posição da tag no contato.
     * @return                   A string que informa a de validez da remoção da tag.
     */
    public String removerTag(int posicaoContato, int posicaoTag){
        if (posicaoInvalida(posicaoContato, "Contato")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }
        if (posicaoInvalida(posicaoTag, "Tags")){
            throw new IllegalArgumentException("POSIÇÃO INVÁLIDA");
        }

        Contato contato = this.contatosAgenda[posicaoContato - 1];
        if (contato == null){
            throw new IllegalArgumentException("CONTATO INVÁLIDO");
        }
        if(contato.nullTag(posicaoTag)){
            throw new IllegalArgumentException("TAG INEXISTENTE");
        }

        contato.retirarTag(posicaoTag);
        return "TAG REMOVIDA";
    }


    /**
     *
     * Consulta todos os contatos da agenda, a partir do nome inserido como filtro.
     * Retorna apenas os contatos filtrados, ou seja, com mesmo nome do parâmtro.
     * No caso de uma busca sem resultados o retorno é vazio.
     *
     * @param targetNome   O nome padrão para a busca na agenda.
     * @return             A string que contém todos os contatos com o nome especificado.
     */
    public String consultarNome(String targetNome){
        String listaConsultaNome = "";
        for (int i = 0; i < this.contatosAgenda.length; i++){
            if  (this.contatosAgenda[i] != null && this.contatosAgenda[i].getNome().equals(targetNome)){
                listaConsultaNome += this.contatosAgenda[i].formataLista(i + 1);
            }
        }

        if (listaConsultaNome.equals("")){
            return "CONSULTA SEM RESULTADOS";
        }
        return listaConsultaNome.trim();
    }

    /**
     *
     * Consulta todos os contatos da agenda, a partir do sobrenome inserido como filtro.
     * Retorna apenas os contatos filtrados, ou seja, com mesmo sobrenome do parâmtro.
     * No caso de uma busca sem resultados o retorno é vazio.
     *
     * @param targetSobrenome   O sobrenome padrão para a busca na agenda.
     * @return                  A string que contém todos os contatos com o sobrenome especificado.
     */
    public String consultarSobrenome(String targetSobrenome){
        String listaConsultaSobrenome = "";
        for (int i = 0; i < this.contatosAgenda.length; i++){
            if  (this.contatosAgenda[i] != null && this.contatosAgenda[i].getSobrenome().equals(targetSobrenome)){
                listaConsultaSobrenome += this.contatosAgenda[i].formataLista(i + 1);
            }
        }

        if (listaConsultaSobrenome.equals("")){
            return "CONSULTA SEM RESULTADOS";
        }
        return listaConsultaSobrenome.trim();
    }


    /**
     *
     * Consulta todos os contatos da agenda, a partir da tag inserida como filtro.
     * Retorna apenas os contatos filtrados, ou seja, com a mesma tag do parâmtro.
     * No caso de uma busca sem resultados o retorno é vazio.
     *
     * @param targetTag   A tag padrão para a busca na agenda.
     * @return             A string que contém todos os contatos com a tag especificado.
     */
    public String consultarTag(String targetTag){
        String listaConsultaTag = "";
        for (int i = 0; i < this.contatosAgenda.length; i++){
            if  (this.contatosAgenda[i] != null && this.contatosAgenda[i].existeTag(targetTag)){
                listaConsultaTag += this.contatosAgenda[i].formataLista(i + 1);
            }
        }

        if (listaConsultaTag.equals("")){
            return "CONSULTA SEM RESULTADOS";
        }
        return listaConsultaTag.trim();
    }


    /**
     * Informa o estado de existência do contato na agenda de acordo com o nome completo do contato.
     * Retorna "true" caso o contato já tenha cadastro, caso contrário retorna "false".
     *
     * @param nomeEntrada    O nome completo inserido para o contato, na forma "NomeSobrenome".
     * @return               O boolean que informa o estado de existência.
     */
    public boolean existeContato(String nomeEntrada){
        Contato[] contatos = this.contatosAgenda;
        for (int i = 0; i < contatos.length; i++){
            if (contatos[i] != null){
                String nomeIteracao = contatos[i].getNomeCompleto();
                if(nomeIteracao.equals(nomeEntrada)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Informa o estado de existência do contato nos favoritos a de acordo com o nome completo do contato.
     * Retorna "true" caso o contato é favorito, caso contrário retorna "false".
     *
     * @param contatoFavorito    O contato para verificação de existência.
     * @return                   O boolean que informa o estado de existência.
     */
    private boolean existeFavorito(Contato contatoFavorito){
        Contato[] favoritos = this.contatosFavoritos;
        for (int i = 0; i < favoritos.length; i++){
            if (favoritos[i] != null){
                String nomeIteracao = favoritos[i].getNomeCompleto();
                if(nomeIteracao.equals(contatoFavorito.getNomeCompleto())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * Retorna apenas o número de contatos cadastrados na agenda.
     *
     * @return O número de contatos cadastrados na agenda.
     */
    private int getNumeroContatos(){
        int numeroContatos = 0;
        for (Contato contato : this.contatosAgenda){
            if (contato != null){
                numeroContatos++;
            }
        }
        return numeroContatos;
    }

    /**
     *
     * Retorna apenas o número de contatos favoritos da agenda.
     *
     * @return O número de contatos favoritos cadastrados na agenda.
     */
    private int getNumeroFavoritos(){
        int numeroFavoritos = 0;
        for (Contato contato : this.contatosFavoritos){
            if (contato != null){
                numeroFavoritos++;
            }
        }
        return numeroFavoritos;
    }

    /**
     *
     * Verifica se a posição inserida na entrada pelo usuário é válida para o caso estabelecido.
     * Para a agenda as posições válidas vão de 1 a 100.
     * Para os favoritos as posições válidas vão de 1 a 10.
     * Para as tags as posições válidas vão de 1 a 5.
     *
     * @param posicaoTarget   A posição inserida na entrada pelo usuário.
     * @param tipo            O tipo de caso da posição para verificação.
     * @return                O boolean que indica a validez da posição inserida na entrada.
     */
    private boolean posicaoInvalida(int posicaoTarget, String tipo){
        if (tipo.equals("Contato")){
            if (posicaoTarget < 1 || posicaoTarget > 100){
                return true;
            }
            else {
                return false;
            }
        }

        else if (tipo.equals("Favorito")){
            if (posicaoTarget < 1 || posicaoTarget > 10){
                return true;
            }
            else {
                return false;
            }
        }

        else if (tipo.equals("Tags")) {
            if (posicaoTarget < 1 || posicaoTarget > 5){
                return true;
            }
            else {
                return false;
            }
        }

        else {
            return false;
        }

    }

    /**
     *
     * Atualiza o campo contatosAgenda de acordo com o estado atual da agenda.
     * Caso ocorra a sobreescrição de um contato favorito
     * durante o cadastro ou remoção de um contato da agenda,
     * o contato antigo é removido dos favoritos.
     *
     */
    private void atualizaFavoritos(){
        Contato[] arrayFavoritos = this.contatosFavoritos;
        for (int i = 0; i < arrayFavoritos.length; i++){
            Contato contatoFavorito = arrayFavoritos[i];
            if (contatoFavorito != null){
                if(!(existeContato(contatoFavorito.getNomeCompleto()))){
                    removerFavorito(i + 1);
                }
            }
        }
    }

}
