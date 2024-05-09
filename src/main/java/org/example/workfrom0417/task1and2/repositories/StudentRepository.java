package org.example.workfrom0417.task1and2.repositories;

import org.example.workfrom0417.task1and2.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySchoolId(Long schoolId);

    Optional<Student> findByIdAndSchoolId(Long id, Long schoolId);

}
