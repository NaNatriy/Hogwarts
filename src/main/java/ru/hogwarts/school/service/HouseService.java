package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.hogwarts.school.dto.FacultyDTO;
import ru.hogwarts.school.dto.StudentDTO;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.HousesRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class HouseService {
    private HousesRepository housesRepository;

    private StudentRepository studentRepository;

    public HouseService(HousesRepository housesRepository, StudentRepository studentRepository) {
        this.housesRepository = housesRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Faculty createFaculty (FacultyDTO facultyDTO){
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return housesRepository.save(faculty);
    }
    @Transactional
    public Faculty getFacultyById (Long facultyId){
        return housesRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
    }
    @Transactional
    public Faculty updateFaculty(Long id, FacultyDTO facultyDTO){
        Faculty faculty = housesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Faculty not found"));
        faculty.setName(facultyDTO.getName());
        faculty.setColor(facultyDTO.getColor());
        return housesRepository.save(faculty);
    }
    @Transactional
    public void deleteFaculty(Long facultyId){
        housesRepository.deleteById(facultyId);
    }
    @Transactional
    public Collection<Faculty> getByColor(String color) {
        return housesRepository.findByColorIgnoreCase(color);
    }
    @Transactional
    public Faculty findByName(String name){
        return housesRepository.findByNameIgnoreCase(name);
    }

    public Collection<StudentDTO> getStudentsByFacultyId(Long id) {
        Collection<Student> students = studentRepository.findByFacultyId(id);
        Collection<StudentDTO> studentDTOs = new ArrayList<>();
        for (Student student : students) {
            studentDTOs.add(new StudentDTO(student.getId(), student.getName(), student.getAge(), student.getFaculty().getId()));
        }
        return studentDTOs;
    }

}
