package pt.tooyummytogo.facade;

import java.util.Optional;



import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.domain.CatReservas;
import pt.tooyummytogo.domain.CatUtilizadores;
import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

/**
 * Esta é a classe do sistema.
 */
public class TooYummyToGo {
	
	private CatUtilizadores catUtilizadores= new CatUtilizadores();
	private CatReservas catReservas = new CatReservas();
	
	
	/**
	 * For testing
	 * @return 
	 */
	public CatReservas getReservas() {
		return this.catReservas;
	}

	/**
	 * UC1
	 * 
	 * Retorna um handler para registar utilizador
	 * 
	 * @return handler para registar utilizador
	 */
	public RegistarUtilizadorHandler getRegistarUtilizadorHandler() {
		return new RegistarUtilizadorHandler();
	}
	
	
	/**
	 * UC2
	 * 
	 * Retorna uma optional Session que representa um utilizador autenticado
	 * 
	 * @param username - nome 
	 * @param password - String
	 * @return Optional<Sessao>
	 */
	public Optional<Sessao> autenticar(String username, String password) {
		Optional<AbstractUtilizador> currentUser = catUtilizadores.tryLogin(username, password);
		return currentUser.map(u -> new Sessao(u));
		
	}

	
	/**
	 *  UC3
	 *  
	 *  Retorna um handler para registar comerciante
	 *  
	 * @return Handler para registar comerciante
	 */
	public RegistarComercianteHandler getRegistarComercianteHandler() {
		return new RegistarComercianteHandler();
	}
	

}
