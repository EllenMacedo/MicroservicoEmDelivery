package com.mycompany.pagamento.controller;

import com.mycompany.pagamento.dto.PagamentoRequest;
import com.mycompany.pagamento.dto.PagamentoResponse;
import com.mycompany.pagamento.service.PagamentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService service;

    public PagamentoController(PagamentoService service) {
        this.service = service;
    }

    // ENDPOINT ÚNICO DE CRIAÇÃO
    @PostMapping
    public PagamentoResponse criar(@RequestBody PagamentoRequest request) {
        return service.criarPagamento(request);
    }

    // CONSULTA POR ID
    @GetMapping("/{idTransacao}")
    public PagamentoResponse buscar(@PathVariable String idTransacao) {
        return service.buscarPorId(idTransacao);
    }
}