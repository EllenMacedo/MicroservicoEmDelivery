package com.mycompany.delivery.pagamento;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Service
public class PagamentoClient {

    private final RestClient restClient;

    public PagamentoClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public PagamentoResponse criarPagamento(BigDecimal valor, String idTransacao) {

        PagamentoRequest request = new PagamentoRequest(
                valor,
                idTransacao,
                "PIX"
        );

        return restClient.post()
                .uri("/pagamentos")
                .body(request)
                .retrieve()
                .body(PagamentoResponse.class);
    }

    public record PagamentoRequest(
            BigDecimal valor,
            String idTransacao,
            String metodo
    ) {}

    public record PagamentoResponse(
            BigDecimal valor,
            String idTransacao,
            String status
    ) {}
}