package academia.factory;

public class HipertrofiaCreator extends TreinoCreator {
    @Override
    protected Treino criarTreino() {
        return new TreinoHipertrofia();
    }
}
