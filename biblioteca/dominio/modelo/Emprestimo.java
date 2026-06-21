package biblioteca.dominio.modelo;

public class Emprestimo extends ItemPersistencia {
    private String nomeLeitor;
    private String dataEmprestimo;
    private Livro livroEmprestado; 
    private boolean foiDevolvido;  

    public Emprestimo(String oid, String nomeLeitor, String dataEmprestimo, Livro livroEmprestado) {
        super(oid);
        this.nomeLeitor = nomeLeitor;
        this.dataEmprestimo = dataEmprestimo;
        this.livroEmprestado = livroEmprestado;
        this.foiDevolvido = false; 
    }

    @Override
    public TipoEntidade getTipoEntidade() {
        return TipoEntidade.EMPRESTIMO;
    }

    public void registrarDevolucao() { this.foiDevolvido = true; }
    public boolean getFoiDevolvido() { return foiDevolvido; }
    public Livro getLivroEmprestado() { return livroEmprestado; }
    public String getNomeLeitor() { return nomeLeitor; }
    public String getDataEmprestimo() { return dataEmprestimo; }
}
