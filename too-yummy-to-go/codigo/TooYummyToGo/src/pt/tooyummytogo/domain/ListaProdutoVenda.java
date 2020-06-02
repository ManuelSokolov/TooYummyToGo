package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/* Esta classe representa uma lista de produtos para venda */
public class ListaProdutoVenda {

	private List<Produto> lpv = new ArrayList<>();
	private boolean disponibilidade;
	private LocalDateTime dataInicialRecolha;
	private LocalDateTime dataFinalRecolha;
	private List<Reserva> reservas; 


	/**
	 * UC5-OPB
	 * 
	 * Cria e adiciona um novo produto a esta lista de produto para venda
	 * 
	 * @param nome - nome do produto
	 * @param quantidade - quantidade do tipo de produto
	 * @param tipo - tipo de produto com o mesmo nome que o produto
	 * @requires nome != null && quantidade != null && tipo != null
	 */
	public void createProduto(String nome, int quantidade, TipoDeProduto tipo) {
		Produto produto = new Produto(nome, quantidade, tipo);
		lpv.add(produto);
	}


	/**
	 * UC5-OPC
	 * 
	 * Confirma a disponibilidade da lista de produtos para venda com 
	 * data inicio de recolha e data fim de recolha
	 * 
	 * @param inicioRecolha initial date
	 * @param fimRecolha ending date
	 * @requires inicioRecolha != null && fimRecolha != null
	 * @requires inicioRecolha < fimRecolha && fimRecolha>data atual
	 */
	public void confirmaDisp(LocalDateTime inicioRecolha, LocalDateTime fimRecolha) {
		this.disponibilidade = true;
		this.dataInicialRecolha = inicioRecolha;
		this.dataFinalRecolha = fimRecolha;
	}



	/**
	 * UC6
	 * Verifica se a lista de produtos para venda esta disponivel na proxima hora
	 * @return true se esta disponivel na proxima hora, false caso contratio
	 */
	public boolean estaDisp() {
		LocalDateTime now = LocalDateTime.now();
		return (now.plusHours(1).isBefore(dataFinalRecolha));
	}


	/**
	 * UC6
	 * 
	 * Verifica se a lista de produtos para venda esta vazia
	 * @return true se a lpv esta vazia, false caso contrario
	 */
	public boolean estaVazio() {
		return lpv.isEmpty();
	}


	/**
	 * UC6
	 * 
	 * Verifica se a lista de produtos para venda esta disponivel no intervalo
	 * [ini,fim]
	 * 
	 * @param ini - data inicial
	 * @param fim - dat final
	 * @return true se a lista esta disponivel, false caso contrario
	 */
	public boolean estaDisp(LocalDateTime ini, LocalDateTime fim) {
		LocalDateTime now = LocalDateTime.now();

		// (fim-inicio)
		LocalDateTime interval = fim.minusHours(ini.getHour());
		interval = interval.minusMinutes(ini.getMinute());
		interval = interval.minusSeconds(ini.getSecond());

		// (fim-inicio)+ now
		interval = interval.plusHours(now.getHour());
		interval = interval.plusMinutes(now.getMinute());
		interval = interval.plusSeconds(now.getSecond());

		// (fim-inicio)+ now < fimRecolha
		return (interval.isBefore(dataFinalRecolha));
	}
	


	/**
	 * UC7-OPC
	 * 
	 * Devolve a lista dos produtos contidos na lista de venda
	 * 
	 * @return lpv - lista de produtos
	 */
	public List<Produto> getLista() {
		return new ArrayList<>(lpv);
	}
	
}
