package com.mycompany.pagamento.service.pagamento.template;

import com.mycompany.pagamento.domain.Pagamento;

import java.math.BigDecimal;

public abstract class ProcessadorPagamentoTemplate {

    public final Pagamento processar(BigDecimal valor, String idTransacao) {

        validar(valor, idTransacao);

        Pagamento pagamento = executarPagamento(valor, idTransacao);

        atualizarStatus(pagamento);

        registrarLog(pagamento);

        return pagamento;
    }

    private void validar(BigDecimal valor, String idTransacao) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        if (idTransacao == null || idTransacao.isBlank()) {
            throw new IllegalArgumentException("Transação inválida");
        }
    }

    protected abstract Pagamento executarPagamento(BigDecimal valor, String idTransacao);

    protected void atualizarStatus(Pagamento pagamento) {
        // estado inicial padrão
    }

    private void registrarLog(Pagamento pagamento) {
        System.out.println("Pagamento processado: " + pagamento.getIdTransacao());
    }
}