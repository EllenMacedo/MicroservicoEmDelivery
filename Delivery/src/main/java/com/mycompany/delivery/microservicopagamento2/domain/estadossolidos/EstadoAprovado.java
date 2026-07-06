package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

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