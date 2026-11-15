package academia.factory;

import academia.model.Exercicio;
import java.util.List;

public interface Treino {
    String getNome();
    List<Exercicio> getExercicios();
}
