package com.mycompany.delivery.microservicopagamento2.service;

import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;
import com.mycompany.delivery.microservicopagamento2.dto.*;
import com.mycompany.delivery.microservicopagamento2.repository.PagamentoRepository;
import com.mycompany.delivery.microservicopagamento2.service.pagamento.template.*;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public PagamentoResponse criarPagamento(PagamentoRequest request) {

        ProcessadorPagamentoTemplate processor = escolherProcessador(request.metodo());

        Pagamento pagamento = processor.processar(
                request.valor(),
                request.idTransacao()
        );

        repository.salvar(pagamento);

        return toResponse(pagamento);
    }

    private ProcessadorPagamentoTemplate escolherProcessador(MetodoPagamento metodo) {
        return switch (metodo) {
            case PIX -> new PagamentoPixProcessor();
            case CARTAO -> new PagamentoCartaoProcessor();
        };
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