package com.mycompany.delivery.estoque;

import com.mycompany.delivery.model.Item;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class EstoqueClient {

    private final RestClient restClient;

    public EstoqueClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    /**
     * Reserva no EstoqueService todos os itens do pedido de uma vez.
     * Se algum item nao tiver estoque suficiente, o EstoqueService responde
     * com erro (409) e o RestClient lanca uma excecao aqui - o pedido nao
     * deve seguir para o pagamento nesse caso.
     */
    public void reservarItens(List<Item> itens) {
        List<ItemReservaRequest> itensRequest = itens.stream()
                .map(item -> new ItemReservaRequest(item.getNome(), item.getQuantidade()))
                .toList();

        restClient.post()
                .uri("/estoque/reservar")
                .body(new ReservaRequest(itensRequest))
                .retrieve()
                .body(ReservaResponse.class);
    }

    public record ItemReservaRequest(String nome, int quantidade) {}

    public record ReservaRequest(List<ItemReservaRequest> itens) {}

    public record ReservaResponse(boolean sucesso, String mensagem) {}
}
