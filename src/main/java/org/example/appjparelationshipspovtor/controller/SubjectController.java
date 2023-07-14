package org.example.appjparelationshipspovtor.controller;

import org.example.appjparelationshipspovtor.entity.Subject;
import org.example.appjparelationshipspovtor.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    // READ
    @GetMapping
    public List<Subject> getAllSubjects(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @GetMapping("{id}")
    public Subject getSubjectById(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if(optionalSubject.isPresent()){
             return optionalSubject.get();
        }
        return new Subject();
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        Subject subject1 = new Subject();
        subject1.setName(subject.getName());
        subjectRepository.save(subject1);
        return "Subject added";
    }

    @DeleteMapping("{id}")
    public String deleteSubjectById(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            subjectRepository.deleteById(id);
            return "Subject deleted";
        }return "Subject Not found";
    }
    @PutMapping("{id}")
    public String editSubjectById(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> editingSubject = subjectRepository.findById(id);
        if (editingSubject.isPresent()){
            Subject subject1 = editingSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Subject Name edited";
        }return "Subject not found";
    }


}
