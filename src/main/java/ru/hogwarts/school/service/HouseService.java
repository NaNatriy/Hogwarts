package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;

@Service
public class HouseService {
    private Map<Long, Faculty> facultys = new HashMap<>();
    private Long generatedValueId = 0L;
    public Collection<Faculty> getAllFaculty (){
        return facultys.values();
    }
    public Faculty createFaculty (Faculty faculty){
        faculty.setId(++generatedValueId);
        facultys.put(generatedValueId, faculty);
        return faculty;
    }
    public Faculty getFacultyById (Long facultyId){
        return facultys.get(facultyId);
    }
    public Faculty updateFaculty(Long id, Faculty faculty){
        if (facultys.containsKey(faculty.getId())) {
            facultys.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }
    public Faculty deleteFaculty(Long facultyId){
        return facultys.remove(facultyId);
    }

    public List<Faculty> getHousesByColor(String color) {
        List<Faculty> matchingHouses = new ArrayList<>();
        for (Faculty faculty : facultys.values()) {
            if (faculty.getColor().equalsIgnoreCase(color)) {
                matchingHouses.add(faculty);
            }
        }
        return matchingHouses;
    }
}
