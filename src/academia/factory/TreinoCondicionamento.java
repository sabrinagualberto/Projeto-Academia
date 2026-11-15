package academia.factory;

import academia.model.Exercicio;
import java.util.Arrays;
import java.util.List;

public class TreinoCondicionamento implements Treino {
    private final List<Exercicio> exercicios = Arrays.asList(
            new Exercicio("Corrida leve (minutos)", 1, 20), new Exercicio("Polichinelo", 3, 30), new Exercicio("Corda naval", 4, 30), new Exercicio("Abdominal", 3, 20));

    @Override
    public String getNome() { return "Treino de Condicionamento"; }

    @Override
    public List<Exercicio> getExercicios() { return exercicios; }
}
