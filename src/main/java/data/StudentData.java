package data;

import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentData {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    public void createStudentData() {
        Student student1 = new Student.Builder()
                .firstName("Alina")
                .lastName("Pash")
                .yearOfBirth(2001)
                .group(302)
                .avgMark(95)
                .working(true)
                .build();

        Student student2 = new Student.Builder()
                .firstName("Andriy")
                .lastName("Ilarioniv")
                .yearOfBirth(2003)
                .group(102)
                .avgMark(85)
                .working(false)
                .build();

        Student student3 = new Student.Builder()
                .firstName("Petro")
                .lastName("Pavlyuk")
                .yearOfBirth(1999)
                .group(501)
                .avgMark(90)
                .working(true)
                .build();

        students = Arrays.asList(student1, student2, student3);
    }
}
