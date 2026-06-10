package biblioteca;
import java.util.*;
import java.time.LocalDate;

public class Cadastro {
  private final int ANO_ATUAL = LocalDate.now().getYear();

  private Integer lerInteiro(Scanner scanner, String mensagem){
    System.out.print(mensagem);
    if(!scanner.hasNextInt()){
      scanner.nextLine();
      return null;
    }
    int valor = scanner.nextInt();
    scanner.nextLine();
    return valor;
  }

  public void cadastrar(ArrayList<Livro> listaLivros, Scanner scanner){
    System.out.println("\n--- CADASTRO ---");
    System.out.print("Nome do livro: ");
    String nome = scanner.nextLine();

    System.out.print("Autor: ");
    String autor = scanner.nextLine();

    System.out.println("1-Romance 2-Suspense 3-Fantasia 4-Computação 5-História");
    Integer op = lerInteiro(scanner, "");

    if(op == null){
      System.out.println("Entrada inválida para gênero.");
      return;
    }

    if(op < 1 || op > 5){
      System.out.println("ERRO: Gênero inexistente. Cadastro cancelado.");
      return; 
    }        
    String genNome = switch(op){
      case 1 -> "Romance"; case 2 -> "Suspense"; case 3 -> "Fantasia";
      case 4 -> "Computação"; default -> "História";
    };

    System.out.print("ISBN (13 dígitos): ");
    String isbn = scanner.nextLine().replaceAll("\\D", "");

    if(isbn.length() != 13) {
      System.out.println("Erro: ISBN inválido!");
      return;
    }

    System.out.print("Editora: ");
    String editora = scanner.nextLine();

    Integer ano = lerInteiro(scanner, "Ano de Publicação: ");
    if(ano == null){
      System.out.println("Entrada inválida para ano.");
      return;
    }
    if(ano > ANO_ATUAL) { System.out.println("Ano inválido!"); return; }

    Integer paginas = lerInteiro(scanner, "Número de páginas: ");
    if(paginas == null){
      System.out.println("Entrada inválida para número de páginas.");
      return;
    }

    System.out.print("Descrição: ");
    String desc = scanner.nextLine();

    Genero g = new Genero(genNome, desc);
    Livro novo = new Livro(nome, autor, isbn, editora, ano, paginas, g);

    listaLivros.add(novo);
    System.out.println("LIVRO CADASTRADO!");
  }
}
