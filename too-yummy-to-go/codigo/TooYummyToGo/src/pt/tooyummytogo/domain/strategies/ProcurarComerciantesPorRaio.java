package pt.tooyummytogo.domain.strategies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class ProcurarComerciantesPorRaio implements ProcurarComerciantesStrategy{
	
	private int raio;
	private PosicaoCoordenadas localizacao;

	public ProcurarComerciantesPorRaio(int raio, PosicaoCoordenadas localizacao) {
		this.raio = raio;
		this.localizacao = localizacao;
	
	}


	@Override
	public List<ComercianteInfo> procuraComerciante(HashMap<String, AbstractUtilizador> utilizadores)
			throws ComerciantesNotFoundException {
		List<Comerciante> lista = new ArrayList<>();

		for (HashMap.Entry<String, AbstractUtilizador> entry : utilizadores.entrySet()) {
			AbstractUtilizador utilizador = entry.getValue();
			if (utilizador instanceof Comerciante) {
				if (((Comerciante) utilizador).dentroRaio(localizacao,this.raio)) {
					if (((Comerciante) utilizador).listaDisponivel(LocalDateTime.now(), LocalDateTime.now().plusHours(1)))
						lista.add((Comerciante) utilizador);
				}
			}
		}
		
		if(lista.isEmpty()) 
			throw new ComerciantesNotFoundException();
		 
		return lista.stream()
				.map(c -> new ComercianteInfo(c.getUsername(), c.getListaProdutosVenda(), c.getLocalVenda()))
				.collect(Collectors.toList());
	
	}




	
	

}
