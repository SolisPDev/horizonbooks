package net.horizondevs.horizonbooks.principal;

import net.horizondevs.horizonbooks.model.*;
import net.horizondevs.horizonbooks.repository.AutorRepository;
import net.horizondevs.horizonbooks.repository.LibrosRepository;
import net.horizondevs.horizonbooks.services.ConsumoAPI;
import net.horizondevs.horizonbooks.services.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    // Variables para control de teclado
    private Scanner teclado = new Scanner(System.in);

    // DEfinicion de variable de ambiente y variables para consumo de la API
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    // declaracion de repositorios
    private LibrosRepository librosRepository;
    private AutorRepository autorRepository;

    public Principal(LibrosRepository librosRepository, AutorRepository autorRepository) {
        this.librosRepository = librosRepository;
        this.autorRepository = autorRepository;
    }

    public void menuPrincipal(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                       === HorizonBooks ===
                       ======  MENU  ======
                    
                    [ 1 ] Buscar libro por titulo
                    [ 2 ] Consulta libros registrados
                    [ 3 ] Consulta autores registrados
                    [ 4 ] Consulta autores vivos en un determinado año
                    [ 5 ] Consulta libros por idioma
                    
                                  
                    [ 0 ] Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultaLibrosRegistrados();
                    break;
                case 3:
                    consultaAutoresRegistrados();
                    break;
                case 4:
                    consultaAutoresVivos();
                    break;
                case 5:
                    consultaLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción no asignada...");
            }
        }
    }

    // definicion de metodos de las opciones del menu principal

    private void buscarLibro() {
        System.out.println("Ingresa el nombre del libro: ");
        var buscaLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + buscaLibro.replace(" ", "+"));
        var datos = conversor.obtenerDatos(json, Datos.class);

        List<DatosLibros> librosCoincidentes = datos.libros()
                .stream()
                .filter(libro -> libro.titulo().equalsIgnoreCase(buscaLibro))
                .toList();

        for (DatosLibros libro : librosCoincidentes) {
            for (DatosAutor datosAutor : libro.autor()) {
                Optional<Autor> autorOptional = Optional.ofNullable(autorRepository.findByNombreAutor(datosAutor.nombreAutor()));
                Autor autor = autorOptional.orElseGet(() -> {
                    Autor nuevoAutor = new Autor(datosAutor);
                    return autorRepository.save(nuevoAutor);
                });

                if (librosRepository.findByTitulo(libro.titulo()).isEmpty()) {
                    Libros libroToSave = new Libros(libro, autor);
                    librosRepository.save(libroToSave);
                    System.out.println("Libro registrado: " + libro.titulo());
                } else {
                    System.out.println("El libro ya está registrado: " + libro.titulo());
                }
            }
        }
    }

// Metodo para consultar los libros registrados en la base de datos
    private void consultaLibrosRegistrados() {
        List<Libros> librosRegistrados = librosRepository.findAll();

        if (librosRegistrados.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
            return;
        }

        System.out.println("=== Libros Registrados ===");
        librosRegistrados.forEach(libro -> {
            System.out.println(libro.toString());
            System.out.println("--------------------------");
        });
    }

    // Metodo para consultar los autores registrados en la base de datos
    private void consultaAutoresRegistrados() {
        List<Autor> autoresRegistrados = autorRepository.findAll();

        if (autoresRegistrados.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
            return;
        }

        System.out.println("=== Autores Registrados ===");
        autoresRegistrados.forEach(autor -> {
            System.out.println(autor.toString());
            System.out.println("--------------------------");
        });
    }

    // Metodo para consultar autores vivos en un determinado año
    private void consultaAutoresVivos() {
        System.out.println("Ingresa el año a consultar: ");
        int anioConsulta = teclado.nextInt();
        teclado.nextLine(); // Limpiar el buffer

        List<Autor> autoresRegistrados = autorRepository.findAll();

        if (autoresRegistrados.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
            return;
        }

        List<Autor> autoresVivos = autoresRegistrados.stream()
                .filter(autor -> autor.getFechaDeNaciminto() <= anioConsulta &&
                        (autor.getFechaDeFallecimiento() == 0 || autor.getFechaDeFallecimiento() > anioConsulta))
                .toList();

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anioConsulta + ".");
            return;
        }

        System.out.println("=== Autores Vivos en el Año " + anioConsulta + " ===");
        autoresVivos.forEach(autor -> {
            System.out.println(autor.toString());
            System.out.println("--------------------------");
        });
    }

    // Metodo para consultar libros por idioma
    private void consultaLibrosPorIdioma() {
        System.out.println("Ingresa el idioma a consultar (por ejemplo, 'en' para inglés, 'es' para español): ");
        String idiomaConsulta = teclado.nextLine();

        List<Libros> librosRegistrados = librosRepository.findAll();

        if (librosRegistrados.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
            return;
        }

        List<Libros> librosPorIdioma = librosRegistrados.stream()
                .filter(libro -> libro.getIdiomas() != null && libro.getIdiomas().contains(idiomaConsulta))
                .toList();

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + idiomaConsulta + "'.");
            return;
        }

        System.out.println("=== Libros en el idioma '" + idiomaConsulta + "' ===");
        librosPorIdioma.forEach(libro -> {
            System.out.println(libro.toString());
            System.out.println("--------------------------");
        });
    }

}
