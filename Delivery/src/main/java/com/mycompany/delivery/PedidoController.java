package com.mycompany.delivery;

import com.mycompany.delivery.dto.PedidoResponse;
import com.mycompany.delivery.estoque.EstoqueClient;
import com.mycompany.delivery.model.Cliente;
import com.mycompany.delivery.model.Item;
import com.mycompany.delivery.model.Pedido;
import com.mycompany.delivery.pagamento.PagamentoClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;

@RestController
public class PedidoController {

    private final PagamentoClient pagamentoClient;
    private final EstoqueClient estoqueClient;

    public PedidoController(PagamentoClient pagamentoClient, EstoqueClient estoqueClient) {
        this.pagamentoClient = pagamentoClient;
        this.estoqueClient = estoqueClient;
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

        List<Item> itens = (request.itens() == null ? List.<ClienteRequest.ItemRequest>of() : request.itens())
                .stream()
                .map(i -> new Item(i.nome(), i.quantidade(), i.valorUnitario(), i.tipo()))
                .toList();
        itens.forEach(pedido::adicionarItem);

        // 1) Reserva o estoque primeiro - se faltar algum item, nem tenta cobrar o cliente.
        if (!itens.isEmpty()) {
            estoqueClient.reservarItens(itens);
        }

        // 2) Estoque OK, agora sim aciona o pagamento.
        var pagamento = pagamentoClient.criarPagamento(
                BigDecimal.valueOf(pedido.calcularValorTotal()),
                "PEDIDO-" + System.currentTimeMillis()
        );

        return new PedidoResponse(pedido, pagamento);
    }

    // Traduz o erro 409/404 vindo do EstoqueService em algo legivel pra quem chamou o /pedidos.
    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String tratarErroDeEstoque(RestClientResponseException ex) {
        return "Nao foi possivel confirmar o pedido: " + ex.getResponseBodyAsString();
    }
}