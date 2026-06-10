package biblioteca;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerenciarEmprestimo {
  private Integer lerInteiro(Scanner scanner, String mensagem) {
    if(mensagem != null && !mensagem.isEmpty()) System.out.print(mensagem);
    if(!scanner.hasNextInt()) {
      scanner.nextLine();
      return null;
    }
    int valor = scanner.nextInt();
    scanner.nextLine();
    return valor;
  }

  public void gerenciarEmprestimo(ArrayList<Livro> acervo, ArrayList<Emprestimo> emprestimos, Scanner scanner) {
    System.out.println("\n1- Novo Empréstimo | 2- Devolver | 3- Histórico");
    Integer op = lerInteiro(scanner, "");
    if(op == null) {
      System.out.println("Opção inválida.");
      return;
    }

    switch(op) {
      case 1 -> {
        if(acervo.isEmpty()) {
          System.out.println("Acervo vazio. Cadastre livros antes de emprestar.");
          return;
        }
        for(int i=0; i<acervo.size(); i++) System.out.println(i + " - " + acervo.get(i).getTitulo());
        Integer idx = lerInteiro(scanner, "Escolha o índice: ");
        if(idx == null || idx < 0 || idx >= acervo.size()) {
          System.out.println("Índice inválido.");
          return;
        }

        System.out.print("Nome do Aluno: ");
        String aluno = scanner.nextLine();

        LocalDate dataHoje = LocalDate.now();

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dataEmprestimoReal = dataHoje.format(formatador);

        Emprestimo e = new Emprestimo(aluno, dataEmprestimoReal, acervo.get(idx));
        emprestimos.add(e); 
        System.out.println("Empréstimo realizado!");
      }
      case 2 -> {
        System.out.print("Nome do aluno devolvendo: ");
        String aluno = scanner.nextLine();
        for(Emprestimo e : emprestimos) {
          if(e.getNomeLeitor().equalsIgnoreCase(aluno) && !e.getFoiDevolvido()) {
            e.registrarDevolucao();
            System.out.println("Devolução concluída!");
          }
        }
      }
      case 3 -> {
        for(Emprestimo e : emprestimos) {
          String status = e.getFoiDevolvido() ? "[DEVOLVIDO]" : "[PENDENTE]";
          System.out.println(status + " Livro: " + e.getLivroEmprestado().getTitulo() + " | Aluno: " + e.getNomeLeitor());
        }
      }
    }
  }
}
