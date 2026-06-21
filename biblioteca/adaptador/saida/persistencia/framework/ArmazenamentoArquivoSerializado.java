package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.dominio.modelo.ItemPersistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArmazenamentoArquivoSerializado<T extends ItemPersistencia> implements Armazenamento<T> {
  private final File arquivo;
  private final Class<T> tipoEsperado;

  public ArmazenamentoArquivoSerializado(String caminhoArquivo, Class<T> tipoEsperado) {
    this.arquivo = new File(caminhoArquivo);
    this.tipoEsperado = tipoEsperado;
  }

  @Override
  public List<T> recuperar() {
    List<T> objetos = new ArrayList<>();
    if (!arquivo.exists()) {
      return objetos;
    }

    ObjectInputStream in = null;
    try {
      in = new ObjectInputStream(new FileInputStream(arquivo));
      while (true) {
        Object objeto = in.readObject();
        if (tipoEsperado.isInstance(objeto)) {
          objetos.add(tipoEsperado.cast(objeto));
        }
      }
    } catch (EOFException fimDoArquivo) {
      return objetos;
    } catch (IOException e) {
      throw new IllegalStateException("Erro ao recuperar dados de " + arquivo.getAbsolutePath(), e);
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Classe persistida não encontrada ao recuperar dados.", e);
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException ignored) {}
      }
    }
  }

  @Override
  public void armazenar(List<T> objetos) {
    File diretorio = arquivo.getParentFile();
    if (diretorio != null && !diretorio.exists() && !diretorio.mkdirs()) {
      throw new IllegalStateException("Não foi possível criar o diretório: " + diretorio.getAbsolutePath());
    }

    ObjectOutputStream out = null;
    try {
      out = new ObjectOutputStream(new FileOutputStream(arquivo));
      for (T objeto : objetos) {
        out.writeObject(objeto);
      }
    } catch (IOException e) {
      throw new IllegalStateException("Erro ao armazenar dados em " + arquivo.getAbsolutePath(), e);
    } finally {
      if (out != null) {
        try {
          out.close();
        } catch (IOException ignored) {}
      }
    }
  }
}
