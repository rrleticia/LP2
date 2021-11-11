package com.matheusgr.lunr.documento;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Repositório de documentos. O repositório pode ter opreações simples de busca,
 * mas a lógica de ranking, limitação e ordenação deve ficar em outra entidade.
 * 
 * O ID de um documento é único.
 */
class DocumentoRepository {

	private Map<String, Documento> documentos;
	private ValidadorDocumentos validador;

	/**
	 * Construção padrão do repositório de documentos.
	 */
	DocumentoRepository() {
		this.documentos = new HashMap<String, Documento>();
		this.validador = new ValidadorDocumentos();
	}

	/**
	 * Adiciona o documento. O documento é validado para garantir a consistência do
	 * documento (sem termos e id vazios).
	 * 
	 * @param d Documento a ser adicionado.
	 */
	void adiciona(Documento d) {
		this.validador.validacao(d.getId(), d.getTexto());
		this.documentos.put(d.getId(), d);
	}

	/**
	 * Recupera um documento do repositório.
	 * 
	 * @param id ID do documento.
	 * @return Documento, caso exista.
	 */
	Optional<Documento> recupera(String id) {
		this.validador.validacao(id);
		return Optional.ofNullable(this.documentos.get(id));
	}

	/**
	 * Retorna o total de documentos cadastrados.
	 * 
	 * @return O total de documentos cadastrados.
	 */
	int totalDocumentos() {
		return this.documentos.size();
	}

	/**
	 * Realiza uma busca pelos termos.
	 * 
	 * @param termo Termo a ser buscado.
	 * @return Conjunto de documentos com o termo.
	 */
	public Set<Documento> busca(String termo) {
		return this.documentos.values().stream()
					.filter((x) -> Arrays.binarySearch(x.getTexto(), termo) > 0)
					.collect(Collectors.toSet());
	}

	/**
	 *
	 * Realiza uma busca pelos metadados e valores dos metadados.
	 *
	 * @param metadadoBuscado       O metadado a ser buscado.
	 * @param valorMetadadoBuscado  O valor referente ao metadado.
	 * @return                      Conjunto de documentos com os metadados buscados.
	 */
	public Set<Documento> busca(String metadadoBuscado, String valorMetadadoBuscado) {
		List<Documento> todosDocumentos = new ArrayList<Documento>(documentos.values());

		Set<Documento> reunirDocumentos = new HashSet<>();
		for(Documento d : todosDocumentos){
			Map<String, String> metadados = d.getMetadados();
			if(metadados.containsKey(metadadoBuscado) && valorMetadadoBuscado.equals(d.getMetadados().get(metadadoBuscado))) {
				reunirDocumentos.add(d);
			}
		}
		return reunirDocumentos;
	}


	/**
	 * Retorna uma lista que contém todas as chaves, ou seja, documentos cadastrados no repositório.
	 * A lista é retornada sem modificações, sejam essas de ordenação ou conteúdo.
	 *
	 * @return     Lista inalterada que contém todos os documentos armazenados no repositório.
	 */
	public List<Documento> getTodosDocumentos() {
		return new ArrayList<>(documentos.values());
	}

}
