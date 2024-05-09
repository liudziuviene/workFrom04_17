package org.example.workfrom0417.task1and2;

import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.task1and2.repositories.SchoolRepository;
import org.example.workfrom0417.task1and2.repositories.StudentRepository;
import org.example.workfrom0417.task1and2.entities.School;
import org.example.workfrom0417.task1and2.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public School addSchool(School school) {
        return this.schoolRepository.saveAndFlush(school);
    }

    public School addStudentBySchoolId(Student student, Long schoolId) {
        School school = this.schoolRepository.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School not found"));

        student.setSchool(school);
        school.getStudentList().add(student);
        return this.schoolRepository.saveAndFlush(school);
    }

    public School updateSchoolTitleById(Long schoolId, String newTitle) {
        School school = this.schoolRepository.findById(schoolId)
                .orElseThrow(() -> new NoSuchElementException("School not found"));

        school.setTitle(newTitle);
        return this.schoolRepository.saveAndFlush(school);
    }
}