package biblioteca.adaptador.entrada.swing;

import biblioteca.aplicacao.LivroService;
import biblioteca.dominio.modelo.Livro;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PainelAcervoLivro extends JPanel {

    private final LivroService livroService;
    private JTable tabelaAcervo;
    private LivroTableModel tableModel;

    public PainelAcervoLivro(LivroService livroService) {
        this.livroService = livroService;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelTopo = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Acervo de Livros");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        
        JButton btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.addActionListener(e -> carregarDados());

        painelTopo.add(titulo, BorderLayout.WEST);
        painelTopo.add(btnAtualizar, BorderLayout.EAST);
        add(painelTopo, BorderLayout.NORTH);

        tabelaAcervo = new JTable();
        JScrollPane scrollPane = new JScrollPane(tabelaAcervo);
        add(scrollPane, BorderLayout.CENTER);

        carregarDados();
    }

    private void carregarDados() {
        List<Livro> livrosCadastrados = livroService.listarLivros();
        tableModel = new LivroTableModel(livrosCadastrados);
        tabelaAcervo.setModel(tableModel);
    }
}
