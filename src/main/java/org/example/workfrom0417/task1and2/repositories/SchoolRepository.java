package org.example.workfrom0417.task1and2.repositories;

import org.example.workfrom0417.task1and2.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
