package academia;

import academia.decorator.*;
import academia.model.*;
import academia.observer.*;
import academia.service.AcademiaService;
import academia.strategy.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final AcademiaService academia = new AcademiaService();
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        academia.getNotificador().inscrever(new NotificacaoApp());
        academia.getNotificador().inscrever(new NotificacaoEmail());

        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Escolha uma opção: ");
            System.out.println();
            executar(opcao);
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n===== ACADEMIA - ACOMPANHAMENTO DE ALUNOS =====");
        System.out.println("1 - Cadastrar aluno");
        System.out.println("2 - Listar alunos");
        System.out.println("3 - Ver treino de um aluno");
        System.out.println("4 - Matricular aluno em um plano");
        System.out.println("5 - Listar matrículas");
        System.out.println("6 - Registrar peso");
        System.out.println("7 - Trocar objetivo do aluno");
        System.out.println("8 - Verificar planos perto do vencimento");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1: cadastrarAluno(); break;
            case 2: listarAlunos(); break;
            case 3: verTreino(); break;
            case 4: matricular(); break;
            case 5: listarMatriculas(); break;
            case 6: registrarPeso(); break;
            case 7: trocarObjetivo(); break;
            case 8: verificarVencimentos(); break;
            case 0: System.out.println("Até logo!"); break;
            default: System.out.println("Opção inválida.");
        }
    }

    private static void cadastrarAluno() {
        System.out.println("--- Cadastro de aluno ---");
        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");
        Objetivo objetivo = lerObjetivo();
        double peso = lerDouble("Peso atual (kg): ");
        double meta = lerDouble("Meta de peso (kg): ");
        Aluno aluno = academia.cadastrarAluno(nome, email, objetivo, peso, meta);
        System.out.println("\nAluno cadastrado: " + aluno);
        mostrarTreino(aluno);
    }

    private static void listarAlunos() {
        List<Aluno> alunos = academia.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (int i = 0; i < alunos.size(); i++) {
            Aluno a = alunos.get(i);
            System.out.println((i + 1) + " - " + a + " | meta: " + a.getMetaPesoKg() + " kg");
        }
    }

    private static void verTreino() {
        Aluno aluno = escolherAluno();
        if (aluno != null) {
            mostrarTreino(aluno);
        }
    }

    private static void mostrarTreino(Aluno aluno) {
        System.out.println(aluno.getNome() + " -> " + aluno.getTreinoAtual().getNome());
        aluno.getTreinoAtual().getExercicios().forEach(e -> System.out.println("    - " + e));
    }

    private static void matricular() {
        Aluno aluno = escolherAluno();
        if (aluno == null) {
            return;
        }

        System.out.println("Plano:");
        System.out.println("1 - Mensal (R$ 120,00)");
        System.out.println("2 - Trimestral (R$ 330,00)");
        System.out.println("3 - Anual (R$ 1200,00)");
        int tipo = lerInt("Escolha o plano: ");
        ServicoPlano plano;
        int meses;
        switch (tipo) {
            case 1: plano = new PlanoBase("Plano Mensal", 120.0); meses = 1; break;
            case 2: plano = new PlanoBase("Plano Trimestral", 330.0); meses = 3; break;
            case 3: plano = new PlanoBase("Plano Anual", 1200.0); meses = 12; break;
            default: System.out.println("Plano inválido."); return;
        }

        if (lerSimNao("Adicionar personal trainer (R$ 200,00)? (s/n): ")) {
            plano = new PersonalDecorator(plano);
        }
        if (lerSimNao("Adicionar nutricionista (R$ 150,00)? (s/n): ")) {
            plano = new NutricionistaDecorator(plano);
        }
        if (lerSimNao("Adicionar avaliação física (R$ 60,00)? (s/n): ")) {
            plano = new AvaliacaoFisicaDecorator(plano);
        }

        System.out.println("Desconto:");
        System.out.println("1 - Sem desconto");
        System.out.println("2 - Plano anual (15%)");
        System.out.println("3 - Indicação (10%)");
        int d = lerInt("Escolha o desconto: ");
        DescontoStrategy desconto;
        switch (d) {
            case 2: desconto = new DescontoAnual(); break;
            case 3: desconto = new DescontoIndicacao(); break;
            default: desconto = new SemDesconto();
        }

        LocalDate inicio = lerData("Data de início (dd/MM/aaaa) ou Enter para hoje: ");
        Matricula m = academia.matricular(aluno, plano, desconto, inicio, meses);
        System.out.println("\nMatrícula criada:\n" + m);
    }

    private static void listarMatriculas() {
        List<Matricula> matriculas = academia.getMatriculas();
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula registrada.");
            return;
        }
        matriculas.forEach(System.out::println);
    }

    private static void registrarPeso() {
        Aluno aluno = escolherAluno();
        if (aluno == null) {
            return;
        }
        double peso = lerDouble("Novo peso (kg): ");
        System.out.println();
        academia.registrarPeso(aluno, peso);
        System.out.println("Peso atualizado: " + aluno);
    }

    private static void trocarObjetivo() {
        Aluno aluno = escolherAluno();
        if (aluno == null) {
            return;
        }
        Objetivo novo = lerObjetivo();
        System.out.println();
        academia.trocarObjetivo(aluno, novo);
        mostrarTreino(aluno);
    }

    private static void verificarVencimentos() {
        int dias = lerInt("Avisar planos que vencem em até quantos dias? ");
        System.out.println();
        academia.verificarVencimentos(LocalDate.now(), dias);
        System.out.println("Verificação concluída.");
    }

    private static Aluno escolherAluno() {
        List<Aluno> alunos = academia.getAlunos();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado. Cadastre um aluno primeiro.");
            return null;
        }
        listarAlunos();
        int n = lerInt("Número do aluno: ");
        if (n < 1 || n > alunos.size()) {
            System.out.println("Aluno inválido.");
            return null;
        }
        return alunos.get(n - 1);
    }

    private static Objetivo lerObjetivo() {
        System.out.println("Objetivo:");
        System.out.println("1 - Emagrecimento");
        System.out.println("2 - Hipertrofia");
        System.out.println("3 - Condicionamento");
        while (true) {
            int o = lerInt("Escolha o objetivo: ");
            switch (o) {
                case 1: return Objetivo.EMAGRECIMENTO;
                case 2: return Objetivo.HIPERTROFIA;
                case 3: return Objetivo.CONDICIONAMENTO;
                default: System.out.println("Objetivo inválido.");
            }
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return entrada.hasNextLine() ? entrada.nextLine().trim() : "";
    }

    private static int lerInt(String mensagem) {
        while (true) {
            if (!entrada.hasNextLine()) {
                return 0;
            }
            System.out.print(mensagem);
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String texto = entrada.hasNextLine() ? entrada.nextLine().trim().replace(',', '.') : "0";
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido (ex.: 70.5).");
            }
        }
    }

    private static boolean lerSimNao(String mensagem) {
        return lerTexto(mensagem).equalsIgnoreCase("s");
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            String texto = lerTexto(mensagem);
            if (texto.isEmpty()) {
                return LocalDate.now();
            }
            try {
                return LocalDate.parse(texto, FORMATO);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato dd/MM/aaaa.");
            }
        }
    }
}
