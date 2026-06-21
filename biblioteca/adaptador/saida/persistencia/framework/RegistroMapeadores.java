package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.dominio.modelo.ItemPersistencia;
import biblioteca.dominio.modelo.TipoEntidade;

import java.util.HashMap;
import java.util.Map;

public class RegistroMapeadores {
  private final Map<TipoEntidade, MapaPersistencia<? extends ItemPersistencia>> mapeadores;

  public RegistroMapeadores() {
    this.mapeadores = new HashMap<>();
  }

  public <T extends ItemPersistencia> void registrar(MapaPersistencia<T> mapa) {
    mapeadores.put(mapa.getTipoEntidade(), mapa);
  }

  @SuppressWarnings("unchecked")
  public <T extends ItemPersistencia> MapaPersistencia<T> obter(TipoEntidade tipoEntidade) {
    MapaPersistencia<? extends ItemPersistencia> mapa = mapeadores.get(tipoEntidade);
    if (mapa == null) {
      throw new IllegalStateException("Não há mapeador registrado para: " + tipoEntidade);
    }
    return (MapaPersistencia<T>) mapa;
  }
}
