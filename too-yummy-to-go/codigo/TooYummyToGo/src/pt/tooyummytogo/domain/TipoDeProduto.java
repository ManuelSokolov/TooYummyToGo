package pt.tooyummytogo.domain;

/**
 * Esta classe representa um tipo de produto
 */
public class TipoDeProduto {
	
	private String nome;
	private double preco;

	/**
	 * Constructor
	 * 
	 * @param nome - nome deste tipo de produto
	 * @param preco - preco deste tipo de produto
	 */
	public TipoDeProduto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	/**
	 * Devolve o nome deste tipo de produto
	 * @return nome deste tipo de produto
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Devolve o preco deste tipo de produto
	 * @return preco deste tipo de produto
	 */
	public double getPreco() {
		return this.preco;
	}

}
