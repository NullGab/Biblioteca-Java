package biblioteca.dominio.modelo;

public class Livro extends ItemAcervo {
  private String nomeAutor;
  private String isbn;
  private int numPag;
  private Genero generoLivro;

  public Livro(String nomeLivro, String nomeAutor, String isbn, String editora, int anoPublicacao, int numPag, Genero generoLivro){
    super(nomeLivro, editora, anoPublicacao); 
    this.nomeAutor = nomeAutor;
    this.isbn = isbn;
    this.numPag = numPag;
    this.generoLivro = generoLivro;
  }

  @Override
  public String toString() {
    return "NOME: " + getTitulo() + " | AUTOR: " + nomeAutor + 
      " | GÊNERO: " + generoLivro.getGenero() + " | ISBN: " + isbn + 
      " | EDITORA: " + getEditora() + " | ANO: " + getAnoPublicacao();
  }

  public String getNomeAutor() { return nomeAutor; }
  public String getIsbn() { return isbn; }
  public int getNumPag() { return numPag; }
  public Genero getGenero() { return generoLivro; }
}
