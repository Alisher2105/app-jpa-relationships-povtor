package org.example.appjparelationshipspovtor.repository;

import org.example.appjparelationshipspovtor.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Integer> {
}
