package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

public class EstadoCancelado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoCancelado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está cancelado!\n");
}
}