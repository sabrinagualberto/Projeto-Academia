package academia.factory;

public class CondicionamentoCreator extends TreinoCreator {
    @Override
    protected Treino criarTreino() {
        return new TreinoCondicionamento();
    }
}
