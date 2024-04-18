package org.example.workfrom0417.controllers;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.entities.School;
import org.example.workfrom0417.entities.Student;
import org.example.workfrom0417.services.SchoolService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return this.schoolService.getAllSchools();
    }

    @GetMapping(value = "/{id}")
    public School getSchoolById(@PathVariable Long id) {
        return this.schoolService.getSchoolById(id);
    }

    @GetMapping(value = "/{id}/students")
    public List<Student> getStudentsBySchoolId(@PathVariable Long id) {
        return this.schoolService.getStudentsBySchoolId(id);
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public Student getStudentByIdAndSchoolId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        Optional<Student> student = this.schoolService.getStudentByIdAndSchoolId(schoolId, studentId);
        return student.orElse(null);
    }
}