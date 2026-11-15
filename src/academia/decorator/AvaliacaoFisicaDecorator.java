package academia.decorator;

public class AvaliacaoFisicaDecorator extends PlanoDecorator {
    public AvaliacaoFisicaDecorator(ServicoPlano plano) { super(plano); }

    @Override
    public String getDescricao() { return super.getDescricao() + " + Avaliação física"; }

    @Override
    public double getValor() { return super.getValor() + 60.0; }
}
