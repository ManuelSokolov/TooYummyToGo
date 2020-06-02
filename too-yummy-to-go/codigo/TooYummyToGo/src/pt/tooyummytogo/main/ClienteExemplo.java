package pt.tooyummytogo.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.portugueseexpress.InvalidCardException;
import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.exceptions.ReservaFalhadaException;
import pt.tooyummytogo.exceptions.TipoDeProdutoNotFoundException;
import pt.tooyummytogo.exceptions.UtilizadorNotAcceptedException;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;
import pt.tooyummytogo.facade.handlers.RegistarComercianteHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

public class ClienteExemplo {
	public static void main(String[] args) {
		TooYummyToGo ty2g = new TooYummyToGo();

		// UC1
		RegistarUtilizadorHandler regHandler = ty2g.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("Felismina", "hortadafcul");

		// UC3
		RegistarComercianteHandler regComHandler = ty2g.getRegistarComercianteHandler();

		regComHandler.registarComerciante("Silvino", "bardoc2", new PosicaoCoordenadas(34.5, 45.2));
		regComHandler.registarComerciante("Maribel", "torredotombo", new PosicaoCoordenadas(33.5, 45.2));

		// UC4
		Optional<Sessao> talvezSessao = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao.ifPresent((Sessao s) -> {
			AdicionarTipoDeProdutoHandler addTipoProdHandler=null;
			try {
				addTipoProdHandler = s.adicionarTipoDeProdutoHandler();
			} catch (UtilizadorNotAcceptedException e) {
				System.out.println("Consumidor nao pode adicionar tipos de produto.");
			}
			Random r = new Random();
			for (String tp : new String[] { "Pão", "Pão de Ló", "Mil-folhas" }) {
				addTipoProdHandler.registaTipoDeProduto(tp, r.nextDouble() * 10);
			}
		});

		// UC5
		Optional<Sessao> talvezSessao2 = ty2g.autenticar("Silvino", "bardoc2");
		talvezSessao2.ifPresent((Sessao s) -> {

			ColocarProdutoHandler cpvHandler=null;
			try {
				cpvHandler = s.getColocarProdutoHandler();
			} catch (UtilizadorNotAcceptedException e3) {
				System.out.println("Consumidor nao pode colocar produtos para venda.");
			}

			List<String> listaTiposDeProdutos = cpvHandler.inicioDeProdutosHoje();

			try {

				cpvHandler.indicaProduto(listaTiposDeProdutos.get(0), 10); // Pão
				cpvHandler.indicaProduto(listaTiposDeProdutos.get(2), 5); // Mil-folhas

				cpvHandler.indicaProduto("Pastel de nata", 2);

			} catch (TipoDeProdutoNotFoundException e) {

				System.out.println("Tipo de produto não encontrado");
				// UC4
				AdicionarTipoDeProdutoHandler atp=null;
				try {
					atp = s.adicionarTipoDeProdutoHandler();
				} catch (UtilizadorNotAcceptedException e2) {

				}

				atp.registaTipoDeProduto("Pastel de nata", 2f);

				try {
					cpvHandler.indicaProduto("Pastel de nata", 2);
				} catch (TipoDeProdutoNotFoundException e1) {//
					//Does nothing
				}

			}

			cpvHandler.confirma(LocalDateTime.now(), LocalDateTime.now().plusHours(2));

			System.out.println("Produtos disponíveis");
		});

		// UC6 + UC7
		Optional<Sessao> talvezSessao3 = ty2g.autenticar("Felismina", "hortadafcul");
		talvezSessao3.ifPresent((Sessao s) -> {
			EncomendarHandler encomendarHandler=null;
			try {
				encomendarHandler = s.getEncomendarComerciantesHandler();
			} catch (UtilizadorNotAcceptedException e1) {
				System.out.println("Comerciante nao pode comprar a outros comerciantes.");
			}
			boolean redefineRaio = false;
			boolean redefinePeriodo = false;
			List<ComercianteInfo> listaComInfo = new ArrayList<>();
			try {

				listaComInfo = encomendarHandler
						.indicaLocalizacaoActual(new PosicaoCoordenadas(34.5, 45.2));


				for (ComercianteInfo comInfo : listaComInfo) {
					System.out.println(comInfo.getNome());
				}
				redefineRaio = true;
				if (redefineRaio) {
					listaComInfo = encomendarHandler.redefineRaio(100);
					for (ComercianteInfo comInfo : listaComInfo) {
						System.out.println(comInfo.getNome());
					}
				}
				redefinePeriodo = false;
				if (redefinePeriodo) {
					listaComInfo = encomendarHandler.redefinePeriodo(LocalDateTime.now(),
							LocalDateTime.now().plusHours(1));
					for (ComercianteInfo comInfo : listaComInfo) {
						System.out.println(comInfo.getNome());
					}
				}

			}catch(ComerciantesNotFoundException e ) { 
				try {
					System.out.println("Nao foram encontrados comerciantes!");
					redefineRaio = true;
					if (redefineRaio) {

						for (ComercianteInfo comInfo : listaComInfo) {
							System.out.println(comInfo.getNome());
						}
					}
					redefinePeriodo = false;
					if (redefinePeriodo) {
						listaComInfo = encomendarHandler.redefinePeriodo(LocalDateTime.now(),
								LocalDateTime.now().plusHours(1));
						for (ComercianteInfo comInfo : listaComInfo) {
							System.out.println(comInfo.getNome());
						}
					}
				}catch(ComerciantesNotFoundException e2) {
					System.out.println("Nao foram encontrados comerciantes!");
				}
			}



			// A partir de agora é UC7
			List<ProdutoInfo> listaProdInfo = encomendarHandler.escolheComerciante(listaComInfo.get(0));
			for (ProdutoInfo prodInfo : listaProdInfo) {
				encomendarHandler.indicaProduto(prodInfo, 1); // Um de cada
			}


			//ciclo while codigo Reserva is null
			try {
				String codigoReserva = encomendarHandler.indicaPagamento("365782312312", "02/21", "766");
				System.out.println("Reserva " + codigoReserva + " feita com sucesso");

				//Print os detalhes da reserva
				String detalhesDaReserva = encomendarHandler.getDetalhesReserva(codigoReserva);
				System.out.println("\n" + detalhesDaReserva);	
			}catch(ReservaFalhadaException | InvalidCardException e) {
				System.out.println("Nao foi possivel efetuar pagamento!");
			}


		});


	}
}
