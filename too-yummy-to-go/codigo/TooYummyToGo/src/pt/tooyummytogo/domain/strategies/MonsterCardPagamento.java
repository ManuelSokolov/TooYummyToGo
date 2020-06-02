package pt.tooyummytogo.domain.strategies;

import com.monstercard.Card;
import com.monstercard.MonsterCardAPI;

import pt.portugueseexpress.InvalidCardException;
import pt.tooyummytogo.domain.AbstractMeioDePagamento;

public class MonsterCardPagamento extends AbstractMeioDePagamento {

	
	public MonsterCardPagamento(double total, String numeroCartao, String dataValidade,
			String cvc) {
		super( total, numeroCartao, dataValidade, cvc);
		
	}
	
	@Override
	/**
	 * Verifica se o pagamento eh efetuado
	 * @return true se o pagamento foi efetuado com sucesso
	 */
	public boolean efectuaPagamento() {
		//para ser valido ano tem de estar em 20XX
		Card cartao = new Card(numeroCartao, cvc, getMes(dataValidade), getAno(dataValidade));
		boolean pagamentoEfectuado=false;
		MonsterCardAPI monsterCard = new MonsterCardAPI();

		if (monsterCard.isValid(cartao)) { 
			if(monsterCard.block(cartao, total)){
				pagamentoEfectuado = monsterCard.charge(cartao, total);
			}
		}
		return pagamentoEfectuado;
	}

	


}
