package com.mycompany.delivery;

import com.mycompany.delivery.dto.PedidoResponse;
import com.mycompany.delivery.model.Cliente;
import com.mycompany.delivery.model.Pedido;
import com.mycompany.delivery.pagamento.PagamentoClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PedidoController {

    private final PagamentoClient pagamentoClient;

    public PedidoController(PagamentoClient pagamentoClient) {
        this.pagamentoClient = pagamentoClient;
    }

    @PostMapping("/pedidos")
    public PedidoResponse criarPedido(@RequestBody ClienteRequest request) {

        Cliente cliente = new Cliente(
                request.nome(),
                request.tipo(),
                request.fidelidade(),
                request.logradouro(),
                request.bairro(),
                request.cidade()
        );

        Pedido pedido = new Pedido(LocalDateTime.now(), cliente);

        var pagamento = pagamentoClient.criarPagamento(
                BigDecimal.valueOf(pedido.calcularValorTotal()),
                "PEDIDO-" + System.currentTimeMillis()
        );

        return new PedidoResponse(pedido, pagamento);
    }
}