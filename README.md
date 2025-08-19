# ForoHub API REST

Una API REST desarrollada con Spring Boot para la gestión de tópicos de un foro de discusión.

---

## 🚀 Tecnologías Utilizadas

* Java 17
* Spring Boot 3.2.4
* Spring Data JPA
* Spring Security
* Flyway
* MySQL
* JWT (JSON Web Tokens)
* Maven

---

## 🛠️ Funcionalidades

La API permite:
* **Autenticación y Autorización:** Registro de usuarios y autenticación con JWT.
* **Gestión de Tópicos:**
    * Crear nuevos tópicos.
    * Listar todos los tópicos.
    * Ver detalles de un tópico específico.
    * Actualizar un tópico existente.
    * Eliminar un tópico.
* **Búsqueda:** Búsqueda avanzada de tópicos por curso y año.
* **Validaciones:** Validación de datos de entrada y manejo de errores centralizado.

---

## 📖 Endpoints de la API

| HTTP Method | URL | Descripción |
|---|---|---|
| `POST` | `/login` | Autentica al usuario y retorna un JWT. |
| `POST` | `/users` | **Registra un nuevo usuario.** |
| `POST` | `/topics` | Crea un nuevo tópico. Requiere token JWT. |
| `GET` | `/topics` | Lista todos los tópicos con paginación. Requiere token JWT. |
| `GET` | `/topics/{id}` | Muestra los detalles de un tópico por su ID. |
| `PUT` | `/topics/{id}` | Actualiza un tópico existente por su ID. Requiere token JWT. |
| `DELETE`| `/topics/{id}` | Elimina un tópico por su ID. Requiere token JWT. |

---

## 🧑‍💻 Cómo Ejecutar el Proyecto

1.  **Clona el repositorio:** `git clone <URL_del_repositorio>`
2.  **Configura la base de datos:** Crea una base de datos MySQL llamada `forohub` y configura tus credenciales en el archivo `application.properties`.
3.  **Ejecuta las migraciones:** Flyway se encargará de crear las tablas automáticamente al iniciar la aplicación.
4.  **Ejecuta la aplicación:** Puedes ejecutar la aplicación desde tu IDE o usando Maven con el comando `mvn spring-boot:run`.

---

