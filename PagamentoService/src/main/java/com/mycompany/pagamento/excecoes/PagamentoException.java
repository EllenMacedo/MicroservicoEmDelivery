package com.mycompany.pagamento.application.exception;

public abstract class PagamentoException extends RuntimeException {
    protected PagamentoException(String msg) {
        super(msg);
    }
}
