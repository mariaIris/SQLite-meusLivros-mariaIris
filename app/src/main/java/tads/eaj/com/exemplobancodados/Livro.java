package tads.eaj.com.exemplobancodados;

/**
 * Created by Taniro on 09/09/2018.
 */
public class Livro {

    public Livro(String titulo, String autor, String s, float rating) {

    }

    public Livro(String titulo, String autor, int ano, int nota) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.nota = nota;
    }

    private String titulo;
    private String autor;
    private int ano;
    private int nota;
    private long id;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public long getId() {
        return 0;
    }

    public void setId(int anInt) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                ", nota=" + nota +
                '}';
    }



}

