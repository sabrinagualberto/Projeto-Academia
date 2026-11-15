package academia.observer;

public class NotificacaoEmail implements AlunoObserver {
    @Override
    public void atualizar(Evento e) {
        if (e.getTipo() == TipoEvento.PLANO_VENCENDO) {
            System.out.println("  [E-MAIL para " + e.getAluno().getEmail() + "] "
                    + e.getMensagem());
        }
    }
}
