package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static List<Student> students = Arrays.asList(
            new Student("Student1", Map.of("Math", 90, "Physics", 85)),
            new Student("Student2", Map.of("Math", 95, "Physics", 88)),
            new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
            new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
    );

    public static void main(String[] args) {
        Map<String, Double> averageGrades = students.parallelStream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.collectingAndThen(
                        Collectors.averagingDouble(Map.Entry::getValue),
                        avg -> Math.round(avg * 10.0) / 10.0
                )));

        averageGrades.forEach((subject, average) -> {
            System.out.println(subject + ": " + average);
        });
    }
}