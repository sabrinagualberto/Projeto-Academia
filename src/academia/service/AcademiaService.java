package academia.service;

import academia.factory.*;
import academia.model.*;
import academia.observer.*;
import academia.decorator.ServicoPlano;
import academia.strategy.DescontoStrategy;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AcademiaService {
    private final List<Aluno> alunos = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();
    private final Notificador notificador = new Notificador();

    public Notificador getNotificador() { return notificador; }

    private TreinoCreator criadorPara(Objetivo objetivo) {
        switch (objetivo) {
            case EMAGRECIMENTO: return new EmagrecimentoCreator();
            case HIPERTROFIA:   return new HipertrofiaCreator();
            default:            return new CondicionamentoCreator();
        }
    }

    public Aluno cadastrarAluno(String nome, String email, Objetivo objetivo,
                                double pesoKg, double metaPesoKg) {
        Aluno aluno = new Aluno(nome, email, objetivo, pesoKg, metaPesoKg);
        criadorPara(objetivo).gerarTreino(aluno);
        alunos.add(aluno);
        return aluno;
    }

    public void trocarObjetivo(Aluno aluno, Objetivo novo) {
        aluno.setObjetivo(novo);
        Treino treino = criadorPara(novo).gerarTreino(aluno);
        notificador.notificar(new Evento(TipoEvento.TREINO_ATUALIZADO, aluno,
                "Seu novo treino: " + treino.getNome()));
    }

    public Matricula matricular(Aluno aluno, ServicoPlano plano,
                                DescontoStrategy desconto, LocalDate inicio, int meses) {
        Matricula m = new Matricula(aluno, plano, desconto, inicio, meses);
        matriculas.add(m);
        return m;
    }

    public void verificarVencimentos(LocalDate hoje, int dias) {
        for (Matricula m : matriculas) {
            long restante = ChronoUnit.DAYS.between(hoje, m.getVencimento());
            if (restante >= 0 && restante <= dias) {
                notificador.notificar(new Evento(TipoEvento.PLANO_VENCENDO, m.getAluno(),
                        "Seu plano vence em " + restante + " dia(s) (" + m.getVencimento() + ")."));
            }
        }
    }

    public void registrarPeso(Aluno aluno, double novoPesoKg) {
        aluno.setPesoKg(novoPesoKg);
        if (aluno.metaAlcancada()) {
            notificador.notificar(new Evento(TipoEvento.META_ALCANCADA, aluno,
                    "Parabéns! Você atingiu sua meta de " + aluno.getMetaPesoKg() + " kg."));
        }
    }

    public List<Aluno> getAlunos() { return alunos; }
    public List<Matricula> getMatriculas() { return matriculas; }
}
