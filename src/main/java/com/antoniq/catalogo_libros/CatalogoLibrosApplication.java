package com.antoniq.catalogo_libros;

import com.antoniq.catalogo_libros.Service.GutendexService;
import com.antoniq.catalogo_libros.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {

	@Autowired
	private GutendexService gutendexService;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) {
		mostrarMenu();
	}

	private void mostrarMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean salir = false;

		while (!salir) {
			System.out.println("\n=== Catálogo de Libros ===");
			System.out.println("1. Buscar libro por palabra clave");
			System.out.println("2. Salir");
			System.out.print("Seleccione una opción: ");

			String opcion = scanner.nextLine();

			switch (opcion) {
				case "1":
					System.out.print("Ingrese la palabra clave de búsqueda: ");
					String consulta = scanner.nextLine();
					buscarYMostrarLibros(consulta);
					break;
				case "2":
					System.out.println("Saliendo del programa...");
					salir = true;
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
			}
		}

		scanner.close();
	}

	private void buscarYMostrarLibros(String consulta) {
		try {
			List<Libro> libros = gutendexService.buscarLibros(consulta);
			if (libros.isEmpty()) {
				System.out.println("No se encontraron libros para la búsqueda: " + consulta);
			} else {
				System.out.println("\nLibros encontrados:");
				for (Libro libro : libros) {
					System.out.println(libro);
				}
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error al buscar los libros: " + e.getMessage());
		}
	}
}
