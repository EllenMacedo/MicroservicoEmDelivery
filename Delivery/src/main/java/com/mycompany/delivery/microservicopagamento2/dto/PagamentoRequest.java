package com.mycompany.delivery.microservicopagamento2.dto;

import java.math.BigDecimal;

public record PagamentoRequest(
        BigDecimal valor,
        String idTransacao,
        MetodoPagamento metodo
) {}