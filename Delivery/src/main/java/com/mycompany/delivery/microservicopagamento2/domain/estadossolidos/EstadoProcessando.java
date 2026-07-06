package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

public class EstadoProcessando extends EstadoPagamento{
//possiveis mudanças de estado
//processando->aprovado
//processando->rejeitado
//processando->cancelado
public EstadoProcessando(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está processando!\n");
}
@Override
public void setEstadoAprovado(){
pagamento.setEstadoPagamento(new EstadoAprovado(pagamento));
}
@Override
public void setEstadoRejeitado(){
pagamento.setEstadoPagamento(new EstadoRejeitado(pagamento));
}
@Override
public void setEstadoCancelado(){
pagamento.setEstadoPagamento(new EstadoCancelado(pagamento));
}
}