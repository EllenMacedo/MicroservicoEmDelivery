package com.mycompany.pagamento.domain.estadossolidos;

import com.mycompany.pagamento.domain.EstadoPagamento;
import com.mycompany.pagamento.domain.Pagamento;

public class EstadoReembolsado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoReembolsado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento foi reembolsado!\n");
}
}