package academia.observer;

import java.util.ArrayList;
import java.util.List;

public class Notificador {
    private final List<AlunoObserver> observadores = new ArrayList<>();

    public void inscrever(AlunoObserver o) { observadores.add(o); }
    public void remover(AlunoObserver o) { observadores.remove(o); }

    public void notificar(Evento evento) {
        for (AlunoObserver o : observadores) {
            o.atualizar(evento);
        }
    }
}
