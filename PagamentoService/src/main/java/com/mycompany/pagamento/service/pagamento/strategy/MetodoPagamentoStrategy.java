package com.mycompany.pagamento.service.pagamento.strategy;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.dto.MetodoPagamento;

import java.math.BigDecimal;

public interface MetodoPagamentoStrategy {

    MetodoPagamento metodo();

    Pagamento processar(BigDecimal valor, String idTransacao);
}