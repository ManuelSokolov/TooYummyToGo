package pt.tooyummytogo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.exceptions.UtilizadorNotAcceptedException;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

class TestLogin {

	@Test
	void testLoginUtilizadorNomePassCerta1() {

		boolean expected = true;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");
		assertEquals(expected,talvezSessao.isPresent());
	}

	@Test
	void testLoginUtilizadorErradoPasswordErrada() {

		boolean expected = false;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Joao", "111");


		assertEquals(expected, talvezSessao.isPresent());
	}

	@Test
	void testLoginUtilizadorPasswordErrada() {

		boolean expected = false;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "111");

		assertEquals(expected, talvezSessao.isPresent());
	}

	@Test
	void testLoginDoisUtilizadores() {

		boolean expected = true;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");
		regHandler.registarUtilizador("Mario", "bbb");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");
		Optional<Sessao> talvezSessao2 = ty2g.autenticar("Mario", "bbb");
		assertEquals(expected, talvezSessao.isPresent() && talvezSessao2.isPresent());
	}

	@Test
	void testLoginDoisUtilizadores1Errado() {

		boolean expected = false;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");
		regHandler.registarUtilizador("Mario", "bbb");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "111");
		Optional<Sessao> talvezSessao2 = ty2g.autenticar("Mario", "bb4");
		assertEquals(expected, talvezSessao.isPresent() && talvezSessao2.isPresent());
	}

	@Test
	void testLoginComerciante() {
		boolean expected = true;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Tiotor", "sporting", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("Tiotor", "sporting");
		assertEquals(expected, talvezSessao.isPresent());
	}

	@Test
	void testLoginComerciantePassErrada() {
		boolean expected = false;
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();
		regComHandler.registarComerciante("Tiotor", "sporting", new PosicaoCoordenadas(34.5, 45.2));
		Optional<Sessao> talvezSessao = ty2g.autenticar("Tiotor", "benfica");
		assertEquals(expected, talvezSessao.isPresent());
	}

	@Test
	void testUtilizadorConsegueColocarProdutos() {

		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.getColocarProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(false,conseguiu);

		});



	}

	@Test
	void testComercianteConsegueColocarProdutos() {

		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarComercianteHandler regHandler = ty2g.getRegistarComercianteHandler();
		regHandler.registarComerciante("Bas", "Dost123", new PosicaoCoordenadas(34.5, 45.2));

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.getColocarProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(true,conseguiu);

		});


	}



	@Test
	void testUtilizadorConsegueAdicionarTipoDeProdutos() {

		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.adicionarTipoDeProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(false,conseguiu);

		});



	}


	@Test
	void testComercianteConsegueAdicionarTipoDeProdutos() {

		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarComercianteHandler regHandler = ty2g.getRegistarComercianteHandler();
		regHandler.registarComerciante("Bas", "Dost123", new PosicaoCoordenadas(34.5, 45.2));

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.adicionarTipoDeProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(true,conseguiu);

		});

	}


	@Test
	void testConsumidorConsegueEncomendarProdutos() {

		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Bas", "Dost123");

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.getEncomendarComerciantesHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(true,conseguiu);

		});

	}

	@Test
	void testComercianteConsegueEncomendarProdutos() {
		TooYummyToGo ty2g = new TooYummyToGo();
		RegistarComercianteHandler regHandler = ty2g.getRegistarComercianteHandler();
		regHandler.registarComerciante("Bas", "Dost123", new PosicaoCoordenadas(34.5, 45.2));

		Optional<Sessao> talvezSessao = ty2g.autenticar("Bas", "Dost123");

		talvezSessao.ifPresent((Sessao s) -> {
			boolean conseguiu = true;
			try {
				s.getEncomendarComerciantesHandler();
			} catch (UtilizadorNotAcceptedException e) {
				conseguiu = false;
			}	
			assertEquals(false,conseguiu);

		});

	}
}
