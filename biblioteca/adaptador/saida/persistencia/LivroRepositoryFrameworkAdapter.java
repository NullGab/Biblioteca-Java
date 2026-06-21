package biblioteca.adaptador.saida.persistencia;

import biblioteca.adaptador.saida.persistencia.framework.UnidadePersistencia;
import biblioteca.aplicacao.LivroRepository;
import biblioteca.dominio.modelo.Livro;
import biblioteca.dominio.modelo.TipoEntidade;
import java.util.List;

public class LivroRepositoryFrameworkAdapter implements LivroRepository {
  private final UnidadePersistencia unidadePersistencia;
  private final MPLivro mpLivro;

  public LivroRepositoryFrameworkAdapter(UnidadePersistencia unidadePersistencia, MPLivro mpLivro) {
    this.unidadePersistencia = unidadePersistencia;
    this.mpLivro = mpLivro;
  }

  @Override
  public void salvar(Livro livro) {
    if (!unidadePersistencia.inserir(livro)) {
      throw new IllegalStateException("Já existe livro com o código: " + livro.getOID());
    }
  }

  @Override
  public List<Livro> buscarTodos() {
    return unidadePersistencia.obterTodos(TipoEntidade.LIVRO);
  }

  @Override
  public void excluirPorIsbn(String isbn) {
    Livro livro = mpLivro.buscarPorIsbn(isbn);
    if (livro != null) {
      unidadePersistencia.excluir(livro.getOID(), TipoEntidade.LIVRO);
    } else {
      throw new IllegalArgumentException("Livro não encontrado para exclusão.");
    }
  }

  @Override
  public String gerarProximoCodigo() {
    return unidadePersistencia.gerarCodigo(TipoEntidade.LIVRO);
  }
}
