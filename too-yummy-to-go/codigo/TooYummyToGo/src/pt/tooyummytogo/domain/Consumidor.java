package pt.tooyummytogo.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pt.portugueseexpress.InvalidCardException;
import pt.tooyummytogo.domain.strategies.MonsterCardPagamento;
import pt.tooyummytogo.domain.strategies.PortugueseExpressPagamento;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import utils.observer.Observable;
import utils.observer.Observer;
/*
 * Esta classe representa um consumidor
 */
public class Consumidor extends AbstractUtilizador {

	private List<Reserva> reservas;
	

	/**
	 * Constructor
	 * 
	 * @param username - nome do consumidor
	 * @param password - palavra passe do consumidor
	 * @requires username != null && password != null
	 */
	public Consumidor(String username, String password) {
		super(username, password);
		this.reservas = new ArrayList<>();
	}


	/**
	 * UC7-OPC
	 * 
	 * Efetua pagamento
	 * 
	 * @param total - preco total da reserva
	 * @param numeroCartao - numero do cartao
	 * @param dataValidade - data de validade do cartao 
	 * @param cvc - cvc do cartao
	 * @return true se o pagamento foi feito com sucesso, false caso contrario
	 * @throws InvalidCardException 
	 */
	public boolean efetuaPagamento(double total, String numeroCartao, String dataValidade, String cvc) 
			throws InvalidCardException {

		boolean pagamentoEfectuado = false;

		Properties meioDePagamentoProp = new Properties();
		String activatedMeioDePagamento = null;
		try {
			meioDePagamentoProp.load(new FileInputStream(new File("meioDePagamento.properties")));
			activatedMeioDePagamento = meioDePagamentoProp.getProperty("activatedMeioDePagamento");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(activatedMeioDePagamento.equals("MonsterCardPagamento")) {
			/*** Monster Card ***/
			System.out.println("\nPAGAMENTO COM MONSTER CARD!");
			AbstractMeioDePagamento monstercard = new MonsterCardPagamento(total, 
					numeroCartao, dataValidade, cvc);

			pagamentoEfectuado = monstercard.efectuaPagamento();
		}else if(activatedMeioDePagamento.equals("PortugueseExpressPagamento")) {

			System.out.println("\nPAGAMENTO COM PORTUGUESE EXPRESS!");
			/*** Portuguese Express ***/
			AbstractMeioDePagamento pagamentoPtExpress = new PortugueseExpressPagamento( 
					total, numeroCartao, dataValidade, cvc);
			pagamentoEfectuado = pagamentoPtExpress.efectuaPagamento();
		}

		return pagamentoEfectuado;
	}


	/**
	 * UC7-OPC
	 * 
	 * Cria uma reserva e adiciona-a a lista de reservas do consumidor
	 * 
	 * @param comercianteAComprar - informacao sobre o comerciante 
	 * @param total - preco total da reserva
	 * @param listaDeCompras - lista de compras
	 * @requires comercianteAComprar != null && total != null && listaDeCompras != null
	 * @return reserva criada
	 */
	public Reserva criaReserva(ComercianteInfo comercianteAComprar, double total, List<ProdutoInfo> listaDeCompras) {
		List<Produto> lpv = comercianteAComprar.getLpv().getLista();

		for (ProdutoInfo prodInfo : listaDeCompras) {
			for (Produto produto : lpv) {
				if (prodInfo.getNome().equals(produto.getNome())) {
					produto.retiraQuantidade(prodInfo.getQuantidadePedida());
				}
			}
		}
		//Cria a reserva
		Reserva reserva = new Reserva(this.username ,comercianteAComprar,total, listaDeCompras);
		this.reservas.add(reserva);
		//Add observer
		reserva.addObserver(CatUtilizadores.getComerciante(comercianteAComprar.getNome()));
		//Notify Observer
		reserva.notifyObservers();
		return reserva;
	}


	/**
	 * Detalhes da reserva com este codigo passado como parametro
	 * 
	 * @param codigoReserva codigo da reserva
	 * @return detalhes da reserva
	 */
	public String getDetalhes(String codigoReserva) {
		String detalhesReserva = "";
		for (Reserva reserva : reservas) {
			if (reserva.getCodigo().contentEquals(codigoReserva)) {
				detalhesReserva = reserva.detalhesReserva();
			}
		}
		return detalhesReserva;
	}

}





