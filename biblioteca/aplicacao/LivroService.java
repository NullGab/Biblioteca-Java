package biblioteca.aplicacao;

import biblioteca.dominio.modelo.Genero;
import biblioteca.dominio.modelo.Livro;
import java.time.LocalDate;

public class LivroService {

  private final LivroRepository repository;

  public LivroService(LivroRepository repository) {
    this.repository = repository;
  }

  public void cadastrarLivro(String nome, String autor, int opcaoGenero, String isbn, String editora, int ano, int paginas, String desc) {

    if (opcaoGenero < 1 || opcaoGenero > 5) {
      throw new IllegalArgumentException("Gênero inexistente.");
    }

    String genNome = switch (opcaoGenero) {
      case 1 -> "Romance"; case 2 -> "Suspense"; case 3 -> "Fantasia";
      case 4 -> "Computação"; default -> "História";
    };

    String isbnLimpo = isbn.replaceAll("\\D", "");
    if (isbnLimpo.length() != 13) {
      throw new IllegalArgumentException("ISBN inválido! Deve conter 13 dígitos.");
    }

    if (ano > LocalDate.now().getYear()) {
      throw new IllegalArgumentException("Ano de publicação não pode estar no futuro.");
    }

    Genero genero = new Genero(genNome, desc);
    Livro novoLivro = new Livro(nome, autor, isbnLimpo, editora, ano, paginas, genero);

    repository.salvar(novoLivro);
  }
}
