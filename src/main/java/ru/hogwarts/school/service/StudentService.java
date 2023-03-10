package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private Map<Long, Student> students = new HashMap<>();
    private Long generatedValueId = 1L;

    public Collection<Student> getAllStudents(){
        return students.values();
    }

    public Student createStudent(Student student) {
        students.put(generatedValueId, student);
        generatedValueId++;
        return student;
    }
    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }
    public Student updateStudent(Long studentId, Student student){
        students.put(studentId, student);
        return student;
    }
    public Student deleteStudent(Long studentId){
        return students.remove(studentId);
    }
    public List<Student> getAge(int age) {
        List<Student> matchingStudents = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                matchingStudents.add(student);
            }
        }
        return matchingStudents;
    }
}
