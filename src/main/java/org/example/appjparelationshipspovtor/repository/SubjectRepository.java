package org.example.appjparelationshipspovtor.repository;

import org.example.appjparelationshipspovtor.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
