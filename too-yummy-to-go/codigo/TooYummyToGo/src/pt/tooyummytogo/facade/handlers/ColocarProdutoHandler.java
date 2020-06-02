package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.TipoDeProduto;
import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.exceptions.TipoDeProdutoNotFoundException;
/*
 * Esta classe representa um handler para colocar um produto a venda
 */
public class ColocarProdutoHandler {

	private Comerciante comerciante;

	/**
	 * Constructor
	 * 
	 * @param utilizador - comerciante
	 */
	public ColocarProdutoHandler(AbstractUtilizador utilizador) {
		this.comerciante = (Comerciante) utilizador;
	}


	/**
	 * UC5-OPA
	 * 
	 * Inicializa a lista de produtos para venda diaria 
	 * e devolve lista com os tipos de produtos que este comerciante criou 
	 * 
	 * @return lista de nomes dos tipos de produto
	 */
	public List<String> inicioDeProdutosHoje() {
		this.comerciante.createListaProdutos();
		List<String> ltp = new ArrayList<>();
		List<TipoDeProduto> list = comerciante.getTiposProduto();
		for (TipoDeProduto tp : list) {
			ltp.add(tp.getNome());
		}
		return ltp;
	}


	/**
	 * UC5-OPB
	 * 
	 * Comerciante indica o tipo de produto e a quantidade que 
	 * pretende colocar a venda
	 * 
	 * @param nome - nome do tipo de produto
	 * @param quantidade - quantidade do tipo de produto
	 * @throws TipoDeProdutoNotFoundException
	 */	
	public void indicaProduto(String nome, int quantidade) throws TipoDeProdutoNotFoundException {
		comerciante.addProdutoLista(nome, quantidade);
	}	


	/**
	 * UC5-OPC
	 * 
	 * Comerciante confirma que quer colocar a lista de produtos para venda
	 * disponivel entre a data de inicio e fim
	 * 
	 * @param inicioRecolha - data inicial 
	 * @param fimRecolha - data final
	 * @requires inicioRecolha != null && fimRecolha != null
	 * @requires inicioRecolha < fimRecolha e fimRecolha > dataActual
	 */
	public void confirma(LocalDateTime inicioRecolha, LocalDateTime fimRecolha) {
		comerciante.confirmaDispLista(inicioRecolha, fimRecolha);
	}

}
