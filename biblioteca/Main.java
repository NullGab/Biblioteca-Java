package biblioteca;

import biblioteca.adaptador.entrada.swing.BibliotecaFrame;
import biblioteca.adaptador.saida.persistencia.EmprestimoRepositoryAdp;
import biblioteca.adaptador.saida.persistencia.LivroRepositoryAdp;
import biblioteca.aplicacao.EmprestimoRepository;
import biblioteca.aplicacao.EmprestimoService;
import biblioteca.aplicacao.LivroRepository;
import biblioteca.aplicacao.LivroService;

public class Main {
  public static void main(String[] args) {
    System.out.println("LOG: Inicializando sistema...");

    LivroRepository livroRepository = new LivroRepositoryAdp();
    EmprestimoRepository emprestimoRepository = new EmprestimoRepositoryAdp();

    LivroService livroService = new LivroService(livroRepository);
    EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, livroRepository);

    BibliotecaFrame frame = new BibliotecaFrame(livroService, emprestimoService);

    System.out.println("LOG: Grafo montado. Abrindo interface gráfica.");
    frame.exibir();
  }
}
