package net.horizondevs.horizonbooks.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAutor;
    private int fechaDeNacimiento;
    private int fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libros> libros = new ArrayList<>();

    // Constructor sin argumentos (necesario para Hibernate)
    public Autor() {}

    public Autor(DatosAutor datosAutor) {
        this.nombreAutor = datosAutor.nombreAutor();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento() != null ? Integer.parseInt(datosAutor.fechaDeNacimiento()) : 0;
        this.fechaDeFallecimiento = datosAutor.fechaDeFallecimiento() != null ? Integer.parseInt(datosAutor.fechaDeFallecimiento()) : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public int getFechaDeNaciminto() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNaciminto(int fechaDeNaciminto) {
        this.fechaDeNacimiento = fechaDeNaciminto;
    }

    public int getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(int fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libros> getLibros() {
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String cadenaFechaNacimiento = "";
        String cadenaFechaFallecimiento = "";

        if(fechaDeNacimiento != 0) {
            cadenaFechaNacimiento = String.valueOf(fechaDeNacimiento);
        } else {
            cadenaFechaNacimiento = "Fecha no disponible";
        }

        if(fechaDeFallecimiento != 0) {
            cadenaFechaFallecimiento = String.valueOf(fechaDeFallecimiento);
        } else {
            cadenaFechaFallecimiento = "Aún vive";
        }

        return "=============== Autor ===============\n" +
                "Nombre :" + nombreAutor + "\n" +
                "Año de Naciminto :" + cadenaFechaNacimiento + "\n" +
                "Año de Fallecimiento : " + cadenaFechaFallecimiento + "\n" +
                "====================================";
    }
}
