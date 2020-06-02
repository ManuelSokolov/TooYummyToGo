package pt.tooyummytogo.facade.handlers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pt.tooyummytogo.domain.CatUtilizadores;
import pt.tooyummytogo.domain.CatReservas;
import pt.tooyummytogo.domain.Consumidor;
import pt.tooyummytogo.domain.Reserva;
import pt.tooyummytogo.domain.strategies.ProcurarComerciantesPorPeriodo;
import pt.tooyummytogo.domain.strategies.ProcurarComerciantesPorRaio;
import pt.tooyummytogo.domain.strategies.ProcurarComerciantesStrategy;
import pt.portugueseexpress.InvalidCardException;
import pt.tooyummytogo.domain.AbstractUtilizador;
import pt.tooyummytogo.exceptions.ComerciantesNotFoundException;
import pt.tooyummytogo.exceptions.ReservaFalhadaException;
import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
/*
 * Esta classe representa um handler para encomendar
 */
public class EncomendarHandler {
	
	private static final int RAIO_DEFAULT = 5;

	private PosicaoCoordenadas localizacaoCorrente;
	private int raioCorrente;
	private List<ProdutoInfo> listaDeCompras;
	private ComercianteInfo comercianteAComprar;
	private Consumidor consumidor;
	private Reserva reserva;

	/**
	 * Construtor
	 * 
	 * @param utilizador user 
	 */
	public EncomendarHandler(AbstractUtilizador utilizador) {
		this.listaDeCompras = new ArrayList<>();
		this.consumidor = (Consumidor) utilizador;
	}

	/**
	 * UC6
	 * 
	 * Devolve uma lista com os comerciantes com produtos disponiveis para venda na 
	 * proxima hora dentro de um raio de 5 para a localizacao atual 
	 * 
	 * @param localizacaoAtual - localizacao do consumidor
	 * @requires localizacaoAtual != null
	 * @return lista de comerciantes
	 * @throws ComerciantesNotFoundException 
	 */
	public List<ComercianteInfo> indicaLocalizacaoActual(PosicaoCoordenadas localizacaoAtual) throws ComerciantesNotFoundException {
		this.localizacaoCorrente = localizacaoAtual;
		this.raioCorrente = RAIO_DEFAULT;
		ProcurarComerciantesStrategy p = new ProcurarComerciantesPorRaio(RAIO_DEFAULT,localizacaoAtual);

		return CatUtilizadores.procuraComerciantes(p);
	}
	
	/**
	 * UC6
	 * Devolve uma lista com os comerciantes com produtos disponiveis para venda na 
	 * proxima hora dentro de um raio definido para a localizacao atual 
	 * 
	 * @param raio - raio dado
	 * @requires raio!= null
	 * @return lista de comerciantes
	 * @throws ComerciantesNotFoundException 
	 */
	public List<ComercianteInfo> redefineRaio(int raio) throws ComerciantesNotFoundException  {
		this.raioCorrente = raio;
		ProcurarComerciantesStrategy p = new ProcurarComerciantesPorRaio(raio,this.localizacaoCorrente);
		
		return CatUtilizadores.procuraComerciantes(p);
	}

	/**
	 * UC6
	 * 
	 * Devolve uma lista com os comerciantes com produtos disponiveis para venda no 
	 * intervalo [inicioRecolha,fimRecolha] para a localizacao atual
	 * 
	 * @param inicioRecolha - data inicio de recolha
	 * @param fimRecolha - data fim de recolha
	 * @requires inicioRecolha != null && fimRecolha != null
	 * @return lista de comerciantes
	 * @throws ComerciantesNotFoundException 
	 */
	public List<ComercianteInfo> redefinePeriodo(LocalDateTime inicioRecolha, LocalDateTime fimRecolha) throws ComerciantesNotFoundException  {
		ProcurarComerciantesStrategy p = new ProcurarComerciantesPorPeriodo(this.localizacaoCorrente,this.raioCorrente,inicioRecolha,fimRecolha);
		
		return CatUtilizadores.procuraComerciantes(p);

	}

	
	/**
	 * UC7-OPA
	 * 
	 * Lista dos produtos para venda do comerciante
	 * 
	 * @param comercianteInfo - comerciante
	 * @requires comercianteInfo != null
	 * @return lista de produtos
	 */
	public List<ProdutoInfo> escolheComerciante(ComercianteInfo comercianteInfo) {
		this.comercianteAComprar = comercianteInfo;
		return comercianteInfo.getLpv().getLista().stream()
				.map(p -> new ProdutoInfo(p.getNome(), p.getQuantidade(), p.getTipo())).collect(Collectors.toList());
	}


	/**
	 * UC7-OPB
	 * 
	 * Consumidor indica o produto que quer adicionar a sua lista de produtos que 
	 * quer comprar (lista de compras) e caso esteja disponivel e adicionado
	 * 
	 * @param prodInfo - informacao do produto
	 * @param quantidade - quantidade do produto 
	 * @requires prodInfo != null && quantidade != null
	 * @throws quantidadeNotAvailableExecption
	 */
	public void indicaProduto(ProdutoInfo prodInfo, int quantidade) /* throws quantidadeNotAvailableExecption */ {
		boolean ProdDisp = prodInfo.estaDisp(quantidade);
		if (ProdDisp) {
			listaDeCompras.add(prodInfo);
		}
	}


	/**
	 * UC7-OPC 
	 * 
	 * Consumidor indica os seus dados bancarios, cria uma reserva
	 * e adiciona-a ao catalogo de reservas do sistema
	 * 
	 * @param numeroCartao - numero do cartao
	 * @param dataValidade - data de validade do cartao
	 * @param cvc - cvc do cartao
	 * @requires dataValidade date type (month/year)
	 * @requires month entre 1 and 12
	 * @return codigo codigo da reserva caso tenha sucesso, excepcao caso contrario
	 * @throws ReservaFalhadaException
	 * @throws InvalidCardException 
	 */
	public String indicaPagamento(String numeroCartao, String dataValidade, String cvc) 
			throws ReservaFalhadaException, InvalidCardException {
		double total = 0;
		for (ProdutoInfo prodInfo : this.listaDeCompras) {// excepcao quando quantidade nao da!
			total += prodInfo.getTipo().getPreco() * prodInfo.getQuantidadePedida();
		}

		boolean pagamentoEfectuado = this.consumidor.efetuaPagamento(total, numeroCartao, dataValidade, cvc);
	
		//TODO ver pagamentos (FASE 4) (comerciante receber o pagamento com total, criar 
		//cartao no comerciante para receber 

		if (pagamentoEfectuado) {
			Reserva r = this.consumidor.criaReserva(comercianteAComprar, total, listaDeCompras);
			CatReservas.addReserva(this.consumidor.getUsername(), r);
			return r.getCodigo();
		} else {
			throw new ReservaFalhadaException();
		}

	}

	/**
	 * Retorna os detalhes da reserva do consumidor
	 * 
	 * @param codigoReserva codigo da reserva
	 * @return detalhes da reserva
	 */
	public String getDetalhesReserva(String codigoReserva) {
		return consumidor.getDetalhes(codigoReserva); 
	}

}
