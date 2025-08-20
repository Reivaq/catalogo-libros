package com.antoniq.catalogo_libros.Service;

import com.antoniq.catalogo_libros.model.Libro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {

    private int count;

    @JsonAlias("results")
    private List<Libro> libros;

    // Getters y Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "GutendexResponse{" +
                "count=" + count +
                ", libros=" + libros +
                '}';
    }
}
