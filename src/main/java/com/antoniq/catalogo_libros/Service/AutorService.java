package com.antoniq.catalogo_libros.service;

import com.antoniq.catalogo_libros.model.Autor;
import com.antoniq.catalogo_libros.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Guardar autor
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Buscar autor por nombre
    public Optional<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    // Listar todos los autores
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Buscar autores vivos en un año
    public List<Autor> autoresVivosEnAno(int ano) {
        return autorRepository.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(ano, ano);
    }
}
