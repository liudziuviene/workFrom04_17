package org.example.workfrom0417.services;

import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.entities.School;
import org.example.workfrom0417.entities.Student;
import org.example.workfrom0417.repositories.SchoolRepository;
import org.example.workfrom0417.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final StudentRepository studentRepository;

    public List<School> getAllSchools() {
        return this.schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return this.schoolRepository.findById(id).orElse(null);
    }

    public List<Student> getStudentsBySchoolId(Long schoolId) {
        return this.studentRepository.findBySchoolId(schoolId);
    }

    public Optional<Student> getStudentByIdAndSchoolId(Long schoolId, Long studentId) {
        return studentRepository.findByIdAndSchoolId(studentId, schoolId);
    }
}
