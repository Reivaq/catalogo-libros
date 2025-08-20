package com.antoniq.catalogo_libros.controller;

import com.antoniq.catalogo_libros.Service.GutendexService;
import com.antoniq.catalogo_libros.Service.LibroEstadisticasService;
import com.antoniq.catalogo_libros.model.Libro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final GutendexService gutendexService;
    private final LibroEstadisticasService estadisticasService;

    public LibroController(GutendexService gutendexService, LibroEstadisticasService estadisticasService) {
        this.gutendexService = gutendexService;
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/busqueda")
    public List<Libro> buscarLibros(@RequestParam String consulta) throws Exception {
        return gutendexService.buscarLibros(consulta);
    }

    @GetMapping("/estadisticas/idiomas")
    public Map<String, Long> librosPorIdioma() {
        return estadisticasService.contarLibrosPorIdioma();
    }
}
