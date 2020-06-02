package pt.tooyummytogo.domain.strategies;

import java.util.HashMap;
import java.util.List;

import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.domain.CatUtilizadores;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;



public interface ProcurarComerciantesStrategy {
	
	public List<ComercianteInfo> procuraComerciante(HashMap<String, AbstractUtilizador> utilizadores) throws ComerciantesNotFoundException;

	
	
}
