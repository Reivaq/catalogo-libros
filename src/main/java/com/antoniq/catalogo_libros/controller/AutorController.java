package com.antoniq.catalogo_libros.controller;

import com.antoniq.catalogo_libros.Service.AutorEstadisticasService;
import com.antoniq.catalogo_libros.model.Autor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorEstadisticasService estadisticasService;

    public AutorController(AutorEstadisticasService estadisticasService) {
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/vivos")
    public List<Autor> autoresVivos(@RequestParam int ano) {
        return estadisticasService.autoresVivosEnAno(ano);
    }
}
