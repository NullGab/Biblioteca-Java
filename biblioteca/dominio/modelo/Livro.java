package biblioteca.dominio.modelo;

public class Livro extends ItemAcervo {
    private String nomeAutor;
    private String isbn;
    private int numPag;
    private Genero generoLivro;

    public Livro(String oid, String nomeLivro, String nomeAutor, String isbn, String editora, int anoPublicacao, int numPag, Genero generoLivro){
        super(oid, nomeLivro, editora, anoPublicacao); 
        this.nomeAutor = nomeAutor;
        this.isbn = isbn;
        this.numPag = numPag;
        this.generoLivro = generoLivro;
    }

    @Override
    public TipoEntidade getTipoEntidade() {
        return TipoEntidade.LIVRO;
    }

    public String getNomeAutor() { return nomeAutor; }
    public String getIsbn() { return isbn; }
    public int getNumPag() { return numPag; }
    public Genero getGenero() { return generoLivro; }
}
