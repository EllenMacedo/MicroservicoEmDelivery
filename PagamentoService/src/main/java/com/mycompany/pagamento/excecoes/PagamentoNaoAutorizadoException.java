package com.mycompany.pagamento.application.exception;

public class PagamentoNaoAutorizadoException extends PagamentoException {
    public PagamentoNaoAutorizadoException(String idTransacao) {
        super("Transação não autorizada pelo gateway: " + idTransacao);
    }
}
