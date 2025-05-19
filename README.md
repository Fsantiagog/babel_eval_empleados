# API de Gestión de Empleados

Este proyecto es una API RESTful desarrollada con **Spring Boot** para la gestión de empleados. Permite crear, consultar, actualizar y eliminar empleados, así como registrar bitácoras de operaciones.

## Características

- CRUD de empleados
- Registro de bitácora mediante Aspect
- Filtros para logging de headers de las peticiones
- Uso de MapStruct para mapeo de entidades y DTOs
- Documentación OpenAPI (Swagger)

## Tecnologías

- Java 17+
- Spring Boot
- Maven
- MapStruct
- Lombok
- OpenAPI (springdoc-openapi)
- JPA/Hibernate
- Docker (Alpine)

## Estructura del Proyecto

- `controllers`: Controladores REST
- `domains`: Lógica de negocio
- `dtos`: Modelos de datos para requests/responses
- `mappers`: MapStruct para conversión de entidades
- `persistence`: Entidades y repositorios JPA
- `aop`: Aspectos para bitácora
- `components`: Filtros y utilidades

## Ejecución

### 1. Clona el repositorio

```bash
git clone https://github.com/Fsantiagog/babel_eval_empleados
cd balbel_eval_empleados
```

### 2. Compila el proyecto

```bash
mvn clean package -DskipTests
```

### 3. Construye la imagen de docker

```bash
docker build -t empleados-api .
```

### 4. Crea la base de datos

```bash
ocker run --name postgres -e POSTGRES_PASSWORD=1q2w3e4r -d -p 5432:5432 postgres
```

### 5. Ejecuta el contenedor

```bash
docker run -p 8080:8080 empleados-api
```

### 6. Contrato

```bash
http://localhost:8080/swagger-ui.html
```

