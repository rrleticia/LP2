package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Documento de texto simples. Não precisa de tratamento complexos nem tem
 * metadados próprios.
 */
public class DocumentoTexto extends DocumentoAbstrato {

	/**
	 * Construtor padrão do documento de texto.
	 * @param id ID do documento.
	 * @param txt Texto do documento.
	 */
	public DocumentoTexto(String id, String txt) {
		super(id, txt);
		setLimpo(definirTextoLimpo(txt));
	}

	/**
	 *
	 * Retorna o texto limpo, como definido pela biblioteca de transformação, para documentos do tipo html.
	 * Esse texto não contém caracteres de pontuação.
	 *
	 * @param txt           O texto original guardado no documento
	 * @return              O texto limpo, ou seja, sem caracteres de pontuação.
	 */
	private String definirTextoLimpo(String txt){
		return (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.clean, txt).strip();
	}

	/**
	 *
	 * Retorna o mapa de metadados referentes ao conteúdo do documento.
	 * Caso o mapa ainda não esteja inicializado, esse é construído ao retorno.
	 * Caso o mapa, já tenha sido inicializado, apenas é necessário retornar esse ao usuário.
	 *
	 * @return   O mapa de metadados referentes ao documento de texto.
	 */
	@Override
	public Map<String, String> getMetadados() {
		if (this.metadados != null) {
			return this.metadados;
		}
		this.metadados = new HashMap<String, String>();
		this.metadados.put("LINHAS", "" + super.getOriginal().chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + super.getLimpo().length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "txt");
		return this.metadados;
	}


}
