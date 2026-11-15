package academia.model;

import academia.decorator.ServicoPlano;
import academia.strategy.DescontoStrategy;

import java.time.LocalDate;

public class Matricula {
    private final Aluno aluno;
    private final ServicoPlano plano;
    private DescontoStrategy desconto;
    private final LocalDate inicio;
    private final int meses;

    public Matricula(Aluno aluno, ServicoPlano plano, DescontoStrategy desconto,
                     LocalDate inicio, int meses) {
        this.aluno = aluno;
        this.plano = plano;
        this.desconto = desconto;
        this.inicio = inicio;
        this.meses = meses;
    }

    public LocalDate getVencimento() { return inicio.plusMonths(meses); }

    public double valorFinal() { return desconto.aplicar(plano.getValor()); }

    public void setDesconto(DescontoStrategy desconto) { this.desconto = desconto; }
    public Aluno getAluno() { return aluno; }
    public ServicoPlano getPlano() { return plano; }
    public DescontoStrategy getDesconto() { return desconto; }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | total: R$ %.2f | vence em %s",
                aluno.getNome(), plano.getDescricao(), desconto.getNome(),
                valorFinal(), getVencimento());
    }
}
