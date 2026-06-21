package biblioteca.adaptador.saida.persistencia;

import biblioteca.adaptador.saida.persistencia.framework.Armazenamento;
import biblioteca.adaptador.saida.persistencia.framework.MapaPersistencia;
import biblioteca.dominio.modelo.Livro;
import biblioteca.dominio.modelo.TipoEntidade;

public class MPLivro extends MapaPersistencia<Livro> {
  public MPLivro(Armazenamento<Livro> armazenamento) {
    super(TipoEntidade.LIVRO, armazenamento);
  }

  public Livro buscarPorIsbn(String isbn) {
    for (Livro livro : colecaoObjetos) {
      if (livro.getIsbn().equals(isbn)) {
        return livro;
      }
    }
    return null;
  }
}
