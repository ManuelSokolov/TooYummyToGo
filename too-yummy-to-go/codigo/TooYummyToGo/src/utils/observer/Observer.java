package utils.observer;

import java.util.List;

import pt.tooyummytogo.facade.dto.ProdutoInfo;

public interface Observer {
	
	/**
	 * Atualiza o comerciante que foi feita um reserva
	 * 
	 * @param nomeConsumidor nome do consumidor
	 * @param listaDeCompras lista de compras
	 */
	public void update(String nomeConsumidor, List<ProdutoInfo> listaDeCompras);
}
