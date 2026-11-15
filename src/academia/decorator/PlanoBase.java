package academia.decorator;

public class PlanoBase implements ServicoPlano {
    private final String nome;
    private final double valor;

    public PlanoBase(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    @Override public String getDescricao() { return nome; }
    @Override public double getValor() { return valor; }
}
