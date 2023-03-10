package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.HousesRepository;

import java.util.*;

@Service
public class HouseService {
    private HousesRepository facultyRepository;
    public HouseService(HousesRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Collection<Faculty> getAllFaculty (){
        return facultyRepository.findAll();
    }
    public Faculty createFaculty (Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public Faculty getFacultyById (Long facultyId){
        return facultyRepository.findById(facultyId).get();
    }
    public Faculty updateFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }
    public void deleteFaculty(Long facultyId){
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> getByColor(String color) {
        return facultyRepository.findByColorIgnoreCase(color);
    }
}
