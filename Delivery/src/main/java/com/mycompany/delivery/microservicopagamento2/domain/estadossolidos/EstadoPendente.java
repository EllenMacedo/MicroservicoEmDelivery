package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

public class EstadoPendente extends EstadoPagamento{
//possiveis mudanças de estado
//pendente->processando
public EstadoPendente(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está pendente!\n");
}
@Override
public void setEstadoProcessando(){
pagamento.setEstadoPagamento(new EstadoProcessando(pagamento));
}
}