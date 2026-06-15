# MiniShop

Proyecto base de laboratorio construido desde cero con Spring Boot, Java 17, Maven, H2, pruebas unitarias, pruebas de integracion y GitHub Actions.

## Requisitos

- Java 17
- Maven 3.9+

## Ejecutar la aplicacion

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
