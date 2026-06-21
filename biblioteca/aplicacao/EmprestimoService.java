package biblioteca.aplicacao;

import biblioteca.dominio.modelo.Emprestimo;
import biblioteca.dominio.modelo.Livro;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmprestimoService {

  private final EmprestimoRepository emprestimoRepository;
  private final LivroRepository livroRepository;

  public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository) {
    this.emprestimoRepository = emprestimoRepository;
    this.livroRepository = livroRepository;
  }

  public void realizarEmprestimo(String isbnLivro, String nomeAluno) {
    Livro livroEscolhido = null;
    for (Livro l : livroRepository.buscarTodos()) {
      if (l.getIsbn().equals(isbnLivro)) {
        livroEscolhido = l;
        break;
      }
    }

    if (livroEscolhido == null) {
      throw new IllegalArgumentException("Livro com ISBN " + isbnLivro + " não encontrado no acervo.");
    }

    LocalDate dataHoje = LocalDate.now();
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dataEmprestimoReal = dataHoje.format(formatador);

    String oid = emprestimoRepository.gerarProximoCodigo();
    Emprestimo novoEmprestimo = new Emprestimo(oid, nomeAluno, dataEmprestimoReal, livroEscolhido);
    emprestimoRepository.salvar(novoEmprestimo);
  }

  public void devolverLivro(String nomeAluno) {
    boolean encontrou = false;

    for (Emprestimo e : emprestimoRepository.buscarTodos()) {
      if (e.getNomeLeitor().equalsIgnoreCase(nomeAluno) && !e.getFoiDevolvido()) {
        e.registrarDevolucao();
        emprestimoRepository.atualizar(e);
        encontrou = true;
      }
    }

    if (!encontrou) {
      throw new IllegalArgumentException("Nenhum empréstimo pendente encontrado para o aluno: " + nomeAluno);
    }
  }

  public List<Emprestimo> obterHistorico() {
    return emprestimoRepository.buscarTodos();
  }
}
