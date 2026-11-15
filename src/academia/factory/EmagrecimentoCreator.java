package academia.factory;

public class EmagrecimentoCreator extends TreinoCreator {
    @Override
    protected Treino criarTreino() {
        return new TreinoEmagrecimento();
    }
}
