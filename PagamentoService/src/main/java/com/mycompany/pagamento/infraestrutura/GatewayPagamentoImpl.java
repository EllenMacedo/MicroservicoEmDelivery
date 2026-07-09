package com.mycompany.pagamento.infrastructure;

import com.mycompany.pagamento.application.GatewayPagamento;
import com.mycompany.pagamento.domain.Pagamento;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GatewayPagamentoImpl implements GatewayPagamento {

    private final RestClient restClient;

    public GatewayPagamentoImpl(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.gateway-exemplo.com")
                .build();
    }

    @Override
    public boolean autorizar(Pagamento pagamento) {
        AutorizacaoRequest request = new AutorizacaoRequest(
                pagamento.getIdTransacao(),
                pagamento.getValor()
        );

        AutorizacaoResponse response = restClient.post()
                .uri("/v1/autorizacoes")
                .body(request)
                .retrieve()
                .body(AutorizacaoResponse.class);

        return response != null && response.aprovado();
    }

    private record AutorizacaoRequest(String idTransacao, java.math.BigDecimal valor) {}

    private record AutorizacaoResponse(boolean aprovado, String motivo) {}
}