package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.dominio.modelo.ItemPersistencia;
import biblioteca.dominio.modelo.TipoEntidade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class MapaPersistencia<T extends ItemPersistencia> {
  private final TipoEntidade tipoEntidade;
  private final Armazenamento<T> armazenamento;
  protected final List<T> colecaoObjetos;
  protected int indice;
  protected int controleCodigo;

  protected MapaPersistencia(TipoEntidade tipoEntidade, Armazenamento<T> armazenamento) {
    this.tipoEntidade = tipoEntidade;
    this.armazenamento = armazenamento;
    this.colecaoObjetos = new ArrayList<>();
    this.indice = -1;
    this.controleCodigo = 1;
    recuperarDoArmazenamento();
    recalcularControleCodigo();
  }

  public TipoEntidade getTipoEntidade() {
    return tipoEntidade;
  }

  public boolean inserir(T ip) {
    if (haItemPersistido(ip.getOID())) {
      return false;
    }
    inserirItemNoArmazenamento(ip);
    recalcularControleCodigo();
    return true;
  }

  public boolean alterar(T ip) {
    if (!haItemPersistido(ip.getOID())) {
      return false;
    }
    alterarItemNoArmazenamento(ip);
    recalcularControleCodigo();
    return true;
  }

  public boolean excluir(String oid) {
    if (!haItemPersistido(oid)) {
      return false;
    }
    excluirItemNoArmazenamento();
    recalcularControleCodigo();
    return true;
  }

  public T getOID(String oid) {
    if (!haItemPersistido(oid)) {
      return null;
    }
    return colecaoObjetos.get(indice);
  }

  public List<T> obterTodos() {
    return Collections.unmodifiableList(colecaoOrdenadaPorOid());
  }

  public String gerarCodigo() {
    String oid = tipoEntidade.getPrefixoOid() + controleCodigo;
    controleCodigo++;
    return oid;
  }

  protected boolean haItemPersistido(String oid) {
    indice = -1;
    for (int i = 0; i < colecaoObjetos.size(); i++) {
      if (colecaoObjetos.get(i).getOID().equalsIgnoreCase(oid)) {
        indice = i;
        return true;
      }
    }
    return false;
  }

  protected void inserirItemNoArmazenamento(T ip) {
    colecaoObjetos.add(ip);
    ordenarPorOid();
    gravarNoArmazenamento();
  }

  protected void alterarItemNoArmazenamento(T ip) {
    colecaoObjetos.set(indice, ip);
    ordenarPorOid();
    gravarNoArmazenamento();
  }

  protected void excluirItemNoArmazenamento() {
    colecaoObjetos.remove(indice);
    gravarNoArmazenamento();
  }

  protected void gravarNoArmazenamento() {
    armazenamento.armazenar(colecaoObjetos);
  }

  private void recuperarDoArmazenamento() {
    colecaoObjetos.clear();
    colecaoObjetos.addAll(armazenamento.recuperar());
    ordenarPorOid();
  }

  private List<T> colecaoOrdenadaPorOid() {
    ordenarPorOid();
    return new ArrayList<>(colecaoObjetos);
  }

  protected void ordenarPorOid() {
    Collections.sort(colecaoObjetos, new Comparator<T>() {
      @Override
      public int compare(T o1, T o2) {
        return Integer.valueOf(numeroDoOid(o1.getOID())).compareTo(numeroDoOid(o2.getOID()));
      }
    });
  }

  protected void recalcularControleCodigo() {
    int maior = 0;
    for (T objeto : colecaoObjetos) {
      maior = Math.max(maior, numeroDoOid(objeto.getOID()));
    }
    controleCodigo = maior + 1;
  }

  protected int numeroDoOid(String oid) {
    if (oid == null) {
      return 0;
    }
    String apenasNumeros = oid.replaceAll("\\D+", "");
    if (apenasNumeros.isEmpty()) {
      return 0;
    }
    return Integer.parseInt(apenasNumeros);
  }
}
