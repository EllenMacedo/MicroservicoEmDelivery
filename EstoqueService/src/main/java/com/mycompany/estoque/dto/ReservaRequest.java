package com.mycompany.estoque.dto;

import java.util.List;

public record ReservaRequest(List<ItemReservaRequest> itens) {
}
