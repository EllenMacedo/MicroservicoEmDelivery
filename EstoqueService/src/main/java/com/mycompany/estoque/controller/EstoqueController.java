package com.mycompany.estoque.controller;

import com.mycompany.estoque.dto.ProdutoResponse;
import com.mycompany.estoque.dto.ReservaRequest;
import com.mycompany.estoque.dto.ReservaResponse;
import com.mycompany.estoque.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ProdutoResponse> consultar(@PathVariable String nome) {
        var produto = service.consultar(nome);
        return ResponseEntity.ok(new ProdutoResponse(produto.getNome(), produto.getQuantidadeDisponivel()));
    }

    @PostMapping("/reservar")
    public ResponseEntity<ReservaResponse> reservar(@RequestBody ReservaRequest request) {
        service.reservarItens(request.itens());
        return ResponseEntity.ok(new ReservaResponse(true, "Estoque reservado com sucesso"));
    }
}
