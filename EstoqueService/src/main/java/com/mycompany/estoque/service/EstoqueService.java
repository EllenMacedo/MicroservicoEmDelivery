package com.mycompany.estoque.service;

import com.mycompany.estoque.domain.Produto;
import com.mycompany.estoque.dto.ItemReservaRequest;
import com.mycompany.estoque.repository.EstoqueRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {

    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public Produto consultar(String nome) {
        return repository.buscarPorNome(nome)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(nome));
    }

    public void reservarItens(List<ItemReservaRequest> itens) {
        List<Produto> produtos = itens.stream()
                .map(item -> buscarEValidar(item))
                .toList();

        for (int i = 0; i < itens.size(); i++) {
            produtos.get(i).reservar(itens.get(i).quantidade());
        }
    }

    private Produto buscarEValidar(ItemReservaRequest item) {
        Produto produto = consultar(item.nome());
        if (!produto.temQuantidadeSuficiente(item.quantidade())) {
            throw new EstoqueInsuficienteException(item.nome(), produto.getQuantidadeDisponivel(), item.quantidade());
        }
        return produto;
    }

    public static class ProdutoNaoEncontradoException extends RuntimeException {
        public ProdutoNaoEncontradoException(String nome) {
            super("Produto '" + nome + "' nao encontrado no estoque");
        }
    }

    public static class EstoqueInsuficienteException extends RuntimeException {
        public EstoqueInsuficienteException(String nome, int disponivel, int solicitado) {
            super("Estoque insuficiente para '" + nome + "'. Disponivel: " + disponivel + ", solicitado: " + solicitado);
        }
    }
}
