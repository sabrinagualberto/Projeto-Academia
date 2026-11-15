package academia.decorator;

public abstract class PlanoDecorator implements ServicoPlano {
    protected final ServicoPlano plano;

    protected PlanoDecorator(ServicoPlano plano) {
        this.plano = plano;
    }

    @Override public String getDescricao() { return plano.getDescricao(); }
    @Override public double getValor() { return plano.getValor(); }
}
