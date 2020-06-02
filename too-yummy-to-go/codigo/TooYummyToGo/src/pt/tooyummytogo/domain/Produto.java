package pt.tooyummytogo.domain;
/*
 * Esta classe representa um produto
 */
public class Produto {

	private String nome;
	private int quantidade;
	private TipoDeProduto tipo;

	/**
	 * Constructor
	 * 
	 * @param nome - nome do produto
	 * @param quantidade - quantidade do produto
	 * @param tipo - nome do tipo de produto ao qual produto pertence
	 * @requires nome!= null && quantidade != null && tipo != null
	 */
	public Produto(String nome, int quantidade, TipoDeProduto tipo) {
		this.nome = nome;
		this.quantidade = quantidade;
		this.tipo = tipo;
	}

	/**
	 * Devolve o nome do produto
	 * @return nome do produto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Devolve a quantidade do produto
	 * @return quantidade do produto
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * Devolve o tipo de produto deste produto
	 * @return tipo de produto deste produto
	 */
	public TipoDeProduto getTipo() {
		return tipo;
	}

	/**
	 * Remove quantidade dada da quantidade deste produto
	 * @param quantidade a retirar
	 * @requires quantidade <= this.quantidade
	 */
	public void retiraQuantidade(int quantidade) {
		this.quantidade -= quantidade;
	}
}
