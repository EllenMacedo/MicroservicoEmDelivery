package com.mycompany.delivery.microservicopagamento2.domain.estadossolidos;

import com.mycompany.delivery.microservicopagamento2.domain.EstadoPagamento;
import com.mycompany.delivery.microservicopagamento2.domain.Pagamento;

public class EstadoRejeitado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoRejeitado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está rejeitado!\n");
}
}