package biblioteca;
import java.util.*;

public class Consultar {
  public void consultaLivro(ArrayList<Livro> listaLivro, Scanner scanner){
    if(listaLivro.isEmpty()){
      System.out.println("NADA CADASTRADO.");
      return;
    }

    System.out.println("\n--- CONSULTA ---");
    Iterator<Livro> it = listaLivro.iterator();
    int count = 1;
    while(it.hasNext()){
      System.out.println(count + "°- " + it.next());
      count++;
    }
  }
}
