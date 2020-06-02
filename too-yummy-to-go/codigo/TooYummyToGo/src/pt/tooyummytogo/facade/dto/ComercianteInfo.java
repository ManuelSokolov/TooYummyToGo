package pt.tooyummytogo.facade.dto;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.ListaProdutoVenda;
import pt.tooyummytogo.domain.TipoDeProduto;
/*
 * Esta classe representa um DTO de comerciante
 */
public class ComercianteInfo {

	private String nome;
	private PosicaoCoordenadas localVenda;
	private ListaProdutoVenda lpv;
	private List<TipoDeProduto> tiposProduto;
	
	/**
	 * Constructor
	 * 
	 * @param nome - nome do comerciante
	 * @param lpv - lista de produtos para venda
	 * @param localDeVenda - local de venda do comerciante
	 * @requires nome != null && lpv != null && localDeVenda != null
	 */
	public ComercianteInfo(String nome, ListaProdutoVenda lpv, PosicaoCoordenadas localDeVenda) {
		this.nome = nome;
		this.localVenda = localDeVenda;
		this.lpv = lpv;
	}
	
	/**
	 * Devolve o nome do comerciante
	 * @return nome mechant name
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Devolve a lista de produtos do comerciante
	 * @return devolve a lista de produtos do comerciante
	 */
	public ListaProdutoVenda getLpv() {
		return lpv;
	}
	
	/**
	 * Devolve o local de venda do comerciante
	 * @return localVenda do comerciante
	 */
	public PosicaoCoordenadas getLocalVenda() {
		return localVenda;
	}
	
	/**
	 * Devolve uma lista de tipos de produto do comerciante
	 * @return lista de produtos do comerciante
	 */
	public List<TipoDeProduto> getTiposProduto() {
		return new ArrayList<>(tiposProduto);
	}

	

}
