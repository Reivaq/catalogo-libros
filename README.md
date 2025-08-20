# Catálogo de Libros

Catálogo de Libros es una aplicación desarrollada en **Java 24** con **Spring Boot 3.5**, que permite buscar, consultar, almacenar y analizar información de libros y autores. El proyecto se integra con la **API Gutendex** y utiliza **PostgreSQL** para persistencia de datos.

---

## Funcionalidades Principales

1. **Búsqueda de libros por título**
    - Permite buscar libros directamente desde la API Gutendex.
    - Recupera información del libro y su autor.
    - Guarda los resultados en la base de datos.

2. **Listado de todos los libros**
    - Muestra todos los libros almacenados en la base de datos.

3. **Listado de autores**
    - Muestra los autores de los libros guardados.
    - Solo se considera el primer autor de cada libro (simplificación).

4. **Autores vivos en un año específico**
    - Permite consultar los autores que estaban vivos en un año determinado.
    - Utiliza consultas derivadas de Spring Data JPA.

5. **Persistencia de datos**
    - Los libros y autores se almacenan en PostgreSQL.
    - Cada libro está relacionado con su autor mediante una relación `ManyToOne`.

6. **Libros por idioma**
    - Permite listar libros filtrados por idioma.
    - También permite calcular estadísticas de libros por idioma.

---

## Tecnologías Utilizadas

- **Java 24**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **PostgreSQL**
- **HikariCP**
- **Maven**
- **API Gutendex** (para obtener información de libros y autores)

---

## Estructura del Proyecto
src/main/java/com/antoniq/catalogo_libros
├── CatalogoLibrosApplication.java        # Clase principal para iniciar Spring Boot
├── controller
│   ├── LibroController.java              # Endpoints para operaciones con libros
│   └── AutorController.java              # Endpoints para operaciones con autores
├── model
│   ├── Libro.java                         # Entidad Libro con atributos y relación a Autor
│   └── Autor.java                         # Entidad Autor con nombre, año de nacimiento y fallecimiento
├── repository
│   ├── LibroRepository.java               # Repositorio JPA de libros
│   └── AutorRepository.java               # Repositorio JPA de autores
├── service
│   ├── GutendexService.java               # Lógica de integración con la API Gutendex
│   ├── AutorService.java                  # Lógica de negocios relacionada con autores
│   └── AutorEstadisticasService.java      # Consultas de autores vivos y estadísticas

---

## Base de Datos

La aplicación utiliza **PostgreSQL** con las siguientes entidades principales:

### Autor
- `id` (Long, clave primaria)
- `nombre` (String)
- `anio_nacimiento` (Integer)
- `anio_fallecimiento` (Integer, puede ser null)

### Libro
- `id` (Long, clave primaria)
- `titulo` (String)
- `idioma` (String)
- `descargas` (Integer)
- `autor_id` (FK a Autor)

---

## Configuración

1. **Base de datos**
    - Configura tu base PostgreSQL y crea la base `catalogo_libros`.
    - Configura `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/catalogo_libros
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


mvn spring-boot:run
## Endpoints de la API

### Libros

- `GET /libros/busqueda?consulta=TituloDelLibro`  
  Busca un libro por título desde la API y lo guarda en la base.

- `GET /libros`  
  Lista todos los libros almacenados.

### Autores

- `GET /autores`  
  Lista todos los autores de los libros guardados.

- `GET /autores/vivos?ano=2025`  
  Lista los autores que estaban vivos en un año específico.

### Estadísticas

- `GET /libros/idioma/{idioma}`  
  Muestra libros filtrados por idioma.

- `GET /libros/estadisticas/idioma`  
  Muestra cantidad de libros por idioma.
## Endpoints de la API

### Libros

- `GET /libros/busqueda?consulta=TituloDelLibro`  
  Busca un libro por título desde la API y lo guarda en la base.

- `GET /libros`  
  Lista todos los libros almacenados.

### Autores

- `GET /autores`  
  Lista todos los autores de los libros guardados.

- `GET /autores/vivos?ano=2025`  
  Lista los autores que estaban vivos en un año específico.

### Estadísticas

- `GET /libros/idioma/{idioma}`  
  Muestra libros filtrados por idioma.

- `GET /libros/estadisticas/idioma`  
  Muestra cantidad de libros por idioma.

## Ejemplo de Uso

- Buscar un libro por título:  
  `GET http://localhost:8081/libros/busqueda?consulta=Don Quijote`

- Listar autores vivos en 1900:  
  `GET http://localhost:8081/autores/vivos?ano=1900`

- Obtener cantidad de libros en español:  
  `GET http://localhost:8081/libros/estadisticas/idioma?idioma=es`

## Consideraciones

- Solo se considera un autor por libro (el primero en la lista de la API).  
- Los años de fallecimiento pueden ser `null` para autores vivos.  
- La aplicación persiste los datos para poder realizar consultas y estadísticas sin depender de la API en tiempo real.

## Contribuciones

Las contribuciones son bienvenidas. Para sugerencias, mejoras o corrección de errores, puedes abrir un pull request o issue en el repositorio.
