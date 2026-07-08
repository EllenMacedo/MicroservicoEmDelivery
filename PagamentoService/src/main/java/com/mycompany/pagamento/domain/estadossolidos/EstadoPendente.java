package com.mycompany.pagamento.domain.estadossolidos;

import com.mycompany.pagamento.domain.EstadoPagamento;
import com.mycompany.pagamento.domain.Pagamento;

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