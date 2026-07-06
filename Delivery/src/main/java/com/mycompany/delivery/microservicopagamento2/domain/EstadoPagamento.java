package com.mycompany.delivery.microservicopagamento2.domain;

public abstract class EstadoPagamento{
//possiveis mudanças de estado
//pendente->processando->aprovado->reembolsado
//pendente->processando->rejeitado
//pendente->processando->cancelado
protected Pagamento pagamento;
public EstadoPagamento(Pagamento pagamento){
    this.pagamento=pagamento;
}
public String getNomeEstado()
{
return this.getClass().getSimpleName().toLowerCase();
}
public void setEstadoPendente() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como pendente um pedido que está: " + getNomeEstado());
}
public void setEstadoProcessando() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como processando um pedido que está: " + getNomeEstado());
}
public void setEstadoAprovado() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como aprovado um pedido que está: " + getNomeEstado());
}
public void setEstadoRejeitado() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como rejeitado um pedido que está: " + getNomeEstado());
}
public void setEstadoCancelado() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como cancelado um pedido que está: " + getNomeEstado());
}
public void setEstadoReembolsado() {
throw new RuntimeException("EstadoPagamento.java: Não é possível botar como reembolsado um pedido que está: " + getNomeEstado());
}
}