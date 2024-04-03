# Ejercicios Computación Paralela y Distribuida 2024-1

Solución y explicación de los ejercicios propuestos para el curso.

**Equipo**
* Paula Daniela Guzmán Zabala
* Carlos Andres Rios Rojas

**Índice**
* [Compilación de Ejercicios](#id1)
    * [Ejercicio 0](#id1.0)
    * [Ejercicio 1](#id1.1)
    * [Ejercicio 2](#id1.2)
* [Configuración de Ambiente de Desarrollo](#id2)

---
</br>

<a id="id1"></a>

## Compilación de Ejercicios

<a id="id1.0"></a>

### Ejercicio 0

Compilar el ejercicio

```bash
cd ejercicio_0 && mvn compile && cd ..
```

Testear el ejercicio

```bash
cd ejercicio_0 && mvn test && cd ..
```

<a id="id1.1"></a>

### Ejercicio 1

Compilar el ejercicio

```bash
cd ejercicio_1 && mvn compile && cd ..
```

Testear el ejercicio

```bash
cd ejercicio_1 && mvn test && cd ..
```

<a id="id1.2"></a>

### Ejercicio 2

Compilar el ejercicio

```bash
cd ejercicio_2 && mvn compile && cd ..
```

Testear el ejercicio

```bash
cd ejercicio_2 && mvn test && cd ..
```

---
</br>

<a id="id2"></a>

## Configuración de Ambiente de Desarrollo

1. Descargar este repositorio

```bash
git clone https://github.com/Paula2208/Paralela.git
cd Paralela
```

2. Instalar el JDK de Java 8 o superior [https://www.oracle.com/java/technologies/downloads/#java8](https://www.oracle.com/java/technologies/downloads/#java8).

3. Extraer la herramienta de gestión de proyectos de software Apache Maven [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) en los archivos de programa.

4. Añadir la carpeta `bin` del directorio extraido `apache-maven-3.9.6` en la variable de entorno `PATH`.

```bash
## Probar la instalación
mvn -v
```

5. Instalar y configurar en Maven el framework pdcp [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi).

```bash
cd PCDP && mvn install
```

6. Compilar el ejercicio deseado con las instrucciones dispuestas en el apartado de [Compilación de Ejercicios](#id1). 