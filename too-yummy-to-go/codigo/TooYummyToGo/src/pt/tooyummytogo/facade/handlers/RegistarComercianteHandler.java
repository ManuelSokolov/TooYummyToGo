package pt.tooyummytogo.facade.handlers;

import pt.tooyummytogo.domain.CatUtilizadores;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
/*
 * Esta classe representa um handler para registar um comerciante
 */
public class RegistarComercianteHandler {
	
	/**
	 * Regista a um comerciante
	 * 
	 * @param username - nome do comerciante
	 * @param password - palavra passe do comerciante
	 * @param coordenadas - localizacao do local de venda do comerciante
	 * @requires username != null && password != null  && coordenada!= null
	 * @ensures existe um novo comerciante do sistema
	 */
	public void registarComerciante(String username, String password, PosicaoCoordenadas coordenadas) {
		CatUtilizadores.registarComerciante(username, password, coordenadas);
	}

}
