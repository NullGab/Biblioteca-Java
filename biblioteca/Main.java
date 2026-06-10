package biblioteca;

import biblioteca.adaptador.entrada.swing.BibliotecaFrame;
import biblioteca.adaptador.saida.persistencia.LivroRepositoryAdp;
import biblioteca.aplicacao.LivroRepository;
import biblioteca.aplicacao.LivroService;

public class Main {
  public static void main(String[] args) {

    System.out.println("LOG: Inicializando sistema...");

    LivroRepository repository = new LivroRepositoryAdp();

    LivroService service = new LivroService(repository);

    BibliotecaFrame frame = new BibliotecaFrame(service);

    System.out.println("LOG: Grafo montado. Abrindo interface gráfica.");
    frame.exibir();
  }
} 
