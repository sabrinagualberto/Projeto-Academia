package academia.model;

import academia.factory.Treino;

public class Aluno {
    private final String nome;
    private final String email;
    private Objetivo objetivo;
    private double pesoKg;
    private final double metaPesoKg;
    private Treino treinoAtual;

    public Aluno(String nome, String email, Objetivo objetivo, double pesoKg, double metaPesoKg) {
        this.nome = nome;
        this.email = email;
        this.objetivo = objetivo;
        this.pesoKg = pesoKg;
        this.metaPesoKg = metaPesoKg;
    }

    public boolean metaAlcancada() {
        switch (objetivo) {
            case EMAGRECIMENTO: return pesoKg <= metaPesoKg;
            case HIPERTROFIA:   return pesoKg >= metaPesoKg;
            default:            return false;
        }
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public Objetivo getObjetivo() { return objetivo; }
    public void setObjetivo(Objetivo objetivo) { this.objetivo = objetivo; }
    public double getPesoKg() { return pesoKg; }
    public void setPesoKg(double pesoKg) { this.pesoKg = pesoKg; }
    public double getMetaPesoKg() { return metaPesoKg; }
    public Treino getTreinoAtual() { return treinoAtual; }
    public void setTreinoAtual(Treino treinoAtual) { this.treinoAtual = treinoAtual; }

    @Override
    public String toString() {
        return nome + " (" + objetivo + ", " + pesoKg + " kg)";
    }
}
