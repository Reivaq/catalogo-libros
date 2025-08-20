package com.antoniq.catalogo_libros;

import com.antoniq.catalogo_libros.Service.GutendexService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {
	private final GutendexService gutendexService;

    public CatalogoLibrosApplication(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    public static void main(String[] args) {

		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("🚀 Catálogo de Libros con Gutendex iniciado");
		String respuesta = gutendexService.buscarLibros("Garcia");
		System.out.println("Respuesta JSON de Gutendex:");
		System.out.println(respuesta.substring(0, 500)); // mostramos solo los primeros 500 caracteres
	}
}
