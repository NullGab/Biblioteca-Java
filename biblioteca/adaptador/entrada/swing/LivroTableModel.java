package biblioteca.adaptador.entrada.swing;

import biblioteca.dominio.modelo.Livro;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LivroTableModel extends AbstractTableModel {

  private final List<Livro> livros;
  private final String[] colunas = {"Título", "Autor", "Gênero", "ISBN", "Ano"};

  public LivroTableModel(List<Livro> livros) {
    this.livros = livros;
  }

  @Override
  public int getRowCount() {
    return livros.size();
  }

  @Override
  public int getColumnCount() {
    return colunas.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return colunas[columnIndex];
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    Livro livro = livros.get(rowIndex);

    return switch (columnIndex) {
      case 0 -> livro.getTitulo();
      case 1 -> livro.getNomeAutor();
      case 2 -> livro.getGenero().getGenero();
      case 3 -> livro.getIsbn();
      case 4 -> livro.getAnoPublicacao();
      default -> null;
    };
  }
}
