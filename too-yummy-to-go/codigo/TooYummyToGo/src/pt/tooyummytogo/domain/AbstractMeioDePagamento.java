package pt.tooyummytogo.domain;

import pt.portugueseexpress.InvalidCardException;
import pt.tooyummytogo.domain.strategies.MeioDePagamentoStrategy;

/**
 * Esta classe indica a forma de pagamento
 *
 */
public abstract class AbstractMeioDePagamento implements MeioDePagamentoStrategy{
	
	protected double total;
	protected String numeroCartao;
	protected String dataValidade;
	protected String cvc;
	
	/**
	 * Construtor
	 * 
	 * @param pagamentoEfectuado
	 * @param total
	 * @param numeroCartao
	 * @param dataValidade
	 * @param cvc
	 */
	public AbstractMeioDePagamento(double total, String numeroCartao, 
			String dataValidade, String cvc) {
		this.total = total;
		this.numeroCartao = numeroCartao;
		this.dataValidade = dataValidade;
		this.cvc = cvc;
	}
	
	protected String getMes(String dataValidade) {
		String[] split = dataValidade.split("/");
		return split[0];
	}
	
	protected String getAno(String dataValidade) {
		String[] split = dataValidade.split("/");
		return "20" + split[1];
	}
	
}
