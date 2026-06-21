package biblioteca.adaptador.saida.persistencia;

import biblioteca.adaptador.saida.persistencia.framework.Armazenamento;
import biblioteca.adaptador.saida.persistencia.framework.MapaPersistencia;
import biblioteca.dominio.modelo.Emprestimo;
import biblioteca.dominio.modelo.TipoEntidade;

public class MPEmprestimo extends MapaPersistencia<Emprestimo> {
  public MPEmprestimo(Armazenamento<Emprestimo> armazenamento) {
    super(TipoEntidade.EMPRESTIMO, armazenamento);
  }
}
