package com.mycompany.pagamento.dto;

import java.math.BigDecimal;

public record PagamentoRequest(
        BigDecimal valor,
        String idTransacao,
        MetodoPagamento metodo
) {}