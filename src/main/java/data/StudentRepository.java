package data;

import model.Student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {

    private final StudentData studentData = new StudentData();

    private List<Student> students;

    public StudentRepository() {
        studentData.createStudentData();
        students = studentData.getStudents();
    }

    public List<Student> searchAll() {
        return students;
    }

    public List<Student> findByGroup(int group) {
        return students.stream()
                .filter(c -> c.getGroup() == group)
                .collect(Collectors.toList());
    }

    public List<Student> findByMark(int mark) {
        return students.stream()
                .filter(c -> c.getAvgMark() >= 90 )
                .collect(Collectors.toList());
    }

    public List<Student> findByWork() {
        return students.stream()
                .filter(c -> c.isWorking() == true)
                .collect(Collectors.toList());
    }
}
