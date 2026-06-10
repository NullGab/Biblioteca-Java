package biblioteca.dominio.modelo;

public abstract class ItemAcervo {
  private String titulo;
  private String editora;
  private int anoPublicacao;

  public ItemAcervo(String titulo, String editora, int anoPublicacao) {
    this.titulo = titulo;
    this.editora = editora;
    this.anoPublicacao = anoPublicacao;
  }

  public String getTitulo() { return titulo; }
  public String getEditora() { return editora; }
  public int getAnoPublicacao() { return anoPublicacao; }
}
