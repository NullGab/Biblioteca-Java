package biblioteca.adaptador.entrada.swing;

import biblioteca.aplicacao.EmprestimoService;
import biblioteca.aplicacao.LivroService;
import javax.swing.*;

public class BibliotecaFrame extends JFrame {

  private final LivroService livroService;
  private final EmprestimoService emprestimoService;

  public BibliotecaFrame(LivroService livroService, EmprestimoService emprestimoService) {
    this.livroService = livroService;
    this.emprestimoService = emprestimoService;

    setTitle("Sistema de Biblioteca");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null); 

    JTabbedPane painelDeAbas = new JTabbedPane();

    painelDeAbas.addTab("Cadastrar Livro", new PainelCadastroLivro(livroService));

    painelDeAbas.addTab("Acervo de Livros", new PainelAcervoLivro(livroService));
    painelDeAbas.addTab("Gerenciar Empréstimos", new PainelGerenciarEmprestimos(emprestimoService));

    add(painelDeAbas);
  }

  public void exibir() {
    setVisible(true);
  }
}
