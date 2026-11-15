package academia.observer;

import academia.model.Aluno;

public class Evento {
    private final TipoEvento tipo;
    private final Aluno aluno;
    private final String mensagem;

    public Evento(TipoEvento tipo, Aluno aluno, String mensagem) {
        this.tipo = tipo;
        this.aluno = aluno;
        this.mensagem = mensagem;
    }

    public TipoEvento getTipo() { return tipo; }
    public Aluno getAluno() { return aluno; }
    public String getMensagem() { return mensagem; }
}
