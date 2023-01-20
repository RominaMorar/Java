package org.example;

import model.Aspirant;
import model.Person;
import model.Student;
import serialize.JsonSerialize;
import serialize.TxtSerialize;
import serialize.XmlSerialize;
import service.StudentService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Person person1 = new Person.Builder()
                .firstName("Andriy")
                .lastName("Shevchenko")
                .yearOfBirth(1998)
                .build();

        Student student1 = new Student.Builder()
                .firstName("Alina")
                .lastName("Pash")
                .yearOfBirth(2001)
                .group(302)
                .avgMark(95)
                .working(true)
                .build();

        Aspirant aspirant1 = new Aspirant.Builder()
                .firstName("Nikita")
                .lastName("Razumkov")
                .yearOfBirth(1998)
                .group(502)
                .avgMark(75)
                .working(true)
                .Work("Optimization")
                .build();

        System.out.println(person1);
        System.out.println(student1);
        System.out.println(aspirant1);

        Student student2 = new Student.Builder()
                .firstName("Roman")
                .lastName("Tishenko")
                .yearOfBirth(2002)
                .group(401)
                .avgMark(85)
                .working(true)
                .build();

        System.out.println(student2);

        System.out.println("Equals = " + student1.equals(student2));
        boolean b = student1.hashCode() == student2.hashCode();
        System.out.println("Hash code = " + b);

        new JsonSerialize<Aspirant>().writeObject("aspirant.json", aspirant1);
        new XmlSerialize<Aspirant>().writeObject("aspirant.xml", aspirant1);
        new TxtSerialize<Aspirant>().writeObject("aspirant.txt", aspirant1);

        StudentService studentService = new StudentService();

        System.out.println("\nDemo StudentService.getAll() =====================");
        List<Student> students = studentService.searchAll();
        students.forEach(System.out::println);

        System.out.println("\nDemo StudentService.findByGroup(302) =====================");
        studentService.findByGroup(302).forEach(System.out::println);

        System.out.println("\nDemo StudentService.findByMark(90) =====================");
        studentService.findByMark(90).forEach(System.out::println);

        System.out.println("\nDemo StudentService.findByWork() =====================");
        studentService.findByWork().forEach(System.out::println);

    }

}