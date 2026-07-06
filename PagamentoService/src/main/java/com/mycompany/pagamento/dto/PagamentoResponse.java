package com.mycompany.pagamento.dto;

import java.math.BigDecimal;

public record PagamentoResponse(BigDecimal valor, String idTransacao, String status) {}