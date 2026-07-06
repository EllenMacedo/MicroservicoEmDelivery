package com.mycompany.pagamento.service.pagamento.template;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.domain.estadossolidos.EstadoAprovado;

import java.math.BigDecimal;

public class PagamentoPixProcessor extends ProcessadorPagamentoTemplate {

    @Override
    protected Pagamento executarPagamento(BigDecimal valor, String idTransacao) {
        Pagamento pagamento = new Pagamento(valor, idTransacao);
        pagamento.setEstadoPagamento(new EstadoAprovado(pagamento)); // PIX é instantâneo
        return pagamento;
    }
}