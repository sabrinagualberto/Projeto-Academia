package academia.decorator;

public class NutricionistaDecorator extends PlanoDecorator {
    public NutricionistaDecorator(ServicoPlano plano) { super(plano); }

    @Override
    public String getDescricao() { return super.getDescricao() + " + Nutricionista"; }

    @Override
    public double getValor() { return super.getValor() + 150.0; }
}
