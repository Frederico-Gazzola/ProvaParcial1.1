public abstract class Cliente {
    private String nome;

    Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente: " + this.nome;
    }
}