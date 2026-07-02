package com.mycompany.delivery;

import com.mycompany.delivery.model.Cliente;
import com.mycompany.delivery.model.Pedido;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Rafael
 */

@RestController
public class PedidoController {

    @PostMapping("/pedidos")
    public Pedido criarPedido(@RequestBody ClienteRequest request) {
        Cliente cliente = new Cliente(
                request.nome(), request.tipo(), request.fidelidade(),
                request.logradouro(), request.bairro(), request.cidade());

        return new Pedido(LocalDateTime.now(), cliente);
    }
}
