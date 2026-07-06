package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

public class EstadoReembolsado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoReembolsado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento foi reembolsado!\n");
}
}