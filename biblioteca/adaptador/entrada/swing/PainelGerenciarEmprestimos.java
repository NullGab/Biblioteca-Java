package biblioteca.adaptador.entrada.swing;

import biblioteca.aplicacao.EmprestimoService;
import biblioteca.dominio.modelo.Emprestimo;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelGerenciarEmprestimos extends JPanel {

  private final EmprestimoService emprestimoService;
  private JTable tabelaHistorico;
  private EmprestimoTableModel tableModel;

  private JTextField txtIsbnEmprestimo, txtAlunoEmprestimo;

  private JTextField txtAlunoDevolucao;

  public PainelGerenciarEmprestimos(EmprestimoService emprestimoService) {
    this.emprestimoService = emprestimoService;

    setLayout(new BorderLayout(10, 10));
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JPanel painelAcoes = new JPanel(new GridLayout(2, 1, 10, 20));

    JPanel painelNovoEmprestimo = new JPanel(new FlowLayout(FlowLayout.LEFT));
    painelNovoEmprestimo.setBorder(BorderFactory.createTitledBorder("Realizar Novo Empréstimo"));

    txtIsbnEmprestimo = new JTextField(12);
    txtAlunoEmprestimo = new JTextField(15);
    JButton btnEmprestar = new JButton("Emprestar");

    painelNovoEmprestimo.add(new JLabel("ISBN do Livro:"));
    painelNovoEmprestimo.add(txtIsbnEmprestimo);
    painelNovoEmprestimo.add(new JLabel("Nome do Aluno:"));
    painelNovoEmprestimo.add(txtAlunoEmprestimo);
    painelNovoEmprestimo.add(btnEmprestar);

    JPanel painelDevolucao = new JPanel(new FlowLayout(FlowLayout.LEFT));
    painelDevolucao.setBorder(BorderFactory.createTitledBorder("Registrar Devolução"));

    txtAlunoDevolucao = new JTextField(15);
    JButton btnDevolver = new JButton("Devolver Livros do Aluno");

    painelDevolucao.add(new JLabel("Nome do Aluno:"));
    painelDevolucao.add(txtAlunoDevolucao);
    painelDevolucao.add(btnDevolver);

    painelAcoes.add(painelNovoEmprestimo);
    painelAcoes.add(painelDevolucao);

    add(painelAcoes, BorderLayout.NORTH);

    JPanel painelTabela = new JPanel(new BorderLayout());
    painelTabela.setBorder(BorderFactory.createTitledBorder("Histórico de Empréstimos"));

    tabelaHistorico = new JTable();
    JScrollPane scrollPane = new JScrollPane(tabelaHistorico);
    painelTabela.add(scrollPane, BorderLayout.CENTER);

    add(painelTabela, BorderLayout.CENTER);

    btnEmprestar.addActionListener(e -> tentarEmprestar());
    btnDevolver.addActionListener(e -> tentarDevolver());

    atualizarTabela();
  }

  private void tentarEmprestar() {
    try {
      String isbn = txtIsbnEmprestimo.getText().trim();
      String aluno = txtAlunoEmprestimo.getText().trim();

      if (isbn.isEmpty() || aluno.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha o ISBN e o Nome do Aluno.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
      }

      emprestimoService.realizarEmprestimo(isbn, aluno);
      JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

      txtIsbnEmprestimo.setText("");
      txtAlunoEmprestimo.setText("");
      atualizarTabela();

    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro no Empréstimo", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void tentarDevolver() {
    try {
      String aluno = txtAlunoDevolucao.getText().trim();

      if (aluno.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Preencha o Nome do Aluno.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
      }

      emprestimoService.devolverLivro(aluno);
      JOptionPane.showMessageDialog(this, "Devolução registrada para " + aluno + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

      txtAlunoDevolucao.setText("");
      atualizarTabela();

    } catch (IllegalArgumentException ex) {
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro na Devolução", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void atualizarTabela() {
    List<Emprestimo> historico = emprestimoService.obterHistorico();
    tableModel = new EmprestimoTableModel(historico);
    tabelaHistorico.setModel(tableModel);
  }
}
