package org.example.appjparelationshipspovtor.controller;

import org.example.appjparelationshipspovtor.entity.Address;
import org.example.appjparelationshipspovtor.entity.Faculty;
import org.example.appjparelationshipspovtor.entity.Group;
import org.example.appjparelationshipspovtor.entity.University;
import org.example.appjparelationshipspovtor.payload.FacultyDto;
import org.example.appjparelationshipspovtor.payload.GroupDto;
import org.example.appjparelationshipspovtor.repository.AddressRepository;
import org.example.appjparelationshipspovtor.repository.FacultyRepository;
import org.example.appjparelationshipspovtor.repository.GroupRepository;
import org.example.appjparelationshipspovtor.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public List<Group> getGroup() {
        return groupRepository.findAll();
    }

    @GetMapping("{id}")
    public Group getGroupById(@PathVariable Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        return byId.orElseGet(Group::new);
    }

    @DeleteMapping("{id}")
    public String deleteGroupById(@PathVariable Integer id) {
        Optional<Group> byId = groupRepository.findById(id);
        if (byId.isPresent()) {
            groupRepository.deleteById(id);
        }
        return "Group not found";
    }

    @PostMapping
    public Group addGroup(@RequestBody GroupDto groupDto) {

        Optional<Faculty> faculty = facultyRepository.findById(groupDto.getFacultyId());
        if (faculty.isPresent()) {
            Faculty faculty1 = faculty.get();
            Group group = new Group();
            group.setName(groupDto.getGroupName());
            group.setFaculty(faculty1);
            return groupRepository.save(group);
        }
        return null;


    }
}
