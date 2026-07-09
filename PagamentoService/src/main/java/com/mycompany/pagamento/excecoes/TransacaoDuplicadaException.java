package com.mycompany.pagamento.application.exception;

public class TransacaoDuplicadaException extends PagamentoException {
    public TransacaoDuplicadaException(String idTransacao) {
        super("Já existe um pagamento com o id de transação: " + idTransacao);
    }
}
