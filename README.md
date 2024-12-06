#  README.md
##  1. Foro Zuro

## 2. Descripción

Foro Zuro es una aplicación web desarrollada con Java y Spring Boot, diseñada para permitir la creación y gestión de foros de discusión. Los usuarios pueden autenticarse, crear temas, y comentar en ellos utilizando un sistema seguro con autenticación JWT.

## 3. Características
- Autenticación segura mediante JWT.
- Gestión de usuarios con roles.
- Creación, actualización y eliminación de tópicos.
- Paginación para listar tópicos.
- Diseño RESTful para integrarse con otras aplicaciones.
## 4. Tecnologías Utilizadas
- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven
## 5. Requisitos
- Java 17 o superior
- MySQL 8.0
- Maven 3.6+
## 6. Instalación

#### 1. Clona este repositorio:
     
```bash
   git clone https://github.com/tu_usuario/foro-zuro.git
```

#### 2. Configura tu base de datos en application.properties:

```properties
spring.datasource.url=jdbc:mysql://localhost/zuro_foro
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

#### 3. Ejecuta la aplicación:

```bash
mvn spring-boot:run
```

#### 4. Accede a la API:

```url
http://localhost:8080
```

## **7. Endpoints Principales**

#### Autenticación

- **POST /login**
  
  - Body:
    
    ```json
    {
        "username": "usuario",
        "password": "contraseña"
    }
    ```

### Tópicos

- **GET /topicos**  
  - Retorna la lista paginada de tópicos activos.
    
- **POST /topicos**  
  - Crea un nuevo tópico (requiere token JWT).
    
- **PUT /topicos/{id}**  
  - Actualiza un tópico existente.
    
- **DELETE /topicos/{id}**  
  - Elimina un tópico (lógica de eliminación).
    
## 8. Contribuciones
Las contribuciones son bienvenidas. Por favor, crea un *fork* del repositorio, realiza tus cambios, y envía un *pull request*.

## 9. Licencia
Este proyecto está bajo la Licencia MIT.
