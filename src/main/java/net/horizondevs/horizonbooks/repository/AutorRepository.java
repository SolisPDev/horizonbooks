package net.horizondevs.horizonbooks.repository;

import net.horizondevs.horizonbooks.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor,Long> {
    Autor findByNombreAutor(String nombreAutor);
}



