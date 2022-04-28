public class PessoaJuridica extends Cliente {
    private String cnpj;

    PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return super.toString() + ", CNPJ: " + this.cnpj;
    }
}

