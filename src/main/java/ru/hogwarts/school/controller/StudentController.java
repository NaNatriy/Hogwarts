package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.HousesRepository;
import ru.hogwarts.school.service.StudentService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody @Valid StudentDTO studentDTO) {
        Student createdStudent = studentService.createStudent(studentDTO);
        StudentDTO createdStudentDTO = new StudentDTO(
                createdStudent.getId(),
                createdStudent.getName(),
                createdStudent.getAge(),
                createdStudent.getFaculty().getId());
        return ResponseEntity.ok(createdStudentDTO);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long Id) {
        Student student = studentService.getStudentById(Id);
        StudentDTO studentDTO = StudentDTO.fromStudent(student);
        return ResponseEntity.ok(studentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {
        Student updatedStudent = studentService.updateStudent(id, studentDTO);
        StudentDTO updateStudentDTO = new StudentDTO(
                updatedStudent.getId(),
                updatedStudent.getName(),
                updatedStudent.getAge(),
                updatedStudent.getFaculty().getId()
        );
        return ResponseEntity.ok(updateStudentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/student/age")
    public ResponseEntity<Collection<StudentDTO>> getStudentsByAge(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        Collection<Student> students = studentService.getByAgeMinMax(min, max);
        Collection<StudentDTO> studentsDTO = students.stream().map(StudentDTO::fromStudent).collect(Collectors.toList());
        return ResponseEntity.ok(studentsDTO);
    }
}
