package com.mycompany.pagamento.domain;

//import com.mycompany.pagamento.domain.estadossolidos.EstadoPendente;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;

import com.mycompany.pagamento.domain.Pagamento;
import com.mycompany.pagamento.domain.estadossolidos.EstadoAprovado;
import com.mycompany.pagamento.application.exception.TransacaoDuplicadaException;
import com.mycompany.pagamento.application.exception.PagamentoNaoAutorizadoException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class Pagamento{
//    private BigDecimal valor;
//    private EstadoPagamento estadoPagamento;
//    private String idTransacao;
//    private LocalDateTime criadoEm;
//    private LocalDateTime atualizadoEm;
//
//    public Pagamento(BigDecimal valor, String idTransacao){
//        this.valor=valor;
//        validarValor();
//        estadoPagamento = new EstadoPendente(this);
//        this.idTransacao=idTransacao;//se for pra gerar id aleatoriamente que gere quando Pagamento for criado, ou modifique aqui
//        validarIdTransacao();
//        this.criadoEm=LocalDateTime.now();
//    }
//
//    public BigDecimal getValor(){
//        return valor;
//    }
//
//    public String getIdTransacao(){
//        return idTransacao;
//    }
//
//    public LocalDateTime getCriadoEm(){
//        return criadoEm;
//    }
//
//    public LocalDateTime getAtualizadoEm(){
//        return atualizadoEm;
//    }
//
//    public EstadoPagamento getEstadoPagamento(){
//        return estadoPagamento;
//    }
//
//    private void validarValor(){
//        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
//            throw new IllegalArgumentException("Pagamento.java: Valor inválido");
//        }
//    }
//
//    private void validarIdTransacao(){
//        if (idTransacao == null || idTransacao.isBlank()) {
//            throw new IllegalArgumentException("Pagamento.java: Id da transação inválido.");
//        }
//    }
//
//    public void setEstadoPagamento(EstadoPagamento estadoPagamento){
//        this.estadoPagamento = estadoPagamento;
//        this.atualizadoEm = LocalDateTime.now();
//    }



    private final PagamentoRepository pagamentoRepository;
    private final GatewayPagamento gatewayPagamento;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            GatewayPagamento gatewayPagamento) {
        this.pagamentoRepository = pagamentoRepository;
        this.gatewayPagamento = gatewayPagamento;
    }

    @Transactional
    public Pagamento processar(BigDecimal valor, String idTransacao) {

        // 1. Validação externa: duplicidade de transação
        if (pagamentoRepository.existsByIdTransacao(idTransacao)) {
            throw new TransacaoDuplicadaException(idTransacao);
        }

        // 2. Criação da entidade — invariantes internas validadas no construtor
        Pagamento pagamento = new Pagamento(valor, idTransacao);

        // 3. Validação externa: autorização junto ao gateway
        boolean autorizado = gatewayPagamento.autorizar(pagamento);
        if (!autorizado) {
            throw new PagamentoNaoAutorizadoException(idTransacao);
        }

        // 4. Transição de estado — regra de domínio real, delegada à entidade
        pagamento.setEstadoPagamento(new EstadoAprovado(pagamento));

        // 5. Persistência
        return pagamentoRepository.save(pagamento);
    }

}