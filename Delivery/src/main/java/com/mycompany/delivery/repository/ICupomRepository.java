package com.mycompany.delivery.repository;

import com.mycompany.delivery.model.CupomDescontoPedido;
import java.util.Optional;

public interface ICupomRepository {
    Optional<CupomDescontoPedido> buscarCupom(String codigo);
}
