package com.mycompany.delivery.dto;

import com.mycompany.delivery.model.Pedido;
import com.mycompany.delivery.pagamento.PagamentoClient.PagamentoResponse;

public record PedidoResponse(
        Pedido pedido,
        PagamentoResponse pagamento
) {}