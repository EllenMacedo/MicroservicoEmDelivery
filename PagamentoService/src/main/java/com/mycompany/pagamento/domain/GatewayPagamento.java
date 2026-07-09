import com.mycompany.pagamento.domain.Pagamento;

public interface GatewayPagamento {
    boolean autorizar(Pagamento pagamento);
}