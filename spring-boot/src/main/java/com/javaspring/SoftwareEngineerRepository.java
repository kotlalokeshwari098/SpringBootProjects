package com.javaspring;

import org.springframework.data.jpa.repository.JpaRepository;
//extends JpaRepository<SoftwareEngineer, Integer>
//You’re telling Spring:
//The repository works with the entity type SoftwareEngineer.
//The primary key (ID) type of that entity is Integer.
public interface SoftwareEngineerRepository
        extends JpaRepository<SoftwareEngineer,Integer> {

}
