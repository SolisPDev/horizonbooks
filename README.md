# HorizonBooks

HorizonBooks es una aplicación de consola desarrollada en **Java** utilizando **Spring Boot**, **JPA** y otras tecnologías para gestionar una base de datos de libros y autores. Esta aplicación permite a los usuarios buscar información sobre libros a través de una API, registrarlos en una base de datos local si no existen, y consultar libros y autores registrados.

---

## Características

1. **Búsqueda de libros por título:** Consulta una API de libros y registra los datos en la base de datos si no existen.
2. **Consulta de libros registrados:** Muestra todos los libros almacenados en la base de datos.
3. **Consulta de autores registrados:** Lista todos los autores almacenados en la base de datos.
4. **Consulta de autores vivos en un año específico:** Muestra autores que estaban vivos en el año indicado.
5. **Consulta de libros por idioma:** Lista los libros disponibles en un idioma específico.

---

## Requisitos Previos

- **Java 17** o superior.
- **Maven** (para gestionar dependencias).
- **PostgreSQL** como base de datos.
- Dependencias configuradas en el archivo `pom.xml`:
  - Spring Boot Starter Data JPA.
  - Spring Boot Starter Web.
  - PostgreSQL Driver.

---

## Instalación y Configuración

### 1. Clona el repositorio
```bash
git clone https://github.com/tu_usuario/horizonbooks.git
cd horizonbooks
```

### 2. Configura la base de datos
Crea una base de datos PostgreSQL y actualiza las credenciales en el archivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/horizonbooks
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

### 3. Ejecuta la aplicación
Usa Maven para compilar y ejecutar la aplicación:
```bash
mvn spring-boot:run
```

---

## Uso de la Aplicación

### Menú Principal
Cuando ejecutas la aplicación, se muestra un menú con las siguientes opciones:
```plaintext
=== HorizonBooks ===
======  MENU  ======

[ 1 ] Buscar libro por título
[ 2 ] Consulta libros registrados
[ 3 ] Consulta autores registrados
[ 4 ] Consulta autores vivos en un determinado año
[ 5 ] Consulta libros por idioma

[ 0 ] Salir
```

### Funcionalidades
1. **Buscar libro por título:**
   - Ingresa el título de un libro para buscarlo en la API.
   - Si el libro no está en la base de datos, se registra junto con su autor.

2. **Consulta libros registrados:**
   - Muestra todos los libros en la base de datos con detalles como título, autor, idiomas y descargas.

3. **Consulta autores registrados:**
   - Lista todos los autores en la base de datos con información sobre sus años de nacimiento y fallecimiento.

4. **Consulta autores vivos en un determinado año:**
   - Ingresa un año para listar los autores que estaban vivos en ese momento.

5. **Consulta libros por idioma:**
   - Ingresa un código de idioma (por ejemplo, `en` para inglés, `es` para español) para listar los libros disponibles en ese idioma.

---

## Ejemplo de Salida
### Buscar Libro
```plaintext
Ingresa el nombre del libro:
Cien años de soledad
Título: Cien años de soledad
Autor(es): Gabriel García Márquez
Lenguaje(s): [en, es]
********************************
Libro registrado en la base de datos.
```

### Consultar Libros Registrados
```plaintext
=== Libros Registrados ===
===============  LIBRO ===============
titulo  = Cien años de soledad
Autor   = Gabriel García Márquez
Idiomas =[en, es]
Total de Descargas = 50000.0
======================================
--------------------------
```

### Consultar Autores Vivos en un Año
```plaintext
Ingresa el año a consultar:
1960
=== Autores Vivos en el Año 1960 ===
=============== Autor ===============
Nombre :Gabriel García Márquez
Año de Naciminto :1927
Año de Fallecimiento : 2014
====================================
--------------------------
```

### Consultar Libros por Idioma
```plaintext
Ingresa el idioma a consultar (por ejemplo, 'en' para inglés, 'es' para español):
es
=== Libros en el idioma 'es' ===
===============  LIBRO ===============
titulo  = Cien años de soledad
Autor   = Gabriel García Márquez
Idiomas =[en, es]
Total de Descargas = 50000.0
======================================
--------------------------
```

---

## Créditos

Desarrollado por: **Antonio Solís Perales - Ing. en Informática**

Proyecto de formación como desarrollador Back-End con Java del programa ONE y AluraLatam.

![Grupo 7 ONE](https://cdn2.gnarususercontent.com.br/1/1221562/b6256fa6-5fde-4cdd-a4a3-d33ebc90bb6c.png)
![Grupo 7 ONE](https://app.aluracursos.com/assets/images/logos/logo-aluraespanhol.svg)

---

## Licencia
Este proyecto está licenciado bajo los términos de la [MIT License](LICENSE).

