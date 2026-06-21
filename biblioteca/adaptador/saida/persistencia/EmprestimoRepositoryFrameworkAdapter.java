package biblioteca.adaptador.saida.persistencia;

import biblioteca.adaptador.saida.persistencia.framework.UnidadePersistencia;
import biblioteca.aplicacao.EmprestimoRepository;
import biblioteca.dominio.modelo.Emprestimo;
import biblioteca.dominio.modelo.TipoEntidade;
import java.util.List;

public class EmprestimoRepositoryFrameworkAdapter implements EmprestimoRepository {
  private final UnidadePersistencia unidadePersistencia;

  public EmprestimoRepositoryFrameworkAdapter(UnidadePersistencia unidadePersistencia) {
    this.unidadePersistencia = unidadePersistencia;
  }

  @Override
  public void salvar(Emprestimo emprestimo) {
    unidadePersistencia.inserir(emprestimo);
  }

  @Override
  public List<Emprestimo> buscarTodos() {
    return unidadePersistencia.obterTodos(TipoEntidade.EMPRESTIMO);
  }

  @Override
  public void atualizar(Emprestimo emprestimo) {
    unidadePersistencia.alterar(emprestimo);
  }

  @Override
  public String gerarProximoCodigo() {
    return unidadePersistencia.gerarCodigo(TipoEntidade.EMPRESTIMO);
  }
}
