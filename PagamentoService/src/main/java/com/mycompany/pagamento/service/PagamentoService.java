package com.mycompany.pagamento.service;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.dto.MetodoPagamento;
import com.mycompany.pagamento.dto.PagamentoResponse;
import com.mycompany.pagamento.dto.PagamentoRequest;
import com.mycompany.pagamento.repository.PagamentoRepository;
import com.mycompany.pagamento.service.pagamento.strategy.MetodoPagamentoStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;
    private final List<MetodoPagamentoStrategy> estrategias;

    public PagamentoService(
            PagamentoRepository repository,
            List<MetodoPagamentoStrategy> estrategias
    ) {
        this.repository = repository;
        this.estrategias = estrategias;
    }

    public PagamentoResponse criarPagamento(PagamentoRequest request) {

        MetodoPagamentoStrategy strategy = escolherStrategy(request.metodo());

        Pagamento pagamento = strategy.processar(
                request.valor(),
                request.idTransacao()
        );

        repository.salvar(pagamento);

        return toResponse(pagamento);
    }

    private MetodoPagamentoStrategy escolherStrategy(MetodoPagamento metodo) {

        return estrategias.stream()
                .filter(strategy -> strategy.metodo() == metodo)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Método de pagamento não encontrado")
                );
    }

    public PagamentoResponse buscarPorId(String idTransacao) {

        Pagamento pagamento = repository.buscarPorIdTransacao(idTransacao)
                .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

        return toResponse(pagamento);
    }

    private PagamentoResponse toResponse(Pagamento pagamento) {
        return new PagamentoResponse(
                pagamento.getValor(),
                pagamento.getIdTransacao(),
                pagamento.getEstadoPagamento().getNomeEstado()
        );
    }
}