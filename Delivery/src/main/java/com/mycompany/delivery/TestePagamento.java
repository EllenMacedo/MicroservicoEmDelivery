package com.mycompany.delivery;

import com.mycompany.delivery.microservicopagamento.Pagamento;

import java.math.BigDecimal;

public class TestePagamento {
    public static void main(String[] args) {

        // cria pagamento
        Pagamento pagamento = new Pagamento(
                new BigDecimal("100.00"),
                "TX123"
        );

        System.out.println("Estado inicial: " +
                pagamento.getEstadoPagameento().getNomeEstado());

        // PENDENTE -> PROCESSANDO
        pagamento.getEstadoPagameento().setEstadoProcessando();
        System.out.println("Após processar: " +
                pagamento.getEstadoPagameento().getNomeEstado());

        // PROCESSANDO -> APROVADO
        pagamento.getEstadoPagameento().setEstadoAprovado();
        System.out.println("Após aprovação: " +
                pagamento.getEstadoPagameento().getNomeEstado());

        // APROVADO -> REEMBOLSADO
        pagamento.getEstadoPagameento().setEstadoReembolsado();
        System.out.println("Após reembolso: " +
                pagamento.getEstadoPagameento().getNomeEstado());
    }
}