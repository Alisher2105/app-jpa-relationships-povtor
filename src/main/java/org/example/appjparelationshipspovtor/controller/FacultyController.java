package org.example.appjparelationshipspovtor.controller;

import org.example.appjparelationshipspovtor.entity.Address;
import org.example.appjparelationshipspovtor.entity.Faculty;
import org.example.appjparelationshipspovtor.entity.University;
import org.example.appjparelationshipspovtor.payload.FacultyDto;
import org.example.appjparelationshipspovtor.payload.UniversityDto;
import org.example.appjparelationshipspovtor.repository.AddressRepository;
import org.example.appjparelationshipspovtor.repository.FacultyRepository;
import org.example.appjparelationshipspovtor.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public List<Faculty> getFaculty(){
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList;
    }

    @GetMapping("{id}")
    public Faculty getFacultyById(@PathVariable Integer id){
        Optional<Faculty> byId = facultyRepository.findById(id);
        return byId.orElseGet(Faculty::new);
    }

    @DeleteMapping("{id}")
    public String deleteFacultyById(@PathVariable Integer id){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        }return "Faculty not found";
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody FacultyDto facultyDto){
        Optional<University> byId = universityRepository.findById(facultyDto.getUniversityId());
        if (byId.isPresent()) {
            Faculty faculty = new Faculty();
            faculty.setName(facultyDto.getFacultyName());
            faculty.setUniversity(byId.get());
            facultyRepository.save(faculty);
            return faculty;
        }return null;

    }

    @PutMapping("{id}")
    public String editFacultyById(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getFacultyName());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }return "Faculty not found";
    }

}
