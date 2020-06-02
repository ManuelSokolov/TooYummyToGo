package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import utils.observer.Observable;
import utils.observer.Observer;
/*
 * Esta classe representa uma reserva
 */
public class Reserva implements Observable {

	private String codigo;
	private String consumidorUsername;
	private String comercianteUsername;
	private ListaProdutoVenda listaDeProdutosVenda;
	private double total;
	private List<ProdutoInfo> listaDeCompras;
	private boolean recolhida;
	private ListaProdutoVenda lpv;
	private List<Observer> observers;
	
	
	/**
	 * Cria uma reserva ainda nao recolhida
	 * 
	 * @param consumidorUsername - nome do consumidor
	 * @param comercianteAComprar - informacao do comerciante
	 * @param total - preco total da reserva
	 * @param listaDeCompras - lista de produtos para compras associada a esta reserva
	 */
	public Reserva(String consumidorUsername, ComercianteInfo comercianteAComprar, double total, List<ProdutoInfo> listaDeCompras) {
		this.consumidorUsername = consumidorUsername;
		this.comercianteUsername = comercianteAComprar.getNome();
		this.listaDeProdutosVenda = comercianteAComprar.getLpv();
		this.total = total;
		this.listaDeCompras = listaDeCompras;
		this.recolhida = false;
		int hora = LocalDateTime.now().getHour();
		int minuto = LocalDateTime.now().getMinute();
		this.codigo = consumidorUsername + "_para_" + comercianteUsername + hora + minuto;
		this.observers = new ArrayList<>();
	}

	
	/**
	 * Devolve o codigo da reserva
	 * @return codigo da reserva
	 */
	public String getCodigo() {
		return this.codigo;
	}
	
	/**
	 * Representacao textual da reserva
	 * @return a representacao textual da reserva
	 */
	public String detalhesReserva() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("***** Detalhes da sua reserva *****\n");
		sb.append("Nome do Consumidor: " + this.consumidorUsername + "\n");
		sb.append("Nome do Comerciante: " + this.comercianteUsername + "\n\n");
		sb.append("Lista de Produtos Reservados\n");
		
		for (ProdutoInfo prodInfo : listaDeCompras) {
			sb.append(prodInfo.getQuantidadePedida() + " " + prodInfo.getNome() + " - " + String.format("%.2f",
					prodInfo.getTipo().getPreco()) + " euros\n");
		}
		
		sb.append("\n");
		String estado = "";
		if (!this.recolhida) {
			estado = "Nao recolhida";
		}else {
			estado = "Recolhida";
		}
		sb.append("Preço total: " + String.format("%.2f", this.total) + " euros\n");
		sb.append("Estado da reserva: " + estado + "\n");
		sb.append("Código da reserva: " + this.codigo + "\n");
		sb.append("**********************************\n");
		
		return sb.toString();
	}
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(consumidorUsername, listaDeCompras);
		}
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}


}












