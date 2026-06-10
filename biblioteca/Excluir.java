package biblioteca;
import java.util.*;

public class Excluir {
  public void excluir(ArrayList<Livro> listaLivro, Scanner scanner){
    System.out.print("Digite o ISBN do livro para excluir: ");
    String isbnParaExcluir = scanner.nextLine().replaceAll("\\D", "");

    Iterator<Livro> it = listaLivro.iterator();
    boolean removido = false;

    while(it.hasNext()){
      Livro atual = it.next();
      if(atual.getIsbn().equals(isbnParaExcluir)){
        it.remove(); 
        removido = true;
        System.out.println("Livro removido com sucesso!");
        break;
      }
    }

    if(!removido) System.out.println("Livro não encontrado.");
  }
}
