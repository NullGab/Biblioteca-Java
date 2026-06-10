package biblioteca;

public class Emprestimo {
  private String nomeLeitor;
  private String dataEmprestimo;
  private Livro livroEmprestado; 
  private boolean foiDevolvido;  

  public Emprestimo(String nomeLeitor, String dataEmprestimo, Livro livroEmprestado) {
    this.nomeLeitor = nomeLeitor;
    this.dataEmprestimo = dataEmprestimo;
    this.livroEmprestado = livroEmprestado;
    this.foiDevolvido = false; 
  }

  public void registrarDevolucao() { this.foiDevolvido = true; }
  public boolean getFoiDevolvido() { return foiDevolvido; }
  public Livro getLivroEmprestado() { return livroEmprestado; }
  public String getNomeLeitor() { return nomeLeitor; }
}
