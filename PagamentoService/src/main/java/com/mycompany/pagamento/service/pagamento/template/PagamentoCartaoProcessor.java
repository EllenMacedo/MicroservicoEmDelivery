package com.mycompany.pagamento.service.pagamento.template;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.domain.estadossolidos.EstadoProcessando;

import java.math.BigDecimal;

public class PagamentoCartaoProcessor extends ProcessadorPagamentoTemplate {

    @Override
    protected Pagamento executarPagamento(BigDecimal valor, String idTransacao) {
        Pagamento pagamento = new Pagamento(valor, idTransacao);
        pagamento.setEstadoPagamento(new EstadoProcessando(pagamento)); // cartão pode demorar
        return pagamento;
    }
}