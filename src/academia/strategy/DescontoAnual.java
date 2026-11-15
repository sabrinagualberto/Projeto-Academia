package academia.strategy;

public class DescontoAnual implements DescontoStrategy {
    @Override public double aplicar(double valor) { return valor * 0.85; }
    @Override public String getNome() { return "Desconto plano anual (15%)"; }
}
