package ru.hogwarts.school.dto;

import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

public class StudentDTO {
    private Long id;
    private String name;
    private Integer age;
    private Long facultyId;

    public StudentDTO() {
    }

    public StudentDTO(Long id, String name, Integer age, Long facultyId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.facultyId = facultyId;
    }

    public static StudentDTO fromStudent(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());
        dto.setFacultyId(student.getFaculty().getId());
        return dto;
    }

    public Student toStudent() {
        Student student = new Student();
        student.setId(this.getId());
        student.setName(this.getName());
        student.setAge(this.getAge());
        Faculty faculty = new Faculty();
        faculty.setId(this.getFacultyId());
        student.setFaculty(faculty);
        return student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
}
