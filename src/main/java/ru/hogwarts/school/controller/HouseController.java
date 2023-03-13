package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.HouseService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("faculty")
public class HouseController {
    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> createFaculty (@RequestBody @Valid FacultyDTO facultyDTO){
        Faculty createdFaculty = houseService.createFaculty(facultyDTO);
        FacultyDTO createdFacultyDTO = new FacultyDTO(
                createdFaculty.getId(),
                createdFaculty.getName(),
                createdFaculty.getColor());
        return ResponseEntity.ok(createdFacultyDTO);
    }

    @GetMapping("{Id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long Id) {
        Faculty faculty = houseService.getFacultyById(Id);
        FacultyDTO facultyDTO = FacultyDTO.fromFaculty(faculty);
        return ResponseEntity.ok(facultyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> updateFaculty(@PathVariable Long id , @RequestBody FacultyDTO facultyDTO){
        Faculty updateFaculty = houseService.updateFaculty(id, facultyDTO);
        FacultyDTO updateFacultyDTO = new FacultyDTO(
                updateFaculty.getId(),
                updateFaculty.getName(),
                updateFaculty.getColor()
        );
        return ResponseEntity.ok(updateFacultyDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id){
        houseService.deleteFaculty(id);
    }

    @GetMapping("/faculty/color")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@RequestParam String color) {
        Collection<Faculty> faculties = houseService.getByColor(color);
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/faculty/name")
    public ResponseEntity getFacultyByName(@RequestParam String name){
        Faculty faculty = houseService.findByName(name);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/faculties/{facultyId}/students")
    public ResponseEntity<Collection<StudentDTO>> getStudentsByFacultyId(@PathVariable Long facultyId) {
        Collection<StudentDTO> studentDTOs = houseService.getStudentsByFacultyId(facultyId);
        if (studentDTOs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(studentDTOs);
    }
}
