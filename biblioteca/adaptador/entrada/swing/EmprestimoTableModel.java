package biblioteca.adaptador.entrada.swing;

import biblioteca.dominio.modelo.Emprestimo;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmprestimoTableModel extends AbstractTableModel {

    private final List<Emprestimo> emprestimos;
    private final String[] colunas = {"Aluno", "Livro", "Data", "Status"};

    public EmprestimoTableModel(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    @Override
    public int getRowCount() {
        return emprestimos.size();
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
        Emprestimo emp = emprestimos.get(rowIndex);
        String status = emp.getFoiDevolvido() ? "Devolvido" : "Pendente";
        
        return switch (columnIndex) {
            case 0 -> emp.getNomeLeitor();
            case 1 -> emp.getLivroEmprestado().getTitulo();
            case 2 -> emp.getDataEmprestimo(); 
            case 3 -> status;
            default -> null;
        };
    }
}
