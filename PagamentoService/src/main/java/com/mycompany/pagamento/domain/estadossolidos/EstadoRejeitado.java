package com.mycompany.pagamento.domain.estadossolidos;

import com.mycompany.pagamento.domain.EstadoPagamento;
import com.mycompany.pagamento.domain.Pagamento;

public class EstadoRejeitado extends EstadoPagamento{
//possiveis mudanças de estado
//aprovado->reembolsado
public EstadoRejeitado(Pagamento pagamento){
super(pagamento);
//System.out.println("O pagamento está rejeitado!\n");
}
}