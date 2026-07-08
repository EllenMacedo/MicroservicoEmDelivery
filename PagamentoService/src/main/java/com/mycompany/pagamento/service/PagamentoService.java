package com.mycompany.pagamento.service;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.dto.*;
import com.mycompany.pagamento.repository.PagamentoRepository;
import com.mycompany.pagamento.service.pagamento.template.*;
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