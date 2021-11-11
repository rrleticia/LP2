package com.matheusgr.lunr.documento;

import biblitex.TransformaTexto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Documento base java. As palavras-chave da linguagem ainda são preservadas
 * (como public, private, etc), mas elimina-se documentação e comentários.
 */
public class DocumentoJava extends DocumentoAbstrato {

	/**
	 * Cria o DocumentoJava, extraindo o texot base.
	 *
	 * @param id       ID do documento a ser criado.
	 * @param original Código java original.
	 */
	public DocumentoJava(String id, String original) {
		super(id, original);
		setLimpo(definirTextoLimpo(original));
	}

	/**
	 *
	 * Retorna o texto limpo, como definido pela biblioteca de transformação, para documentos do tipo java.
	 * Esse texto não contém caracteres de pontuação.
	 *
	 * @param original      O texto original guardado no documento
	 * @return              O texto limpo, ou seja, sem caracteres de pontuação.
	 */
	private String definirTextoLimpo(String original) {
		var transformaTexto = new TransformaTexto();
		var txt = transformaTexto.transforma(TransformaTexto.Algoritmos.java, original);
		return transformaTexto.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
	}

	/**
	 *
	 * Retorna o mapa de metadados referentes ao conteúdo do documento.
	 * Caso o mapa ainda não esteja inicializado, esse é construído ao retorno.
	 * Caso o mapa, já tenha sido inicializado, apenas é necessário retornar esse ao usuário.
	 *
	 * @return   O mapa de metadados referentes ao documento do tipo java.
	 */
	@Override
	public Map<String, String> getMetadados() {
		if (this.metadados != null) {
			return this.metadados;
		}
		this.metadados = extractData(super.getOriginal());
		this.metadados.put("LINHAS", "" + super.getOriginal().chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + super.getLimpo().length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "java");
		return this.metadados;
	}

	/**
	 *
	 * Extraí documento os dados necessários referentes a documentos to tipo java.
	 * Esses metadados particulares de documentos do tipo java são o número de imports e o autor da classe.
	 * Os dados são inseridos em conjunto com os demais metadados.
	 *
	 * @param original2           A String do texto original do documento.
	 * @return                    Mapa que contém os metadados específicos de documentos do tipo java.
	 */
	private Map<String, String> extractData(String original2) {
		Map<String, String> metadados2 = new HashMap<>();
		metadados2.put("IMPORTS", "" + ((super.getLimpo().length() - super.getLimpo().replaceAll("import ", "").length()) / 7));
		metadados2.put("AUTHOR", "" + (super.getOriginal().indexOf("@author") != -1 ? "TRUE" : ""));
		return metadados2;
	}

}
