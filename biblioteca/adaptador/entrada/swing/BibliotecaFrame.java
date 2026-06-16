package biblioteca.adaptador.entrada.swing;

import biblioteca.aplicacao.EmprestimoService;
import biblioteca.aplicacao.LivroService;
import javax.swing.*;
import java.awt.*;

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

    JPanel painelPrincipal = new JPanel(new BorderLayout());
    JLabel labelBemVindo = new JLabel("Sistema inicializado com sucesso!", SwingConstants.CENTER);
    labelBemVindo.setFont(new Font("Arial", Font.BOLD, 18));

    painelPrincipal.add(labelBemVindo, BorderLayout.CENTER);
    add(painelPrincipal);
  }

  public void exibir() {
    setVisible(true);
  }
}
