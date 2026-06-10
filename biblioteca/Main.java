package biblioteca;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args){
    ArrayList<Livro> listaLivros = new ArrayList<>();
    ArrayList<Emprestimo> listaEmprestimos = new ArrayList<>();

    Cadastro cadastro = new Cadastro();
    Consultar consultar = new Consultar();
    Excluir excluir = new Excluir();
    GerenciarEmprestimo gerenciar = new GerenciarEmprestimo(); 

    Scanner scanner = new Scanner(System.in);
    int opcao;

    while(true){ 
      System.out.println("\n========== SISTEMA DE BIBLIOTECA ==========");
      System.out.println("1- Cadastrar Livro | 2- Consultar | 3- Excluir | 4- Empréstimos | 0- Sair");
      System.out.print("Escolha: ");

      if(!scanner.hasNext()){
        System.out.println("\nEntrada encerrada. Finalizando sistema.");
        break;
      }

      if(!scanner.hasNextInt()){
        scanner.nextLine();
        System.out.println("Entrada inválida. Digite um número.");
        continue;
      }
      opcao = scanner.nextInt();
      scanner.nextLine();

      if(opcao == 0) break;

      switch (opcao) {
        case 1: cadastro.cadastrar(listaLivros, scanner); break;
        case 2: consultar.consultaLivro(listaLivros, scanner); break;
        case 3: excluir.excluir(listaLivros, scanner); break;
        case 4: gerenciar.gerenciarEmprestimo(listaLivros, listaEmprestimos, scanner); break;
        default: System.out.println("Opção inválida!");
      }
    }
    scanner.close();
  }
}
