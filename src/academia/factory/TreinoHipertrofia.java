package academia.factory;

import academia.model.Exercicio;
import java.util.Arrays;
import java.util.List;

public class TreinoHipertrofia implements Treino {
    private final List<Exercicio> exercicios = Arrays.asList(
            new Exercicio("Supino reto", 4, 10), new Exercicio("Agachamento com barra", 4, 8), new Exercicio("Remada curvada", 4, 10), new Exercicio("Desenvolvimento", 3, 12));

    @Override
    public String getNome() { return "Treino de Hipertrofia"; }

    @Override
    public List<Exercicio> getExercicios() { return exercicios; }
}
