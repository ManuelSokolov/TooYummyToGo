package pt.tooyummytogo.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.exceptions.TipoDeProdutoNotFoundException;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.dto.ProdutoInfo;
import utils.observer.Observer;
/*
 * Esta classe representa um comerciante
 */
public class Comerciante extends AbstractUtilizador implements Observer {

	private PosicaoCoordenadas localVenda;
	private List<TipoDeProduto> tiposProduto;
	private ListaProdutoVenda lpv;

	

	/**
	 * Constructor para um comerciante
	 * @param username - nome de comerciante
	 * @param password - palavra passe para o comerciante
	 * @param coordenadas - coordenadas para o local de venda
	 * @requires username!=null && password !=null && coordenadas != null
	 */
	public Comerciante(String username, String password, PosicaoCoordenadas coordenadas) {
		super(username, password);
		this.localVenda = coordenadas;
		this.tiposProduto = new ArrayList<>();
	}

	/**
	 * Devolve o local de venda
	 * @return localVenda merchant selling spot
	 */
	public PosicaoCoordenadas getLocalVenda() {
		return localVenda;
	}

	/**
	 * Retorna a lista de produtos para venda associada a este comerciante
	 * @return lista de produtos para venda lpv
	 */
	public ListaProdutoVenda getListaProdutosVenda() {
		return this.lpv;
	}

	/**
	 * Retorna os tipos de produto que este comerciante criou
	 * @return tiposProduto
	 */
	public List<TipoDeProduto> getTiposProduto() {
		return new ArrayList<>(this.tiposProduto);
	}


	/**
	 * UC5-OPA
	 *
	 * Adiciona um novo tipo de produto aos tipos de produto
	 * deste comerciante
	 * 
	 * @param nome - nome do tipo de produto
	 * @param preco - preco tipo de produto
	 * @requires nome!=null && preco != null
	 * 
	 */
	public void addTipoProduto(String nome, double preco) {
		this.tiposProduto.add(new TipoDeProduto(nome, preco));
	}


	/**
	 * UC5-OPA
	 * 
	 * Cria uma lista de produtos para venda 
	 * 
	 * Create daily list of products
	 */
	public void createListaProdutos() {
		this.lpv = new ListaProdutoVenda();
	}



	/**
	 * UC5-OPB
	 * 
	 * Adiciona produto a lista de produtos para venda deste comerciante caso 
	 * ele exista
	 * 
	 * @param nome - tipo de produto
	 * @param quantidade - quantidade do tipo de produto
	 * @throws TipoDeProdutoNotFoundException
	 */
	public void addProdutoLista(String nome, int quantidade) throws TipoDeProdutoNotFoundException {
		TipoDeProduto tipo = null;
		for (TipoDeProduto tp : tiposProduto) {
			if (tp.getNome().equals(nome)) {
				tipo = tp;
			}
		}
		if (tipo == null) {
			throw new TipoDeProdutoNotFoundException();
		} else {
			lpv.createProduto(nome, quantidade, tipo);
		}
	}



	/**
	 * UC5-OPC
	 * 
	 * Confirma disponibilidade da lista de produtos para venda
	 * e adiciona o horario de inicio de recolha e fim de recolha 
	 * sobre a mesma lista
	 * 
	 * @param inicioRecolha - data inicial de recolha
	 * @param fimRecolha - data final de recolha
	 * @requires inicioRecolha != null && fimRecolha !=null
	 * @requires fimRecolha > inicioRecolha 
	 */
	public void confirmaDispLista(LocalDateTime inicioRecolha, LocalDateTime fimRecolha) {
		this.lpv.confirmaDisp(inicioRecolha, fimRecolha);
	}




	/**
	 * UC6
	 * 
	 * Verifica se o comerciante está dentro do raio default (5) do centro 
	 * localizacao
	 * 
	 * @param localizacao - localizacao do centro 
	 * @return true se o comerciante esta dentro do raio dafault, caso contrario false
	 */
	public boolean dentroRaio(PosicaoCoordenadas localizacao) {
		if (localVenda.distanciaEmMetros(localizacao) <= 5) {
			return this.lpv.estaDisp() && !this.lpv.estaVazio();
		}
		return false;
	}


	/**
	 * UC6
	 * 
	 * Verifica se o comerciante esta dentro do raio dado do centro 
	 * localizacaoCorrente
	 * 
	 * @param localizacaoCorrente - localizacao do centro
	 * @param raio - raio dado
	 * @return true se o comercinte esta dentro do raio dado, caso contrario false
	 */
	public boolean dentroRaio(PosicaoCoordenadas localizacaoCorrente, int raio) {
		if (localVenda.distanciaEmMetros(localizacaoCorrente) <= raio) {
			return this.lpv.estaDisp() && !this.lpv.estaVazio();
		}
		return false;
	}


	/**
	 * UC6
	 * 
	 * Verifica se a lista de produtos para venda esta disponivel dentro
	 * do intervalo de tempo [ini,fim]
	 * 
	 * @param ini - data inicial
	 * @param fim - data final
	 * @requires ini != null && fim != null
	 * @requires fim > ini
	 * @return true se a lista de produtos para venda esta disponivel, caso contrario
	 * false
	 */
	public boolean listaDisponivel(LocalDateTime ini, LocalDateTime fim) {
		if (this.lpv == null) {
			return false;
		}
		return this.lpv.estaDisp(ini, fim) && !this.lpv.estaVazio();
	}


	@Override
	public void update(String consumidorUsername, List<ProdutoInfo> listaDeCompras) {

		StringBuilder sb = new StringBuilder();
		sb.append("\n======================================\n");
		sb.append("Notificação para o comerciante: " + this.getUsername());
		sb.append("\nO consumidor: " + consumidorUsername + " fez uma reserva!\n");
		
		sb.append("\n*** Lista de Produtos Reservados ***\n");
		for (ProdutoInfo prodInfo : listaDeCompras) {
			sb.append(prodInfo.getQuantidadePedida() + " " + prodInfo.getNome() + "\n");
		}	
		sb.append("**********************************\n");
		sb.append("======================================\n");

		System.out.println(sb.toString());
	}






}
