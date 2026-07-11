package com.mycompany.pagamento.service.pagamento.template;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.domain.EstadoPagamento;
import com.mycompany.pagamento.domain.estadossolidos.EstadoAprovado;
import com.mycompany.pagamento.dto.MetodoPagamento;
import com.mycompany.pagamento.service.pagamento.strategy.MetodoPagamentoStrategy;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPixProcessor extends ProcessadorPagamentoTemplate implements MetodoPagamentoStrategy {

    @Override
    protected Pagamento executarPagamento(BigDecimal valor, String idTransacao) {
        Pagamento pagamento = new Pagamento(valor, idTransacao);
        //pagamento.setEstadoPagamento(new EstadoAprovado(pagamento)); // PIX é instantâneo
        pagamento.getEstadoPagamento().setEstadoProcessando();
        pagamento.getEstadoPagamento().setEstadoAprovado();
        return pagamento;
    }

    @Override
    public MetodoPagamento metodo() {
        return MetodoPagamento.PIX;
    }
}