package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.dominio.modelo.ItemPersistencia;
import biblioteca.dominio.modelo.TipoEntidade;

import java.util.List;

public class UnidadePersistencia {
  private final RegistroMapeadores registroMapeadores;

  public UnidadePersistencia(RegistroMapeadores registroMapeadores) {
    this.registroMapeadores = registroMapeadores;
  }

  public <T extends ItemPersistencia> boolean inserir(T item) {
    return this.<T>mapa(item.getTipoEntidade()).inserir(item);
  }

  public <T extends ItemPersistencia> boolean alterar(T item) {
    return this.<T>mapa(item.getTipoEntidade()).alterar(item);
  }

  public boolean excluir(String oid, TipoEntidade tipoEntidade) {
    return mapa(tipoEntidade).excluir(oid);
  }

  public <T extends ItemPersistencia> T obterItemPersistencia(String oid, TipoEntidade tipoEntidade) {
    return this.<T>mapa(tipoEntidade).getOID(oid);
  }

  public <T extends ItemPersistencia> List<T> obterTodos(TipoEntidade tipoEntidade) {
    return this.<T>mapa(tipoEntidade).obterTodos();
  }

  public String gerarCodigo(TipoEntidade tipoEntidade) {
    return mapa(tipoEntidade).gerarCodigo();
  }

  private <T extends ItemPersistencia> MapaPersistencia<T> mapa(TipoEntidade tipoEntidade) {
    return registroMapeadores.obter(tipoEntidade);
  }
}
