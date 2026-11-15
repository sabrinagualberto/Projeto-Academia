package academia.strategy;

public interface DescontoStrategy {
    double aplicar(double valor);
    String getNome();
}
