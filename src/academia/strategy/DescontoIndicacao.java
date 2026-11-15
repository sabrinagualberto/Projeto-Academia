package academia.strategy;

public class DescontoIndicacao implements DescontoStrategy {
    @Override public double aplicar(double valor) { return valor * 0.90; }
    @Override public String getNome() { return "Desconto por indicação (10%)"; }
}
