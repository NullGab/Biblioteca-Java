package biblioteca;

import biblioteca.adaptador.entrada.swing.BibliotecaFrame;
import biblioteca.adaptador.saida.persistencia.framework.PersistenciaFactory;
import biblioteca.aplicacao.EmprestimoService;
import biblioteca.aplicacao.LivroService;

public class Main {
    public static void main(String[] args) {
        System.out.println("LOG: Inicializando sistema e ligando infraestrutura...");

        PersistenciaFactory factory = new PersistenciaFactory();
        
        factory.criarArquivoSerializado("dados/livros.dat", "dados/emprestimos.dat");

        LivroService livroService = new LivroService(factory.getLivroRepository());
        EmprestimoService emprestimoService = new EmprestimoService(factory.getEmprestimoRepository(), factory.getLivroRepository());
        BibliotecaFrame frame = new BibliotecaFrame(livroService, emprestimoService);

        System.out.println("LOG: Grafo montado. Abrindo interface gráfica.");
        frame.exibir();
    }
}
