package com.mycompany.delivery.microservicopagamento2.service.pagamento.template;

import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;
import com.mycompany.delivery.microservicopagamento2.domain.estadossolidos.EstadoAprovado;

import java.math.BigDecimal;

public class PagamentoPixProcessor extends ProcessadorPagamentoTemplate {

    @Override
    protected Pagamento executarPagamento(BigDecimal valor, String idTransacao) {
        Pagamento pagamento = new Pagamento(valor, idTransacao);
        pagamento.setEstadoPagamento(new EstadoAprovado(pagamento)); // PIX é instantâneo
        return pagamento;
    }
}