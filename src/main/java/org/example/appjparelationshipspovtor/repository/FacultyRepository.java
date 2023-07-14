package org.example.appjparelationshipspovtor.repository;

import org.example.appjparelationshipspovtor.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
}
