package biblioteca.adaptador.saida.persistencia;

import biblioteca.aplicacao.EmprestimoRepository;
import biblioteca.dominio.modelo.Emprestimo;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepositoryAdp implements EmprestimoRepository {

  private List<Emprestimo> listaEmprestimos = new ArrayList<>();

  @Override
  public void salvar(Emprestimo emprestimo) {
    listaEmprestimos.add(emprestimo);
    System.out.println("Empréstimo registrado.");
  }

  @Override
  public List<Emprestimo> buscarTodos() {
    return listaEmprestimos;
  }
}
