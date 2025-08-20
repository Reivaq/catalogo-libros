package com.antoniq.catalogo_libros.repository;

import com.antoniq.catalogo_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);

    // Buscar libros por título que contengan cierto texto
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
