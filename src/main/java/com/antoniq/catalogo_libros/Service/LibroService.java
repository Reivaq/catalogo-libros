package com.antoniq.catalogo_libros.Service;

import com.antoniq.catalogo_libros.model.Autor;
import com.antoniq.catalogo_libros.model.Libro;
import com.antoniq.catalogo_libros.repository.AutorRepository;
import com.antoniq.catalogo_libros.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public LibroService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Guardar libro junto con su autor
    public Libro guardarLibro(String titulo, String idioma, Integer descargas, String nombreAutor, Integer nacimiento, Integer fallecimiento) {
        Autor autor = autorRepository.findByNombre(nombreAutor)
                .orElseGet(() -> autorRepository.save(new Autor(nombreAutor, nacimiento, fallecimiento)));

        Libro libro = new Libro(titulo, idioma, descargas, autor);
        return libroRepository.save(libro);
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Buscar libro por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Listar libros por idioma
    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdiomaIgnoreCase(idioma);
    }
}
