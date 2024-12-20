package net.horizondevs.horizonbooks.repository;

import net.horizondevs.horizonbooks.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros,Long> {
    Optional<Libros> findByTitulo(String titulo);
}
