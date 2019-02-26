package com.Faciplac.SGQ.util;

public enum TaxonomiaDeBloomEnum {
	n1("conhecimento"),
	n2("compreensão"),
	n3("aplicação"),
	n4("análise"),
	n5("síntese"),
	n6("avaliação");
	
	public String valorClassificacaoDeBloom;
	TaxonomiaDeBloomEnum(String valor) {
		valorClassificacaoDeBloom = valor;
	}

	public String getValor() {
		return valorClassificacaoDeBloom;
	}
}
