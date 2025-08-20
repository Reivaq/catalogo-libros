package com.antoniq.catalogo_libros.Service;

import com.antoniq.catalogo_libros.model.Autor;
import com.antoniq.catalogo_libros.model.Libro;
import com.antoniq.catalogo_libros.repository.AutorRepository;
import com.antoniq.catalogo_libros.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class GutendexService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public GutendexService(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarYGuardarLibro(String tituloBuscado) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder
                .fromHttpUrl("https://gutendex.com/books/")
                .queryParam("search", tituloBuscado)
                .toUriString();


        Map<String, Object> respuesta = restTemplate.getForObject(url, Map.class);

        if (respuesta != null && respuesta.containsKey("results")) {
            List<Map<String, Object>> resultados = (List<Map<String, Object>>) respuesta.get("results");

            if (!resultados.isEmpty()) {
                Map<String, Object> libroMap = resultados.get(0);

                // Título
                String titulo = libroMap.get("title") != null ? libroMap.get("title").toString() : "Desconocido";

                // Idiomas (la API devuelve una lista de strings)
                List<String> idiomas = libroMap.get("languages") != null
                        ? (List<String>) libroMap.get("languages")
                        : Collections.singletonList("desconocido");
                String idioma = idiomas.get(0);

                // Autor (la API devuelve lista de objetos con name, birth_year, death_year)
                List<Map<String, Object>> autoresLista = libroMap.get("authors") != null
                        ? (List<Map<String, Object>>) libroMap.get("authors")
                        : new ArrayList<>();

                Autor autor = null;
                if (!autoresLista.isEmpty()) {
                    Map<String, Object> autorMap = autoresLista.get(0);
                    String nombreAutor = autorMap.get("name") != null ? autorMap.get("name").toString() : "Desconocido";
                    Integer nacimiento = autorMap.get("birth_year") != null ? (Integer) autorMap.get("birth_year") : null;
                    Integer fallecimiento = autorMap.get("death_year") != null ? (Integer) autorMap.get("death_year") : null;

                    // Buscar si ya existe el autor
                    Optional<Autor> autorExistente = autorRepository.findByNombre(nombreAutor);
                    autor = autorExistente.orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(nombreAutor);
                        nuevoAutor.setBirthYear(nacimiento);
                        nuevoAutor.setDeathYear(fallecimiento);
                        return autorRepository.save(nuevoAutor);
                    });
                }

                // Guardar libro
                Libro libro = new Libro();
                libro.setTitulo(titulo);
                libro.setIdioma(idioma);
                libro.setAutor(autor);

                libroRepository.save(libro);

                System.out.println("📚 Libro guardado: " + titulo + " (" + idioma + ")");
                if (autor != null) {
                    System.out.println("👤 Autor: " + autor.getNombre());
                }
            } else {
                System.out.println("⚠️ No se encontró el libro con título: " + tituloBuscado);
            }
        } else {
            System.out.println("⚠️ Error al consultar la API de Gutendex.");
        }
    }

    public List<Libro> buscarLibros(String consulta) {
        return List.of();
    }
}
