package academia.factory;

import academia.model.Exercicio;
import java.util.Arrays;
import java.util.List;

public class TreinoEmagrecimento implements Treino {
    private final List<Exercicio> exercicios = Arrays.asList(
            new Exercicio("Esteira (minutos)", 1, 30), new Exercicio("Burpee", 4, 12), new Exercicio("Agachamento livre", 3, 15), new Exercicio("Prancha (segundos)", 3, 40));

    @Override
    public String getNome() { return "Treino de Emagrecimento"; }

    @Override
    public List<Exercicio> getExercicios() { return exercicios; }
}
