package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {

    private Map<Long, Student> students = new HashMap<>();
    private Long generatedValueId = 0L;

    public Collection<Student> getAllStudents(){
        return students.values();
    }

    public Student createStudent(Student student) {
        student.setId(++generatedValueId);
        students.put(generatedValueId, student);
        return student;
    }
    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }
    public Student updateStudent(Long id, Student student){
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null;
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
