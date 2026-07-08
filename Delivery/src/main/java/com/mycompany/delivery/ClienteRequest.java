package com.mycompany.delivery;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rafael
 */

import java.util.List;

public record ClienteRequest(
        String nome, String tipo, double fidelidade, String logradouro, String bairro, String cidade,
        List<ItemRequest> itens) {

    public record ItemRequest(String nome, int quantidade, double valorUnitario, String tipo) {}
}
