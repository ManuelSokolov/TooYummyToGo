package pt.tooyummytogo.domain;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
/*
 * Esta classe representa um catalogo de reservas
 */
 
public class CatReservas {

	private static Map<String, List<Reserva>> reservas = new HashMap<>();
	

	

	/**
	 * Adiciona uma reserva de um consumidor ao catalogo de reservas
	 * 
	 * @param nomeConsumidor - nome do consumidor
	 * @param reserva - reserva
	 * @requires reserva != null
	 */
	public static void addReserva(String nomeConsumidor, Reserva reserva) {
		if (reservas.containsKey(nomeConsumidor)) {
			List<Reserva> lista = reservas.get(nomeConsumidor);
			lista.add(reserva);
			reservas.replace(nomeConsumidor, lista);
		} 
		else {
			List<Reserva> newList = new ArrayList<>();
			newList.add(reserva);
			reservas.put(nomeConsumidor, newList);
			
		}
	}


	/**
	 * Devolve a lista de reservas feitas por um consumidor
	 * 
	 * @param username - nome do consumidor 
	 * @return lista de reservas feitas pelo consumidor
	 */
	public List<Reserva> getCatalogoReservas(String username) {
		return new ArrayList<>(reservas.get(username));
	}



	
	
}
