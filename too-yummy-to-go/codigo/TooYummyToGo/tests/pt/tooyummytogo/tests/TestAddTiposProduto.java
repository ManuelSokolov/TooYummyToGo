package pt.tooyummytogo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.domain.CatUtilizadores;
import pt.tooyummytogo.domain.Comerciante;
import pt.tooyummytogo.domain.TipoDeProduto;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.exceptions.UtilizadorNotAcceptedException;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;

class TestAddTiposProduto {

	@Test
	void adicionaTiposProdutos() throws ComerciantesNotFoundException {
		boolean esperado = true;
		String[] lista = { "Pão", "Pão de Ló", "Mil-folhas" };
		TooYummyToGo ty2g = new TooYummyToGo();

		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();

		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao.ifPresent((Sessao s) -> {
			AdicionarTipoDeProdutoHandler atp=null;
			try {
				atp = s.adicionarTipoDeProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			float[] preco = { 1.00f, 2.50f, 2f };// Soko
			int i = 0;// Soko
			for (String tp : new String[] { "Pão", "Pão de Ló", "Mil-folhas" }) {
				atp.registaTipoDeProduto(tp, preco[i]);
				i++;
			}
		});
		List<Comerciante> listaCom = CatUtilizadores.getComerciantes();
		Comerciante com = null;
		for (Comerciante c : listaCom) {
			if (c.getUsername().equals("Silvino")) {
				com = c;
			}

		}
		List<TipoDeProduto> tiposProduto = com.getTiposProduto();
		String[] listaComparar = new String[3];
		int i = 0;
		for (TipoDeProduto t : tiposProduto) {
			listaComparar[i] = t.getNome();
			i++;
		}
		boolean iguais = true;
		for (int j = 0; j < lista.length; j++) {
			if (listaComparar[j] != lista[j]) {
				iguais = false;
			}
		}
		assertEquals(esperado, iguais);
	}

}
