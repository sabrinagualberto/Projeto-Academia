package academia.strategy;

public class SemDesconto implements DescontoStrategy {
    @Override public double aplicar(double valor) { return valor; }
    @Override public String getNome() { return "Sem desconto"; }
}
