package biblioteca.aplicacao;

import biblioteca.dominio.modelo.Emprestimo;
import java.util.List;

public interface EmprestimoRepository {
  void salvar(Emprestimo emprestimo);
  List<Emprestimo> buscarTodos();
}
