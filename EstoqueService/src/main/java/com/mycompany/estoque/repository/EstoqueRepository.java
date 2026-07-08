package com.mycompany.estoque.repository;

import com.mycompany.estoque.domain.Produto;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class EstoqueRepository {
    
    private final Map<String, Produto> produtos = new ConcurrentHashMap<>();

    public EstoqueRepository() {
        salvar(new Produto("Caderno", 50));
        salvar(new Produto("Borracha", 100));
        salvar(new Produto("Biscoito", 30));
        salvar(new Produto("Pao", 20));
        salvar(new Produto("Livro", 10));
        salvar(new Produto("Jogo", 5));
    }

    public void salvar(Produto produto) {
        produtos.put(produto.getNome(), produto);
    }

    public Optional<Produto> buscarPorNome(String nome) {
        return Optional.ofNullable(produtos.get(nome));
    }
}
