package com.mycompany.pagamento.service.pagamento.template;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.domain.estadossolidos.EstadoProcessando;
import com.mycompany.pagamento.dto.MetodoPagamento;
import com.mycompany.pagamento.service.pagamento.strategy.MetodoPagamentoStrategy;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PagamentoCartaoProcessor extends ProcessadorPagamentoTemplate implements MetodoPagamentoStrategy {

    @Override
    protected Pagamento executarPagamento(BigDecimal valor, String idTransacao) {
        Pagamento pagamento = new Pagamento(valor, idTransacao);
        pagamento.setEstadoPagamento(new EstadoProcessando(pagamento)); // cartão pode demorar
        return pagamento;
    }

    @Override
    public MetodoPagamento metodo() {
        return MetodoPagamento.CARTAO;
    }
}