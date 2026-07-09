package com.mycompany.pagamento.web;

import com.mycompany.pagamento.application.exception.PagamentoNaoAutorizadoException;
import com.mycompany.pagamento.application.exception.TransacaoDuplicadaException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(TransacaoDuplicadaException.class)
    public ProblemDetail handleTransacaoDuplicada(TransacaoDuplicadaException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(PagamentoNaoAutorizadoException.class)
    public ProblemDetail handlePagamentoNaoAutorizado(PagamentoNaoAutorizadoException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleArgumentoInvalido(IllegalArgumentException ex) {
        // cobre as validações internas de invariantes da entidade Pagamento
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
