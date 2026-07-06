package com.mycompany.delivery.microservicopagamento2.dto;

import java.math.BigDecimal;

public record PagamentoResponse(BigDecimal valor, String idTransacao, String status) {}