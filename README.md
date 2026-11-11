# Máquina de Café con TDD y CI en GitHub Actions

![CI Pipeline](https://github.com/yaugepedro/tdd-maquina-cafe/actions/workflows/ci-pipeline.yml/badge.svg)

Proyecto de la materia **Introducción a la Ingeniería de Software** (ITLA). Es una máquina de café hecha en Java que se desarrolló con **TDD** (primero las pruebas, después el código) y que ahora tiene un **pipeline de CI** en GitHub Actions: cada vez que se sube un cambio, GitHub compila el proyecto y corre las pruebas de forma automática.

- **Estudiante:** Yauge Pedro Volquez Terrero
- **Matrícula:** 2025-1173
- **Docente:** Eduandy Isabel Cruz Abreu

## ¿Qué hace la máquina?

Desde la consola, el usuario elige el tamaño del vaso y cuántas cucharadas de azúcar quiere. Antes de servir, la máquina revisa que haya vasos, café y azúcar suficientes.

| Opción | Tamaño | Café |
|--------|--------|------|
| 1 | Pequeño | 3 oz |
| 2 | Mediano | 5 oz |
| 3 | Grande | 7 oz |
| 0 | Salir | |

Las respuestas posibles son:

- `Felicitaciones`: el pedido se completó y se descuentan los recursos.
- `No hay Vasos`, `No hay Cafe` o `No hay Azucar`: falta algún recurso y no se consume nada.
- `Pedido invalido`: vaso inexistente, cantidad de vasos menor o igual a cero, o azúcar negativa.

También se puede pedir café sin azúcar (cero cucharadas), incluso si el azucarero está vacío.

## Estructura del proyecto

```
.
├── .github/workflows/ci-pipeline.yml   # Pipeline de CI
├── lib/                                # JUnit 4.13.2 y Hamcrest 1.3
├── src/                                # Código de la máquina
│   ├── App.java                        # Menú de consola
│   ├── MaquinaDeCafe.java              # Lógica del pedido
│   ├── Vaso.java                       # Existencias y contenido de cada tamaño
│   ├── Cafetera.java                   # Café disponible
│   └── Azucarero.java                  # Azúcar disponible
└── test/
    ├── TestVaso.java                   # 3 pruebas
    └── TestSistemaCafe.java            # 23 pruebas
```

Cada clase tiene una sola responsabilidad: `Vaso`, `Cafetera` y `Azucarero` controlan sus existencias, y `MaquinaDeCafe` las junta para validar y preparar el pedido.

## TDD: cómo se desarrolló

Se siguió el ciclo rojo, verde y refactorización:

1. **Rojo:** se escribe una prueba para algo que todavía no existe y se comprueba que falle.
2. **Verde:** se escribe el código mínimo para que la prueba pase.
3. **Refactorización:** se mejora el código sin romper las pruebas que ya funcionaban.

El proyecto tiene **26 pruebas con JUnit**. Cubren la disponibilidad y el descuento de café y azúcar, la selección de los tres vasos, los mensajes cuando falta un recurso, que un pedido fallido no consuma nada, el café sin azúcar, varios vasos en un mismo pedido y los pedidos inválidos.

## Cómo ejecutarlo

Necesitas tener instalado el JDK de Java (el pipeline usa Java 17).

**Compilar** (desde la raíz del repositorio):

```bash
# Linux / macOS
mkdir -p out
javac -cp "lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" -d out src/*.java test/*.java
```

```powershell
# Windows (PowerShell)
mkdir out -Force
javac -cp "lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" -d out src/*.java test/*.java
```

**Correr las pruebas:**

```bash
# Linux / macOS
java -cp "out:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TestSistemaCafe TestVaso
```

```powershell
# Windows (PowerShell)
java -cp "out;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore TestSistemaCafe TestVaso
```

Si todo sale bien, verás `OK (26 tests)`.

**Usar la máquina desde la consola:**

```bash
# Linux / macOS
java -cp out App
```

```powershell
# Windows (PowerShell)
java -cp out App
```

## CI con GitHub Actions

El archivo `.github/workflows/ci-pipeline.yml` define el pipeline. Se dispara en cada `push` y en cada `pull request`, y hace lo siguiente en una máquina Ubuntu limpia:

1. Clona el repositorio.
2. Configura Java 17 (Temurin).
3. Compila el código y las pruebas.
4. Ejecuta las pruebas con JUnit.

Si una prueba falla, el pipeline se pone en rojo y el cambio no debería aceptarse. Así las pruebas escritas con TDD se ejecutan siempre, sin depender de que alguien se acuerde de correrlas.

## Flujo de ramas

Se trabajó con tres ramas:

| Rama | Para qué sirve |
|------|----------------|
| `main` | Versión estable |
| `develop` | Integra el trabajo terminado |
| `feature/nueva-funcionalidad` | Cambios nuevos, aislados del resto |

Los cambios van de `feature/*` a `develop` y de `develop` a `main`, siempre mediante pull request. El pipeline corre sobre cada uno de esos pull requests antes de fusionarlos.

## Herramientas

- Java y JUnit 4.13.2 (con Hamcrest Core 1.3)
- Git y GitHub
- GitHub Actions
- Visual Studio Code
