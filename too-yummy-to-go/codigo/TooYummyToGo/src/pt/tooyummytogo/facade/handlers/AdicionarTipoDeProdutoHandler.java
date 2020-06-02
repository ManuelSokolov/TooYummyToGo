package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.AbstractUtilizador;
/*
 * Esta classe representa um handler para adicionar um tipo de produto
 */
public class AdicionarTipoDeProdutoHandler {
	
	private Comerciante comerciante;
	
	/**
	 * Construtor
	 * 
	 * @param utilizador - comerciante
	 */
	public AdicionarTipoDeProdutoHandler(AbstractUtilizador utilizador) {
		this.comerciante = (Comerciante) utilizador;
	}
	
	/**
	 * Regista um tipo de produto
	 * 
	 * @param tipo - tipo de produto
	 * @param preco - preco do tipo de produto
	 */
	public void registaTipoDeProduto(String tipo, double preco) {
		comerciante.addTipoProduto(tipo, preco);
	}

}
