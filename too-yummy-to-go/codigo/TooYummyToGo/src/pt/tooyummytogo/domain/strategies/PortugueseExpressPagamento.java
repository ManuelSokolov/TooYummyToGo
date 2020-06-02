package pt.tooyummytogo.domain.strategies;

import pt.portugueseexpress.InvalidCardException;
import pt.portugueseexpress.PortugueseExpress;
import pt.tooyummytogo.domain.AbstractMeioDePagamento;


public class PortugueseExpressPagamento extends AbstractMeioDePagamento {
	
	

	public PortugueseExpressPagamento(double total, String numeroCartao,
			String dataValidade, String cvc) {
		super(total, numeroCartao, dataValidade, cvc);
	}

	@Override
	/**
	 * Verifica se o pagamento eh efetuado
	 * @return true se o pagamento foi efetuado com sucesso
	 */
	public boolean efectuaPagamento() throws InvalidCardException {
		PortugueseExpress portugueseExpress = new PortugueseExpress();
		boolean pagamentoEfectuado=false;
		//Converter dados para inteiros
		int ccv = Integer.parseInt(cvc);
		int mes = Integer.parseInt(getMes(dataValidade));
		int ano = Integer.parseInt(getAno(dataValidade));
		
		portugueseExpress.setNumber(numeroCartao);
		portugueseExpress.setCcv(ccv);
		portugueseExpress.setMonth(mes);
		portugueseExpress.setYear(ano);
		
		if (portugueseExpress.validate()) {
			portugueseExpress.block(total);
			portugueseExpress.charge(total);
			pagamentoEfectuado = true;
		}		
		return pagamentoEfectuado;
	}
	

}
