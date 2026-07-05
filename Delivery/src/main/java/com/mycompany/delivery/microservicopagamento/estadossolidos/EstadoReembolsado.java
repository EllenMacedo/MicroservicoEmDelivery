package com.mycompany.delivery.microservicopagamento.estadossolidos;

import com.mycompany.delivery.microservicopagamento.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento.Pagamento;

public class EstadoReembolsado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoReembolsado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento foi reembolsado!\n");
}
}