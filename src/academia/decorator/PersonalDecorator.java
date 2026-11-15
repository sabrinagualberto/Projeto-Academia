package academia.decorator;

public class PersonalDecorator extends PlanoDecorator {
    public PersonalDecorator(ServicoPlano plano) { super(plano); }

    @Override
    public String getDescricao() { return super.getDescricao() + " + Personal trainer"; }

    @Override
    public double getValor() { return super.getValor() + 200.0; }
}
