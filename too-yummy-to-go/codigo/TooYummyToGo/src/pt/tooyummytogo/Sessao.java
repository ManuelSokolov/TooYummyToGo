package pt.tooyummytogo;
import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.Consumidor;
import pt.tooyummytogo.exceptions.UtilizadorNotAcceptedException;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
/* Esta classe representa uma sessao */
public class Sessao {

	private AbstractUtilizador utilizador;

	public Sessao(AbstractUtilizador utilizador) {
		this.utilizador = utilizador;
	}


	/*
	 * UC4
	 * 
	 * Retorna um novo handler para um comerciante 
	 * adicionar um tipo de produto 
	 *  
	 * @requires utilizador instanceof comerciante
	 */
	public AdicionarTipoDeProdutoHandler adicionarTipoDeProdutoHandler() throws UtilizadorNotAcceptedException {
		if(utilizador instanceof Consumidor) {
			throw new UtilizadorNotAcceptedException();
		}else {
			return new AdicionarTipoDeProdutoHandler(utilizador);
		}
	}


	/*
	 * UC5
	 * 
	 * Retorna um handler para um comerciante colocar um produto a venda
	 * 
	 * @requires utilizador instanceof comerciante 
	 */
	public ColocarProdutoHandler getColocarProdutoHandler() throws UtilizadorNotAcceptedException {
		if(utilizador instanceof Consumidor) {
			throw new UtilizadorNotAcceptedException();
		}else
			return new ColocarProdutoHandler(utilizador);
	}
	

	/**
	 * UC6 e UC7
	 * 
	 * Retorna um handler um consumidor encomendar 
	 * 
	 * @requires utilizador instanceof consumidor
	 * @return handler para efetuar encomendas
	 */
	public EncomendarHandler getEncomendarComerciantesHandler() throws UtilizadorNotAcceptedException{
		if(utilizador instanceof Comerciante) {
			throw new UtilizadorNotAcceptedException(); 
		}else
			return new EncomendarHandler(utilizador);
	}
}
