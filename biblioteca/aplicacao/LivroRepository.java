package biblioteca.aplicacao;

import biblioteca.dominio.modelo.Livro;
import java.util.List;

public interface LivroRepository {
    void salvar(Livro livro);
    List<Livro> buscarTodos();
    void excluirPorIsbn(String isbn);
    String gerarProximoCodigo();
  }
