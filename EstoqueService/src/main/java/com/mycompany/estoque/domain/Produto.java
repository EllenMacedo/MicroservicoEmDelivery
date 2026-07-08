package com.mycompany.estoque.domain;

public class Produto {

    private final String nome;
    private int quantidadeDisponivel;

    public Produto(String nome, int quantidadeDisponivel) {
        this.nome = nome;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public boolean temQuantidadeSuficiente(int quantidadeSolicitada) {
        return quantidadeDisponivel >= quantidadeSolicitada;
    }

    public void reservar(int quantidadeSolicitada) {
        if (!temQuantidadeSuficiente(quantidadeSolicitada)) {
            throw new IllegalStateException(
                    "Estoque insuficiente para o produto '" + nome + "'. "
                            + "Disponivel: " + quantidadeDisponivel + ", solicitado: " + quantidadeSolicitada);
        }
        this.quantidadeDisponivel -= quantidadeSolicitada;
    }

    public void repor(int quantidade) {
        this.quantidadeDisponivel += quantidade;
    }
}
