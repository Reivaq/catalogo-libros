package com.antoniq.catalogo_libros.Service;

import com.antoniq.catalogo_libros.model.Autor;
import com.antoniq.catalogo_libros.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorEstadisticasService {

    private final AutorRepository autorRepository;

    public AutorEstadisticasService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    // Lista de autores vivos en un año específico
    public List<Autor> autoresVivosEnAno(int ano) {
        return autorRepository.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(ano, ano);
    }

    // Contar autores totales
    public long contarAutores() {
        return autorRepository.count();
    }
}
