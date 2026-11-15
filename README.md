# Sistema de Acompanhamento de Alunos de Academia

Projeto da 2ª VA de Programação III (UPE - Campus Surubim).

Sistema de console em Java para uma academia: cadastra alunos, gera treinos de acordo com o objetivo de cada um,
controla planos com serviços extras e descontos, e envia notificações.

## Funcionalidades

- Cadastro de alunos com objetivo (emagrecimento, hipertrofia ou condicionamento) e meta de peso
- Treino gerado automaticamente conforme o objetivo
- Matrícula em plano mensal, trimestral ou anual, com extras opcionais (personal, nutricionista, avaliação física)
- Descontos aplicáveis ao plano
- Registro de peso e troca de objetivo
- Notificações: treino atualizado, plano vencendo e meta alcançada

## Padrões de projeto

| Padrão | Classe | Pacote | Uso no sistema |
|---|---|---|---|
| Factory Method | A | `factory` | Criação do treino conforme o objetivo |
| Observer | A | `observer` | Notificações de eventos |
| Strategy | A | `strategy` | Regras de desconto |
| Decorator | B | `decorator` | Extras do plano |

## Como executar

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -Dstdout.encoding=UTF-8 -cp out academia.Main
```

No Windows (PowerShell):

```powershell
mkdir out
javac -encoding UTF-8 -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -Dstdout.encoding=UTF-8 -cp out academia.Main
```

## Estrutura

```
src/academia/
  Main.java                  menu interativo
  model/                     Aluno, Objetivo, Exercicio, Matricula
  factory/                   Factory Method
  observer/                  Observer
  strategy/                  Strategy
  decorator/                 Decorator
  service/AcademiaService    fluxo do sistema
```
