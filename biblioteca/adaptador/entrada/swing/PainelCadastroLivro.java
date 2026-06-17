package biblioteca.adaptador.entrada.swing;

import biblioteca.aplicacao.LivroService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCadastroLivro extends JPanel {

  private final LivroService livroService;

  private JTextField txtNome, txtAutor, txtIsbn, txtEditora, txtAno, txtPaginas, txtDescricao;
  private JComboBox<String> comboGenero;

  public PainelCadastroLivro(LivroService livroService) {
    this.livroService = livroService;

    setLayout(new GridLayout(9, 2, 10, 10));
    setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Dá uma margem nas bordas

    add(new JLabel("Nome do Livro:"));
    txtNome = new JTextField();
    add(txtNome);

    add(new JLabel("Autor:"));
    txtAutor = new JTextField();
    add(txtAutor);

    add(new JLabel("Gênero:"));
    String[] generos = {"Selecione...", "Romance", "Suspense", "Fantasia", "Computação", "História"};
    comboGenero = new JComboBox<>(generos);
    add(comboGenero);

    add(new JLabel("ISBN (13 dígitos):"));
    txtIsbn = new JTextField();
    add(txtIsbn);

    add(new JLabel("Editora:"));
    txtEditora = new JTextField();
    add(txtEditora);

    add(new JLabel("Ano de Publicação:"));
    txtAno = new JTextField();
    add(txtAno);

    add(new JLabel("Número de Páginas:"));
    txtPaginas = new JTextField();
    add(txtPaginas);

    add(new JLabel("Descrição rápida:"));
    txtDescricao = new JTextField();
    add(txtDescricao);

    JButton btnSalvar = new JButton("Cadastrar Livro");
    add(new JLabel("")); // Espaço vazio na esquerda para o botão ficar na direita
    add(btnSalvar);

    btnSalvar.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tentarCadastrar();
      }
    });
  }

  private void tentarCadastrar() {
    try {
      String nome = txtNome.getText();
      String autor = txtAutor.getText();
      int opcaoGenero = comboGenero.getSelectedIndex();
      String isbn = txtIsbn.getText();
      String editora = txtEditora.getText();
      String desc = txtDescricao.getText();

      int ano = Integer.parseInt(txtAno.getText());
      int paginas = Integer.parseInt(txtPaginas.getText());

      livroService.cadastrarLivro(nome, autor, opcaoGenero, isbn, editora, ano, paginas, desc);

      JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
      limparCampos();

    } catch (NumberFormatException ex) {
      JOptionPane.showMessageDialog(this, "Erro: Ano e Páginas devem ser números válidos.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso de Validação", JOptionPane.WARNING_MESSAGE);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void limparCampos() {
    txtNome.setText(""); txtAutor.setText(""); txtIsbn.setText("");
    txtEditora.setText(""); txtAno.setText(""); txtPaginas.setText("");
    txtDescricao.setText(""); comboGenero.setSelectedIndex(0);
  }
}
