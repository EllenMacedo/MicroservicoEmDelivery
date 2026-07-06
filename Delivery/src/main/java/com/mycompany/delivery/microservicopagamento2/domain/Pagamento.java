package com.mycompany.delivery.microservicopagamento2.domain;

import com.mycompany.delivery.microservicopagamento2.domain.estadossolidos.EstadoPendente;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pagamento{
//private UUID id;

//private UUID idPedido;

private BigDecimal valor;

//private EstrategiaMetodoPagamento estrategiaMetodoPagamento;

private EstadoPagamento estadoPagamento;

private String idTransacao;

private LocalDateTime criadoEm;

private LocalDateTime atualizadoEm;

public Pagamento(BigDecimal valor, String idTransacao){
this.valor=valor;
validarValor();
estadoPagamento = new EstadoPendente(this);
this.idTransacao=idTransacao;//se for pra gerar id aleatoriamente que gere quando Pagamento for criado, ou modifique aqui
validarIdTransacao();
this.criadoEm=LocalDateTime.now();
}

public BigDecimal getValor(){
return valor;
}

public String getIdTransacao(){
    return idTransacao;
}

public LocalDateTime getCriadoEm(){
    return criadoEm;
}

public LocalDateTime getAtualizadoEm(){
    return atualizadoEm;
}

public EstadoPagamento getEstadoPagameento(){
    return estadoPagamento;
}

private void validarValor(){
    if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
    throw new IllegalArgumentException("Pagamento.java: Valor inválido");
}
}

private void validarIdTransacao(){
if (idTransacao == null || idTransacao.isBlank()) {
    throw new IllegalArgumentException("Pagamento.java: Id da transação inválido.");
}
}

public void setEstadoPagamento(EstadoPagamento estadoPagamento){
this.estadoPagamento = estadoPagamento;
this.atualizadoEm = LocalDateTime.now();
}

}