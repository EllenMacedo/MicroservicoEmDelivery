package com.mycompany.delivery.microservicopagamento.estadossolidos;

import com.mycompany.delivery.microservicopagamento.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento.Pagamento;

public class EstadoAprovado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoAprovado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está aprovado!\n");
}
@Override
public void setEstadoReembolsado(){
pagamento.setEstadoPagamento(new EstadoReembolsado(pagamento));
}
}