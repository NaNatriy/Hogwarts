package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.HousesRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    private HousesRepository housesRepository;

    public StudentService(StudentRepository studentRepository, HousesRepository housesRepository) {
        this.studentRepository = studentRepository;
        this.housesRepository = housesRepository;
    }

    @Transactional
    public Student createStudent(StudentDTO studentDTO) {
        Faculty faculty = housesRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
        Student student = new Student(studentDTO.getName(), studentDTO.getAge(), faculty);
        return studentRepository.save(student);
    }

    @Transactional
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));
    }
    @Transactional
    public Student updateStudent(Long id, StudentDTO studentDTO){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        Faculty faculty = housesRepository.findById(studentDTO.getFacultyId())
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
        student.setFaculty(faculty);
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());

        return studentRepository.save(student);
    }
    @Transactional
    public void deleteStudent(Long Id){
        studentRepository.deleteById(Id);
    }
    @Transactional
    public Collection<Student> getByAgeMinMax(Integer min, Integer max){
        return studentRepository.findByAgeBetween(min, max);
    }
}
