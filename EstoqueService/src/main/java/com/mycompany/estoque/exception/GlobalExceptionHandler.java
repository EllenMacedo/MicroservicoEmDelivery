/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.estoque.exception;

import com.mycompany.estoque.dto.ReservaResponse;
import com.mycompany.estoque.service.EstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Rafael
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EstoqueService.EstoqueInsuficienteException.class)
    public ResponseEntity<ReservaResponse> tratarEstoqueInsuficiente(
            EstoqueService.EstoqueInsuficienteException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ReservaResponse(false, ex.getMessage()));
    }

    @ExceptionHandler(EstoqueService.ProdutoNaoEncontradoException.class)
    public ResponseEntity<ReservaResponse> tratarProdutoNaoEncontrado(
            EstoqueService.ProdutoNaoEncontradoException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReservaResponse(false, ex.getMessage()));
    }
}
