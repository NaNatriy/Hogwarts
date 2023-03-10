package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

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
    public ResponseEntity createStudent (@RequestBody Student student){
        Student createStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createStudent);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity getStudentById(@PathVariable Long Id) {
        Student student = studentService.getStudentById(Id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }
    @GetMapping
    public ResponseEntity<Collection<Student>> getAll(){
        Collection<Student> studentCollection = studentService.getAllStudents();
        return ResponseEntity.ok(studentCollection);
    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestParam Student student){
        Student updateStudent = studentService.updateStudent(student);
        return ResponseEntity.ok(updateStudent);
    }

    @DeleteMapping
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @GetMapping("/students/age/{age}")
    public ResponseEntity<Collection<Student>> getByAge(@RequestParam(required = false) Student student, @PathVariable Integer age) {
        Collection<Student> students = studentService.getByAge(age)
                .stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }
}
