package biblioteca.dominio.modelo;

public enum TipoEntidade {
  LIVRO("L"),
  EMPRESTIMO("E");

  private final String prefixoOid;

  TipoEntidade(String prefixoOid) {
    this.prefixoOid = prefixoOid;
  }

  public String getPrefixoOid() {
    return prefixoOid;
  }
}
