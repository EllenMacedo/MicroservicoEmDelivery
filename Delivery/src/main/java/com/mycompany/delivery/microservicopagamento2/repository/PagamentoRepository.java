package com.mycompany.delivery.microservicopagamento2.repository;

import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PagamentoRepository {

    private final Map<String, Pagamento> banco = new ConcurrentHashMap<>();

    public void salvar(Pagamento pagamento) {
        banco.put(pagamento.getIdTransacao(), pagamento);
    }

    public Optional<Pagamento> buscarPorIdTransacao(String idTransacao) {
        return Optional.ofNullable(banco.get(idTransacao));
    }

    public void deletar(String idTransacao) {
        banco.remove(idTransacao);
    }
}