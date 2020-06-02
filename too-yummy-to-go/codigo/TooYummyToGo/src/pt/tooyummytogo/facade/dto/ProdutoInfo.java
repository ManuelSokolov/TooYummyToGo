package pt.tooyummytogo.facade.dto;

import pt.tooyummytogo.domain.TipoDeProduto;
/*
 * Esta classe representa um DTO de um produto
 */
public class ProdutoInfo {

	private String nome;
	private int quantidadeExistente;
	private int quantidadePedida;
	private TipoDeProduto tipo;

	/**
	 * Constructor
	 * 
	 * @param nome - nome do produto
	 * @param quantidade - quantidade do produto
	 * @param tipo - tipo do produto
	 */
	public ProdutoInfo(String nome, int quantidade, TipoDeProduto tipo) {
		this.nome = nome;
		this.quantidadeExistente = quantidade;
		this.tipo = tipo;
	}

	/**
	 * Nome do produto
	 * @return nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * UC7- OPC
	 * 
	 * Devolve a quantidade pedida pelo consumidor para retirar ao produto
	 * 
	 * @return quantidadePedida 
	 */
	public int getQuantidadePedida() {
		return quantidadePedida;
	}

	/**
	 * Devolve o tipo de produto  
	 * @return tipo de produto
	 */
	public TipoDeProduto getTipo() {
		return tipo;
	}


	/**
	 * UC7-OPB
	 * Verifica se a quantidade deste produto e suficiente para satisfazer o pedido do 
	 * consumidor: caso a quantidade seja inferior ou igual a quantidade existente a 
	 * quantidade pedida e a quantidade, caso a quantidade seja superior a quantidade existente
	 * a quantidade pedida e a quantidade que existe do produto
	 * 
	 * @param quantidade - quantidade do produto
	 * @return true se a quantidade existente e maior que 0, false caso contrario 
	 */
	public boolean estaDisp(int quantidade) {
		if (quantidade <= this.quantidadeExistente && this.quantidadeExistente > 0) {
			this.quantidadePedida = quantidade;
			return true;
		} else if (this.quantidadeExistente > 0){
			this.quantidadePedida = this.quantidadeExistente;
			return true;
		}else {
			return false;
		}
	}
	

}
