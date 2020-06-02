package pt.tooyummytogo.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import pt.tooyummytogo.domain.strategies.ProcurarComerciantesStrategy;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
/*
 * Esta classe representa o catalogo de utilizadores
 */
public class CatUtilizadores {

	private static HashMap<String, AbstractUtilizador> utilizadores = new HashMap<>();
	
	/**
	 * Devolve o comerciante com nome igual a nomeCom
	 * 
	 * @param nome - nome do comerciante
	 * @return Comerciante caso exista e null caso contrario
	 */
	public static Comerciante getComerciante(String nomeCom) {
		if(utilizadores.containsKey(nomeCom))
			return (Comerciante) utilizadores.get(nomeCom);
		else
			return null;
	}
	
	/**
	 * Adiciona um novo consumidor ao catalogo de utilizadores
	 * 
	 * @param username - nome do consumidor
	 * @param password - password do consumidor
	 * @requires username != null && password != null
	 * @ensures existe um novo consumidor no catalogo de utilizadores
	 */
	public static void adicionaUtilizador(String username, String password) {
		Consumidor consumidor = new Consumidor(username, password);
		getUtilizadores().put(username, consumidor);
	}
	
	/**
	 * Adiciona comerciante ao catalogo de utilizadores
	 * 
	 * @param username - nome do comerciante
	 * @param password - password do comerciante
	 * @param coordenadas - coordenadas para o local de venda do comerciante
	 * @requires username != null && password != null && coordenadas!=null
	 */
	public static void registarComerciante(String username, String password, PosicaoCoordenadas coordenadas) {
		Comerciante comerciante = new Comerciante(username, password, coordenadas);
		getUtilizadores().put(username, comerciante);
	}
	
	/**
	 * Devolve uma lista com todos os comerciantes do catalogo de utilizadores
	 * 
	 * @return lista - lista de comerciantes
	 * @throws ComerciantesNotFoundException
	 */
	public static List<Comerciante> getComerciantes() throws ComerciantesNotFoundException {
		List<Comerciante> lista = new ArrayList<>();
		for (HashMap.Entry<String, AbstractUtilizador> entry : getUtilizadores().entrySet()) {
			if (entry.getValue() instanceof Comerciante) {
				lista.add((Comerciante) entry.getValue());
			}
		}
		if (lista.isEmpty())
			throw new ComerciantesNotFoundException();
		return lista;

	}

	/**
	 * Efetua o login 
	 * 
	 * @param username - nome do utilizador
	 * @param password - password do utilizador
	 * @return Optional de AbstractUtiliador caso exista e Optional null caso contrario
	 */
	public Optional<AbstractUtilizador> tryLogin(String username, String password) {
		return Optional.ofNullable(getUtilizadores().get(username)).filter(u -> u.hasPassword(password));
	}


	public static HashMap<String, AbstractUtilizador> getUtilizadores() {
		return utilizadores;
	}

	public static List<ComercianteInfo> procuraComerciantes(ProcurarComerciantesStrategy p) throws ComerciantesNotFoundException {		
		return p.procuraComerciante(utilizadores);
	}

	


	

	
	
	
}
