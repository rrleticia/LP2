package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;
import java.util.HashMap;
import java.util.Map;

/**
 * DocumentoHTML representa e extrai dados de um HTML.
 * 
 * Os termos extraídos são obtidos dos textos dos nós do HTML. São ignorados
 * nome de tags ou de propriedades. Por não ser um texto bem formulado, limpa-se
 * da melhor forma possível o HTML.
 * 
 * Os metadados são obtidos de características do documento, mas de detalhes
 * descritos na tag HEAD.
 */
public class DocumentoHtml extends DocumentoAbstrato {

	private static final String HEAD_METADADO = "HEAD";

	/**
	 * Construtor padrão. Realiza o processamento de extração do HTML.
	 *
	 * @param id ID do documento a ser criado.
	 * @param original HTML do documento a ser criado.
	 */
	public DocumentoHtml(String id, String original) {
		super(id, original);
		setLimpo(definirTextoLimpo(original));
	}

	/**
	 *
	 * Retorna o texto limpo, como definido pela biblioteca de transformação, para documentos do tipo texto.
	 * Esse texto não contém caracteres de pontuação.
	 *
	 * @param original      O texto original guardado no documento
	 * @return              O texto limpo, ou seja, sem caracteres de pontuação.
	 */
	private String definirTextoLimpo(String original){
		var transformaTexto = new TransformaTexto();
		var txt = transformaTexto.transforma(TransformaTexto.Algoritmos.html, original);
		return transformaTexto.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
	}

	/**
	 * Constrói uma String que define a representação textual do documento do tipo html.
	 * A formatação está no formato "=== ID DO DOCUMENTO
	 *                               HEADER DO DOCUMENTO
	 *                               ===
	 *                               O TEXTO LIMPO DO DOCUMENTO".
	 *
	 * @return   A String de representação textual do documento.
	 */
	@Override
	public String toString() {
		return "===" + super.getId() + System.lineSeparator() + this.getMetadados().get(HEAD_METADADO)
				+ System.lineSeparator() + "===" + super.getLimpo();
	}


	/**
	 *
	 * Retorna o mapa de metadados referentes ao conteúdo do documento.
	 * Caso o mapa ainda não esteja inicializado, esse é construído ao retorno.
	 * Caso o mapa, já tenha sido inicializado, apenas é necessário retornar esse ao usuário.
	 *
	 * @return   O mapa de metadados referentes ao documento do tipo html.
	 */
	@Override
	public Map<String, String> getMetadados() {
		if (this.metadados != null) {
			return this.metadados;
		}
		this.metadados = extractHead(super.getOriginal());
		this.metadados.put("LINHAS", "" + super.getOriginal().chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + super.getLimpo().length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "html");
		return this.metadados;
	}

	/**
	 *
	 * Extraí documento os dados necessários referentes a documentos to tipo java.
	 * Esses metadados particulares de documentos do tipo html são a quantidade de tags,
	 * (estimada a partir da quantidade de símbolos) e o corpo da tag "head" por completo.
	 * Os dados são inseridos em conjunto com os demais metadados.
	 *
	 * @param original2           A String do texto original do documento.
	 * @return                    Mapa que contém os metadados específicos documentos do tipo html.
	 */
	private Map<String, String> extractHead(String original2) {
		Map<String, String> metadados2 = new HashMap<>();
		metadados2.put("BRUTE_TAGS", "" + super.getOriginal().chars().filter((value) -> '<' == value).count());
		String meta = "";
		int headStart = original2.toLowerCase().indexOf("<head>");
		if (headStart != -1) {
			int headEnd = original2.toLowerCase().indexOf("</head>");
			if (headEnd != -1) {
				meta = original2.substring(headStart, headEnd);
			}
		}
		metadados2.put(HEAD_METADADO, meta);
		return metadados2;
	}


}
