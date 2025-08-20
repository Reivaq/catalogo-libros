package com.antoniq.catalogo_libros.repository;

import com.antoniq.catalogo_libros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores vivos en cierto año
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoIsNullOrFallecimientoGreaterThanEqual(
            Integer nacimiento, Integer fallecimiento
    );

    Optional<Autor> findByNombre(String nombreAutor);

    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(int ano, int ano1);
}
