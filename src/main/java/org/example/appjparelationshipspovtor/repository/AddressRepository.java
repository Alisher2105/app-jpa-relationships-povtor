package org.example.appjparelationshipspovtor.repository;

import org.example.appjparelationshipspovtor.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
