package pt.tooyummytogo.domain.strategies;


public interface MeioDePagamentoStrategy {
	
	public boolean efectuaPagamento() throws pt.portugueseexpress.InvalidCardException;

}
