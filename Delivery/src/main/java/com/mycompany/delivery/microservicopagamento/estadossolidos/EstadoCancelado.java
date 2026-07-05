package com.mycompany.delivery.microservicopagamento.estadossolidos;

import com.mycompany.delivery.microservicopagamento.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento.Pagamento;

public class EstadoCancelado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoCancelado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está cancelado!\n");
}
}