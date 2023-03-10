package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

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
    public ResponseEntity createFaculty (@RequestBody Faculty faculty){
        Faculty creatFaculty = houseService.createFaculty(faculty);
        return ResponseEntity.ok(creatFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = houseService.getFacultyById(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping()
    public ResponseEntity updateFaculty(@RequestParam Faculty faculty){
        Faculty updateFaculty = houseService.updateFaculty(faculty);
        return ResponseEntity.ok(updateFaculty);
    }

    @DeleteMapping
    public void deleteStudent(@PathVariable Long id){
        houseService.deleteFaculty(id);
    }

    @GetMapping("color")
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@RequestParam(required = false) String color) {
        Collection<Faculty> faculties = houseService.getByColor(color)
                .stream()
                .filter(f -> f.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        if (faculties.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(faculties);
    }
}
