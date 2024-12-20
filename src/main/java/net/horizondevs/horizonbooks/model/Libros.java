package net.horizondevs.horizonbooks.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;

    private List<String> idiomas;

    private Double numeroDeDescargas;

    @ManyToOne
    @JoinColumn(name = "autorId", nullable = false)
    private Autor autor;

    // Constructor sin argumentos (necesario para Hibernate)
    public Libros() {}

    public Libros(DatosLibros datosLibros, Autor autor){
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.numeroDeDescargas = OptionalDouble.of(Double.valueOf(datosLibros.numeroDeDescargas())).orElse(0);
        this.autor = autor;
        autor.getLibros().add(this);
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return  "===============  LIBRO ===============" + "\n" +
                "titulo  = " + titulo + "\n" +
                "Autor   = " + this.autor.getNombreAutor() + "\n" +
                "Idiomas =" + idiomas + "\n" +
                "Total de Descargas = " + numeroDeDescargas + "\n" +
                "======================================";
    }
}
