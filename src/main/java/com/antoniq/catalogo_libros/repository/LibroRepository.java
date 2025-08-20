package com.antoniq.catalogo_libros.repository;

import com.antoniq.catalogo_libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}