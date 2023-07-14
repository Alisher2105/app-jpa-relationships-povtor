package org.example.appjparelationshipspovtor.repository;

import org.example.appjparelationshipspovtor.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {
}
