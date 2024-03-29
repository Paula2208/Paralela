package co.edu.unal.paralela;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Una clase 'envoltorio' (wrapper) para varios métodos analíticos.
 */
public final class StudentAnalytics {
    /**
     * Calcula secuencialmente la edad promedio de todos los estudientes registrados
     * y activos
     * utilizando ciclos.
     *
     * @param studentArray Datos del estudiante para la clase.
     * @return Edad promedio de los estudiantes registrados
     */
    public double averageAgeOfEnrolledStudentsImperative(
            final Student[] studentArray) {
        List<Student> activeStudents = new ArrayList<Student>();

        for (Student s : studentArray) {
            if (s.checkIsCurrent()) {
                activeStudents.add(s);
            }
        }

        double ageSum = 0.0;
        for (Student s : activeStudents) {
            ageSum += s.getAge();
        }

        return ageSum / (double) activeStudents.size();
    }

    /**
     * PARA HACER calcular la edad promedio de todos los estudiantes registrados y
     * activos usando
     * streams paralelos. Debe reflejar la funcionalidad de
     * averageAgeOfEnrolledStudentsImperative. Este método NO debe utilizar ciclos.
     *
     * @param studentArray Datos del estudiante para esta clase.
     * @return Edad promedio de los estudiantes registrados
     */
    public double averageAgeOfEnrolledStudentsParallelStream(
            final Student[] studentArray) {
        return Stream.of(studentArray) // @audit-info Se crea un stream del array de estudiantes
                .parallel() // @audit-info Se paralelizan los métodos aplicados al stream
                .filter(Student::checkIsCurrent) // @audit-info Revisa si el estudiante está activo
                .mapToDouble(Student::getAge) // @audit-info Trae la edad del estudiante
                .average() // @audit-info Hace un promedio de la edad
                .orElse(0.0); // @audit-info Retorna la edad promedio o 0.0 si no se pudo calcular
    }

    /**
     * Calcula secuencialmente -usando ciclos- el nombre más común de todos los
     * estudiantes
     * que no están activos en la clase.
     *
     * @param studentArray Datos del estudiante para esta clase.
     * @return Nombre más común de los estudiantes inactivos.
     */
    public String mostCommonFirstNameOfInactiveStudentsImperative(
            final Student[] studentArray) {
        List<Student> inactiveStudents = new ArrayList<Student>();

        for (Student s : studentArray) {
            if (!s.checkIsCurrent()) {
                inactiveStudents.add(s);
            }
        }

        Map<String, Integer> nameCounts = new HashMap<String, Integer>();

        for (Student s : inactiveStudents) {
            if (nameCounts.containsKey(s.getFirstName())) {
                nameCounts.put(s.getFirstName(),
                        new Integer(nameCounts.get(s.getFirstName()) + 1));
            } else {
                nameCounts.put(s.getFirstName(), 1);
            }
        }

        String mostCommon = null;
        int mostCommonCount = -1;
        for (Map.Entry<String, Integer> entry : nameCounts.entrySet()) {
            if (mostCommon == null || entry.getValue() > mostCommonCount) {
                mostCommon = entry.getKey();
                mostCommonCount = entry.getValue();
            }
        }

        return mostCommon;
    }

    /**
     * PARA HACER calcula el nombre más común de todos los estudiantes que no están
     * activos
     * en la clase utilizando streams paralelos. Debe reflejar la funcionalidad
     * de mostCommonFirstNameOfInactiveStudentsImperative. Este método NO debe usar
     * ciclos
     *
     * @param studentArray Datos de estudiantes para la clase.
     * @return Nombre más comun de los estudiantes inactivos.
     */
    public String mostCommonFirstNameOfInactiveStudentsParallelStream(
            final Student[] studentArray) {
        return Stream.of(studentArray) // @audit-info Se crea un stream con el array de estudiantes
                .parallel() // @audit-info Se paralelizan los métodos que se ejecutarán sobre el stream
                .filter(student -> !student.checkIsCurrent()) // @audit-info Filtra los estudiantes no activos
                .map(Student::getFirstName) // @audit-info Trae el nombre de cada estudiante no activo
                .collect(Collectors.groupingBy(name -> name, Collectors.counting())) // @audit-info Agrupa por nombre
                                                                                     // creando un map
                .entrySet() // @audit-info Crea un Set del map de nombres
                .stream() // @audit-info Crea un stream del Set de nombres
                .max(Map.Entry.comparingByValue()) // @audit-info Obtiene el nombre con mayor repetición
                .map(Map.Entry::getKey) // @audit-info Obtiene el valor del nombre con mayor repetición
                .orElse(""); // @audit-info Si no existe el nombre, devuelve string vacío ("")
    }

    /**
     * calcula secuencialmente el número de estudiantes que han perdido el curso
     * que son mayores de 20 años. Una calificación de perdido es cualquiera por
     * debajo de 65
     * 65. Un estudiante ha perdido el curso si tiene una calificación de perdido
     * y no está activo en la actuialidad
     *
     * @param studentArray Datos del estudiante para la clase.
     * @return Cantidad de calificacione sperdidas de estudiantes mayores de 20 años
     *         de edad.
     */
    public int countNumberOfFailedStudentsOlderThan20Imperative(
            final Student[] studentArray) {
        int count = 0;
        for (Student s : studentArray) {
            if (!s.checkIsCurrent() && s.getAge() > 20 && s.getGrade() < 65) {
                count++;
            }
        }
        return count;
    }

    /**
     * PARA HACER calcular el número de estudiantes que han perdido el curso
     * que son mayores de 20 años de edad . una calificación de perdido está por
     * debajo de 65.
     * Un estudiante ha perdido el curso si tiene una calificación de perdido
     * y no está activo en la actuialidad. Debe reflejar la funcionalidad de
     * countNumberOfFailedStudentsOlderThan20Imperative. El método no debe usar
     * ciclos.
     *
     * @param studentArray Datos del estudiante para la clase.
     * @return Cantidad de calificacione sperdidas de estudiantes mayores de 20 años
     *         de edad.
     */
    public int countNumberOfFailedStudentsOlderThan20ParallelStream(
            final Student[] studentArray) {
        return (int) Stream.of(studentArray) // @audit-info Se crea un stream con el array de estudiantes
                .parallel() // @audit-info Se paralelizan los métodos que se ejecutarán sobre el stream
                // @audit-info Filtra los estudiantes para solo tener los no activos, mayores a 20 años con calificación menor a 65
                .filter(student -> !student.checkIsCurrent() && student.getAge() > 20 && student.getGrade() < 65)
                .count(); // @audit-info Retorna la cuenta de los elementos del stream
    }
}
