package biblioteca.adaptador.saida.persistencia.framework;

import biblioteca.adaptador.saida.persistencia.EmprestimoRepositoryFrameworkAdapter;
import biblioteca.adaptador.saida.persistencia.LivroRepositoryFrameworkAdapter;
import biblioteca.adaptador.saida.persistencia.MPEmprestimo;
import biblioteca.adaptador.saida.persistencia.MPLivro;
import biblioteca.aplicacao.EmprestimoRepository;
import biblioteca.aplicacao.LivroRepository;
import biblioteca.dominio.modelo.Emprestimo;
import biblioteca.dominio.modelo.Livro;

public class PersistenciaFactory {

  private LivroRepository livroRepository;
  private EmprestimoRepository emprestimoRepository;

  public void criarArquivoSerializado(String caminhoLivros, String caminhoEmprestimos) {

    Armazenamento<Livro> armLivro = new ArmazenamentoArquivoSerializado<>(caminhoLivros, Livro.class);
    Armazenamento<Emprestimo> armEmprestimo = new ArmazenamentoArquivoSerializado<>(caminhoEmprestimos, Emprestimo.class);

    MPLivro mpLivro = new MPLivro(armLivro);
    MPEmprestimo mpEmprestimo = new MPEmprestimo(armEmprestimo);

    RegistroMapeadores registro = new RegistroMapeadores();
    registro.registrar(mpLivro);
    registro.registrar(mpEmprestimo);

    UnidadePersistencia unidade = new UnidadePersistencia(registro);

    this.livroRepository = new LivroRepositoryFrameworkAdapter(unidade, mpLivro);
    this.emprestimoRepository = new EmprestimoRepositoryFrameworkAdapter(unidade);
  }

  public LivroRepository getLivroRepository() {
    return livroRepository;
  }

  public EmprestimoRepository getEmprestimoRepository() {
    return emprestimoRepository;
  }
}
