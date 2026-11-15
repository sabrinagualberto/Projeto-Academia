package academia.observer;

public class NotificacaoApp implements AlunoObserver {
    @Override
    public void atualizar(Evento e) {
        System.out.println("  [APP] " + e.getAluno().getNome()
                + " -> " + e.getTipo() + ": " + e.getMensagem());
    }
}
