package service;

import data.StudentRepository;
import model.Student;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepository();
    }

    public List<Student> searchAll(){
       return studentRepository.searchAll();
    }

    public List<Student> findByGroup(int group){
        return studentRepository.findByGroup(group);
    }

    public List<Student> findByMark(int mark){
        return studentRepository.findByMark(mark);
    }

    public List<Student> findByWork(){
        return studentRepository.findByWork();
    }

    public Set<Student> sort(){
        return new TreeSet<>(studentRepository.searchAll());
    }
}
