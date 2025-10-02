# Warehouse Simulator

Este proyecto implementa un simulador de un almacén con robots y obstáculos.  
Permite ejecutar la simulación de dos formas:

1. **Modo archivo (local)** → ejecutando un programa que lee `resources/input.txt`.  
2. **Modo remoto (HTTP)** → a través de un servidor embebido que expone un endpoint `/simulate`.  

---

## Estructura principal

- `Warehouse.java` → Ejecuta la simulación leyendo el archivo `resources/input.txt`.
- `WarehouseSimulator.java` → Contiene la lógica de la simulación (procesa dimensiones, obstáculos y movimientos de robots).
- `WarehouseHttpServer.java` → Inicia un servidor HTTP en `http://localhost:8080/simulate` para ejecutar la simulación de forma remota.
- `warehouse.data.*` → Contiene las clases de soporte (`Grid`, `Robot`, `Coordinate`).

---

## Requisitos

- **JDK 11+** (usa `com.sun.net.httpserver`, que viene incluido en el JDK).  
- **Archivo de entrada** con formato válido (ejemplo más abajo).  

---

## Formato del archivo de entrada

El archivo `resources/input.txt` debe contener:  
1. Dimensiones de la grilla (`maxX maxY`)  
2. Número de obstáculos  
3. Coordenadas de cada obstáculo  
4. Posiciones iniciales y secuencia de instrucciones de cada robot  

### Ejemplo:
10 10
2
3 5
7 4
2 3
NNEEPW
6 4
EESSP
0 0
NNNNN

---

## Ejecución en modo archivo (local)

1) Colocar el archivo de entrada en `resources/input.txt`.  
2) Compilar y ejecutar la clase `Warehouse`:  

```bash
javac -d . src/warehouse/*.java src/warehouse/data/*.java
java warehouse.Warehouse
```

3) El programa mostrará en consola el resultado de la simulación.

## Ejecución en modo remoto (HTTP)

1) Ejecutar el servidor HTTP:

```bash
java warehouse.WarehouseHttpServer
```

Esto iniciará el servidor en: http://localhost:8080/simulate

2) Probar el endpoint enviando el archivo como cuerpo de la petición con curl:

```bash
curl -X POST http://localhost:8080/simulate \
     -H "Content-Type: text/plain" \
     --data-binary "@resources/input.txt"
```

o bien, pasando directamente los datos de la petición con el formato indicado anteriormente:

```bash
curl -X POST http://localhost:8080/simulate -d '10 10 2 3 5 7 4 2 3 NNEEPW 6 4 EESSP 0 0 NNNNN'
```

3) El servidor devolverá el resultado de la simulación como texto plano.

## Notas importantes

- El servidor solo acepta solicitudes POST.

- El cuerpo de la petición debe ser texto plano en el mismo formato que input.txt.

- Si el input es inválido, el servidor responde con: "Error Procesando Input"

- El proyecto no usa librerías externas: solo JDK estándar.

- Todos los comandos deben ejecutarse desde la raíz del proyecto.

- La grilla no se representa con el Norte hacia arriba (como en un mapa convencional), sino con el **Norte apuntando hacia la derecha**. Esto debe tenerse en cuenta al definir las coordenadas iniciales y las secuencias de instrucciones.















