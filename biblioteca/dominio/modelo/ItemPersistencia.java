package biblioteca.dominio.modelo;

import java.io.Serializable;

public abstract class ItemPersistencia implements Serializable {
  private String oid;

  public ItemPersistencia(String oid) {
    this.oid = oid;
  }

  public String getOID() {
    return oid;
  }

  public abstract TipoEntidade getTipoEntidade();
}
