![CI Pipeline](https://github.com/luisberrio-lang/LAB13-CONSTRUCCION/actions/workflows/ci.yml/badge.svg)

# MiniShop

Proyecto base de Laboratorio 13 construido con Spring Boot, Java 17, Maven, H2, pruebas unitarias, pruebas de integracion y GitHub Actions.

## Informacion academica

- Autor: Luis Washinton Berrio Valencia
- Curso: Construccion y Pruebas de Software
- Laboratorio 13

## Tecnologias

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- Maven
- JUnit 5
- Mockito
- MockMvc
- GitHub Actions

## Requisitos

- Java 17
- Maven 3.9+

## Ejecucion local

```bash
mvn spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

## Endpoints

- `GET /api/products`
- `GET /api/products/{id}`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

Ejemplo de producto:

```json
{
  "name": "Keyboard",
  "description": "Mechanical keyboard",
  "price": 149.90,
  "stock": 8
}
```

## H2 Console

Disponible en `http://localhost:8080/h2-console`.

Configuracion:

- JDBC URL: `jdbc:h2:mem:minishopdb`
- User: `sa`
- Password: vacio

## Pruebas

```bash
mvn test
```

Para ejecutar la verificacion completa:

```bash
mvn clean verify --no-transfer-progress
```

## Pipeline CI

El workflow `MiniShop CI Pipeline` se ejecuta en GitHub Actions sobre `ubuntu-latest` para cada `push` a `main` y cada `pull_request` dirigido a `main`.

El pipeline realiza estas tareas:

- Descarga el repositorio con `actions/checkout@v4`.
- Configura Java 17 Temurin con `actions/setup-java@v4`.
- Habilita cache de Maven.
- Ejecuta `mvn compile`.
- Ejecuta `mvn test`.
- Ejecuta `mvn verify`.
- Publica `target/surefire-reports` como artefacto con `actions/upload-artifact@v4`.
- Usa `if: always()` para publicar reportes aunque las pruebas fallen.
