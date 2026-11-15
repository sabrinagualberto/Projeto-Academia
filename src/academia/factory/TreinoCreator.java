package academia.factory;

import academia.model.Aluno;

public abstract class TreinoCreator {

    protected abstract Treino criarTreino();

    public Treino gerarTreino(Aluno aluno) {
        Treino treino = criarTreino();
        aluno.setTreinoAtual(treino);
        return treino;
    }
}
