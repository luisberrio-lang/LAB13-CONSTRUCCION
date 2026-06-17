# LAB14 - Resultados de Pruebas Estaticas

## Datos generales

- Nombre: Luis Washinton Berrio Valencia
- Curso: Construccion y Pruebas de Software
- Laboratorio 14: Pruebas Estaticas

## Descripcion breve

Se configuro JaCoCo para medir cobertura de lineas durante `verify`, se agrego la regla minima del 70%, se integro el plugin de Sonar Maven para SonarCloud y se actualizo el pipeline de GitHub Actions para compilar, probar, verificar cobertura, ejecutar analisis condicional y publicar reportes.

Tambien se agregaron pruebas utiles para caminos correctos y de error en servicios, controladores, validaciones y modelo.

## Comandos utilizados

```bash
mvn clean verify --no-transfer-progress
git status
git diff
git log --oneline -10
```

En el entorno local de trabajo se uso el Maven embebido de IntelliJ porque `mvn` no estaba disponible en el PATH.

## Reporte JaCoCo

- HTML: `target/site/jacoco/index.html`
- XML: `target/site/jacoco/jacoco.xml`
- Cobertura total de lineas obtenida: 96.34%
- Umbral configurado: 70%

## Tabla de cobertura

| Clase | Cobertura de lineas | Metodos sin cobertura | Observacion |
| --- | ---: | ---: | --- |
| `pe.edu.tecsup.minishop.model.Product` | 100.00% | 0 | Cubiertos getters/setters usados por flujos y caminos de `equals/hashCode`. |
| `pe.edu.tecsup.minishop.service.ProductService` | 100.00% | 0 | Cubiertos listar, buscar, crear, actualizar, eliminar y errores por producto inexistente. |
| `pe.edu.tecsup.minishop.MiniShopApplication` | 33.33% | 1 | Falta cobertura directa del metodo `main`; no afecta la logica de negocio. |
| `pe.edu.tecsup.minishop.repository.ProductRepository` | 100.00% | 0 | Interfaz sin lineas ejecutables propias; se valida mediante pruebas de integracion. |
| `pe.edu.tecsup.minishop.exception.ApiExceptionHandler` | 100.00% | 0 | Cubiertos errores 404 y validaciones 400. |
| `pe.edu.tecsup.minishop.exception.ProductNotFoundException` | 100.00% | 0 | Cubierto por servicio e integracion. |
| `pe.edu.tecsup.minishop.controller.ProductController` | 88.89% | 1 | Cubiertos endpoints principales; queda sin cobertura directa una ruta menor del controlador. |

## Tabla de SonarCloud

| Categoria | Cantidad encontrada | Severidad mas alta | Clase mas afectada | Accion recomendada |
| --- | --- | --- | --- | --- |
| Bugs | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Revisar el primer reporte de SonarCloud y corregir hallazgos reales. |
| Vulnerabilities | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Revisar dependencias y configuraciones reportadas por SonarCloud. |
| Code Smells | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Priorizar los olores de codigo con mayor impacto en mantenibilidad. |
| Security Hotspots | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Pendiente de completar despues del primer analisis | Evaluar manualmente cada hotspot antes de marcarlo como revisado. |

## Oportunidades de mejora

1. Agregar pruebas especificas para el metodo `main` o excluirlo formalmente solo si el equipo define que no aporta valor medirlo.
2. Separar pruebas unitarias puras de pruebas de integracion con perfiles o convenciones de nombres para controlar mejor el tiempo del pipeline.
3. Agregar escenarios futuros para reglas de negocio de stock y precios cuando MiniShop crezca.

## Preguntas de reflexion

### 1. Que aporta JaCoCo al proyecto?

JaCoCo permite ver que partes del codigo fueron ejecutadas por las pruebas. En este laboratorio ayudo a validar que la cobertura total supera el 70% y a ubicar clases con menor cobertura.

### 2. Por que no se deben usar exclusiones artificiales para subir cobertura?

Porque ocultarian riesgos reales. La cobertura debe mejorar con pruebas utiles que validen comportamiento, no eliminando clases del reporte sin una razon tecnica clara.

### 3. Que diferencia hay entre pruebas unitarias e integracion?

Las pruebas unitarias revisan una clase aislada, normalmente con mocks. Las pruebas de integracion validan que varias piezas trabajen juntas, por ejemplo controlador, servicio, repositorio y base H2.

### 4. Que papel cumple SonarCloud?

SonarCloud revisa calidad, mantenibilidad, seguridad y duplicacion del codigo. Complementa las pruebas porque puede detectar problemas que no siempre provocan fallos funcionales.

## Observaciones

- La verificacion local final termino con `BUILD SUCCESS`.
- Se ejecutaron 21 pruebas con 0 fallos, 0 errores y 0 omitidas.
- JaCoCo genero correctamente `target/site/jacoco/index.html` y `target/site/jacoco/jacoco.xml`.
- El entorno local no tenia `git` ni `mvn` en el PATH; por eso la validacion se hizo con el Maven incluido en IntelliJ.
- Los resultados de SonarCloud quedan pendientes hasta crear organizacion, proyecto y secretos en GitHub.

## Conclusiones

El proyecto MiniShop queda preparado para ejecutar pruebas estaticas y cobertura en CI. La cobertura real obtenida supera el umbral solicitado y el pipeline queda listo para ejecutar SonarCloud cuando se configuren los secretos requeridos.

## Capturas pendientes

### Captura 1: Ejecucion local de `mvn clean verify --no-transfer-progress`

Espacio para colocar la captura:

### Captura 2: Reporte HTML de JaCoCo en `target/site/jacoco/index.html`

Espacio para colocar la captura:

### Captura 3: Artifact `surefire-reports` en GitHub Actions

Espacio para colocar la captura:

### Captura 4: Artifact `jacoco-report` en GitHub Actions

Espacio para colocar la captura:

### Captura 5: Configuracion de secretos en GitHub Actions

Espacio para colocar la captura:

### Captura 6: Primer analisis de SonarCloud

Espacio para colocar la captura:
