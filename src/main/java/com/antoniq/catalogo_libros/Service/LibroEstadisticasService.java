package com.antoniq.catalogo_libros.Service;

import com.antoniq.catalogo_libros.model.Libro;
import com.antoniq.catalogo_libros.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LibroEstadisticasService {

    private final LibroRepository libroRepository;

    public LibroEstadisticasService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Método para contar libros por idioma usando Streams
    public Map<String, Long> contarLibrosPorIdioma() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .collect(Collectors.groupingBy(Libro::getIdioma, Collectors.counting()));
    }
}
