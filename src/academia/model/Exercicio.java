package academia.model;

public class Exercicio {
    private final String nome;
    private final int series;
    private final int repeticoes;

    public Exercicio(String nome, int series, int repeticoes) {
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
    }

    public String getNome() { return nome; }
    public int getSeries() { return series; }
    public int getRepeticoes() { return repeticoes; }

    @Override
    public String toString() {
        return nome + " - " + series + "x" + repeticoes;
    }
}
