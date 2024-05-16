package org.example.workfrom0417.task1and2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.workfrom0417.task1and2.dto.CreateSchoolDTO;
import org.example.workfrom0417.task1and2.dto.CreateStudentDTO;
import org.example.workfrom0417.task1and2.repositories.SchoolRepository;
import org.example.workfrom0417.task1and2.entities.School;
import org.example.workfrom0417.task1and2.entities.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public List<School> getAllSchools() {
        return this.schoolRepository.findAll();
    }

    public School getSchoolById(Long schoolId) {
        School school = this.schoolRepository.findById(schoolId).orElse(null);
        if (school == null) {
            log.warn("School with id {} not found", schoolId);
        }
        return school;
    }

    public List<Student> getStudentsBySchoolId(Long schoolId) {
        School school = getSchoolById(schoolId);

        if (school == null) {
            return null;
        }

        if (school.getStudents().isEmpty()) {
            log.trace("Student list is empty");
        }

        return school.getStudents();
    }

    public Student getStudentByIdAndSchoolId(Long schoolId, Long studentId) {
        School school = getSchoolById(schoolId);
        if (school == null) {
            return null;
        }

        Student result = school.getStudents().stream()
                .filter(student -> student.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (result == null) {
            log.warn("Student with id {} not found on school {}", studentId, schoolId);
        }
        return result;
    }

    public School addSchool(CreateSchoolDTO createSchoolDTO) {
        School school = new School();
        school.setTitle(createSchoolDTO.getTitle());
        school.setAddress(createSchoolDTO.getAddress());
        school.setNumberOfStudents(createSchoolDTO.getNumberOfStudents());
        school.setNumberOfTeachers(createSchoolDTO.getNumberOfTeachers());
        school.setNumberOfClasses(createSchoolDTO.getNumberOfClasses());
        school.setStudents(new ArrayList<>());
        return this.schoolRepository.saveAndFlush(school);
    }

    public School addStudentBySchoolId(CreateStudentDTO createStudentDTO, Long schoolId) {
        School school = getSchoolById(schoolId);
        if (school != null) {
            Student student = new Student();
            student.setName(createStudentDTO.getName());
            student.setSurname(createStudentDTO.getSurname());
            student.setEmail(createStudentDTO.getEmail());
            student.setPhone(createStudentDTO.getPhone());
            student.setAddress(createStudentDTO.getAddress());

            school.getStudents().add(student);
            return this.schoolRepository.saveAndFlush(school);
        }
        return null;
    }

    public School updateSchoolTitleById(Long schoolId, String newTitle) {
        School school = getSchoolById(schoolId);
        if (school == null) {
            return null;
        }

        school.setTitle(newTitle);
        return this.schoolRepository.saveAndFlush(school);
    }
}
