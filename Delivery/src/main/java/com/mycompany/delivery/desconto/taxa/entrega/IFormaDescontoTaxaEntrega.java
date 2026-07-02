package com.mycompany.delivery.desconto.taxa.entrega;

import com.mycompany.delivery.model.CupomDescontoEntrega;
import com.mycompany.delivery.model.Pedido;

public interface IFormaDescontoTaxaEntrega {
    CupomDescontoEntrega calcularDesconto(Pedido pedido);

    boolean seAplica(Pedido pedido);
}
