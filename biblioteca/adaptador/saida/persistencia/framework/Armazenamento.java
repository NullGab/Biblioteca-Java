package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.dominio.modelo.ItemPersistencia;
import java.util.List;

public interface Armazenamento<T extends ItemPersistencia> {
  List<T> recuperar();
  void armazenar(List<T> objetos);
}
