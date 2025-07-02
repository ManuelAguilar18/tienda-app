
# TiendaApp - Gestión de Productos y Ventas

Aplicación web con Spring Boot 3, Java 21 y MySQL, con frontend en Thymeleaf y ejecución con Docker Compose.

---

## Tecnologías

- Java 21
- Spring Boot 3
- Spring Data JPA
- Thymeleaf + Bootstrap
- MySQL 8 (en contenedor Docker)
- Docker y Docker Compose
- Lombok y Validaciones Jakarta

---

## Requisitos Previos

Antes de empezar, asegúrate de tener instalados:

- **Java JDK 21**  
  [Adoptium Temurin](https://adoptium.net/)
- **Maven 3.8+**  
  [Apache Maven](https://maven.apache.org/download.cgi)
- **Docker Desktop** (incluye Docker Compose)  
  [Docker](https://www.docker.com/products/docker-desktop)

Verifica las instalaciones ejecutando:

```bash
java -version
mvn -v
docker -v
docker-compose -v
```

---

## Estructura del proyecto

```
Tienda-App/

├── src/main/java
├── docker-compose.yml
├── Dockerfile
├── start.bat (opcional)
├── README.md
└── target/
    └── Tienda-App-0.0.1-SNAPSHOT.jar
```

---

## Cómo ejecutar la aplicación

### Paso 1: Clonar o descargar el repositorio

```bash
git clone https://github.com/ManuelAguilar18/tienda-app.git
cd tienda-app
```

---

### Paso 2: Construir el archivo JAR

Desde la raíz del proyecto, ejecuta:

```bash
./mvnw clean package -DskipTests
```

Esto generará el archivo JAR en:

```
target/Tienda-App-0.0.1-SNAPSHOT.jar
```

---

### Paso 3: Ejecutar con Docker Compose

Este paso levantará dos contenedores: MySQL y la aplicación Spring Boot.

```bash
docker-compose up --build
```

- MySQL estará disponible en el puerto **3307** (puerto interno 3306)
- Spring Boot correrá en el puerto **8080**

**Importante:**  
El archivo `docker-compose.yml` expone MySQL en `3307` para evitar conflictos con instalaciones locales.

---

### Paso 4: Validar que los contenedores estén corriendo

En otra terminal puedes ejecutar:

```bash
docker ps
```

Deberías ver algo como:

```
CONTAINER ID   IMAGE          PORTS                     NAMES
xxxxxx         tienda_app     0.0.0.0:8080->8080/tcp    tienda_app
xxxxxx         mysql:8.0      0.0.0.0:3307->3306/tcp    tienda_mysql
```

---

### Paso 5: Acceder a la aplicación

Abre tu navegador en:

```
http://localhost:8080/productos
```

---

## 🔍 Validar la base de datos

1. Puedes conectar con MySQL en:

- Host: `localhost`
- Puerto: `3307`
- Usuario: `root`
- Password: `[POR DEFINIR]`

2. Usa un cliente MySQL (MySQL Workbench, DBeaver, o CLI):

```bash
mysql -h localhost -P 3307 -u root -p
# Ingresa: Colima10
```

3. Revisa la base de datos `tienda` creada automáticamente.

---

## Configuración y personalización

La configuración de conexión está en `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/tienda
    username: root
    password: [POR DEFINIR]
```

> La base de datos es creada por JPA automáticamente en el contenedor MySQL.

---

## Funcionalidades principales

- CRUD Productos con validaciones
- Registro de ventas que decrementan stock
- Frontend con Thymeleaf y Bootstrap
- Validaciones de campos en formularios

---

## Detener y limpiar contenedores y volúmenes

Para parar la app y eliminar datos persistentes:

```bash
docker-compose down -v
```

---

## Soporte y contacto

Juan Manuel Cruz Aguilar Molina.
