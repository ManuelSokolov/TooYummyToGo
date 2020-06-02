package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.CatUtilizadores;
/*
 * Esta classe representa um handler para registar um utilizador
 */
public class RegistarUtilizadorHandler {

	/**
	 * Regista um utilizador normal (Consumidor)
	 * 
	 * @param username - nome de utilizador do consumidor
	 * @param password - password do utilizador
	 * @ensures existe um utilizador com nome username e palavra-passe
	 * password
	 */
	public void registarUtilizador(String username, String password) {
		CatUtilizadores.adicionaUtilizador(username, password);
	}

}
