package com.mycompany.delivery.microservicopagamento.estadossolidos;

import com.mycompany.delivery.microservicopagamento.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento.Pagamento;

public class EstadoRejeitado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoRejeitado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está rejeitado!\n");
}
}