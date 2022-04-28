public class Reserva implements Pagamento{
    Cliente cliente;
    boolean pagamentoAVista;

    Reserva(Cliente cliente, boolean pagamentoAVista){
        this.cliente = cliente;
        this.pagamentoAVista = pagamentoAVista;
    }

    @Override
    public String toString() {
        String tipoCliente = "Tipo do Cliente: ";
        String tipoPagamento = "Pagamento: ";
        String nomeCliente = "Nome: " + this.cliente.getNome();

        if (this.cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) this.cliente;
            tipoCliente += pf.getClass().getName();
        } else {
            PessoaJuridica pj = (PessoaJuridica) this.cliente;
            tipoCliente += pj.getClass().getName();
        }

        if(this.pagamentoAVista) {
            tipoPagamento += "À Vista";
        } else {
            tipoPagamento += "Parcelado";
        }

        return tipoCliente + ", " + nomeCliente + ", " + tipoPagamento;
    }

    @Override
    public double calcularPagamento() {
        double valor = 3200.00;
        double desconto = 0.1;

        if (this.pagamentoAVista) {
            valor = valor * (1 - desconto);
        }
        return valor;
    }
}