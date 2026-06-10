package biblioteca.adaptador.saida.persistencia;

import biblioteca.aplicacao.LivroRepository;
import biblioteca.dominio.modelo.Livro;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LivroRepositoryAdp implements LivroRepository {
  private List<Livro> acervo = new ArrayList<>();

  @Override
  public void salvar(Livro livro) {
    acervo.add(livro);
    System.out.println("LOG (Persistência): Livro salvo em memória.");
  }

  @Override
  public List<Livro> buscarTodos() {
    return new ArrayList<>(acervo);
  }

  @Override
  public void excluirPorIsbn(String isbn) {
    Iterator<Livro> it = acervo.iterator();
    boolean removido = false;

    while(it.hasNext()){
      Livro atual = it.next();
      if(atual.getIsbn().equals(isbn)){
        it.remove();
        removido = true;
        System.out.println("LOG (Persistência): Livro removido do acervo.");
        break;
      }
    }

    if(!removido) {
      throw new IllegalArgumentException("Livro com ISBN " + isbn + " não encontrado para exclusão.");
    }
  }
}
