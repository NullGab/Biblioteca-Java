package biblioteca;

public class Genero {
  private String genero; 
  private String descricao;

  public Genero(String genero, String descricao) {
    this.genero = genero;
    this.descricao = descricao;
  }

  public String getGenero() { return genero; }
  public String getDescricao() { return descricao; }
}
