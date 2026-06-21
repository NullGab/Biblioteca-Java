package biblioteca.dominio.modelo;

public abstract class ItemAcervo extends ItemPersistencia {
    private String titulo;
    private String editora;
    private int anoPublicacao;

    public ItemAcervo(String oid, String titulo, String editora, int anoPublicacao) {
        super(oid);
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() { return titulo; }
    public String getEditora() { return editora; }
    public int getAnoPublicacao() { return anoPublicacao; }
}
