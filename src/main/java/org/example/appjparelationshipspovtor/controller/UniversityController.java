package org.example.appjparelationshipspovtor.controller;

import org.example.appjparelationshipspovtor.entity.Address;
import org.example.appjparelationshipspovtor.entity.University;
import org.example.appjparelationshipspovtor.payload.UniversityDto;
import org.example.appjparelationshipspovtor.repository.AddressRepository;
import org.example.appjparelationshipspovtor.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //READ

    @GetMapping
    public List<University> getUniversities(){
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    @GetMapping("{id}")
    public University getUniversityById(@PathVariable Integer id){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            return optionalUniversity.get();
        }
        return new University();
    }

    //CREATE
    @PostMapping
    public String addUniversity(@RequestBody UniversityDto universityDto){
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address savedAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);
        return "University added";
    }

    @DeleteMapping("{id}")
    public String deleteuniversity(@PathVariable Integer id){
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){
            universityRepository.deleteById(id);
            return "University deleted";
        }
        return "University not found";
    }

    @PutMapping("{id}")
    public String editStudentById(@PathVariable Integer id, @RequestBody UniversityDto universityDto){
        Optional<University> editingUniversity = universityRepository.findById(id);
        if (editingUniversity.isPresent()){
            University university = editingUniversity.get();
            Address address = university.getAddress();
            address.setStreet(universityDto.getStreet());
            address.setDistrict(universityDto.getDistrict());
            address.setCity(universityDto.getCity());
            Address editedAddress = addressRepository.save(address);

            university.setName(universityDto.getName());
            university.setAddress(editedAddress);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }
}
