package org.example.workfrom0417.controllers;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.example.workfrom0417.converters.SchoolConverter;
import org.example.workfrom0417.converters.StudentConverter;
import org.example.workfrom0417.dto.CreateSchoolDTO;
import org.example.workfrom0417.dto.CreateStudentDTO;
import org.example.workfrom0417.dto.SchoolDTO;
import org.example.workfrom0417.dto.UpdateSchoolTitleDTO;
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

    @PostMapping
    public SchoolDTO addSchool(@RequestBody CreateSchoolDTO createSchoolDTO) {
        return SchoolConverter.convertEntityToDTO(this.schoolService.addSchool(SchoolConverter.convertCreateSchoolDTOToEntity(createSchoolDTO)));
    }

    @PostMapping("/{schoolId}/students")
    public SchoolDTO addStudentBySchoolId(@PathVariable Long schoolId, @RequestBody CreateStudentDTO createStudentDTO) {
        Student student = StudentConverter.convertCreateStudentDTOToEntity(createStudentDTO);
        School school = this.schoolService.addStudentBySchoolId(student, schoolId);
        return SchoolConverter.convertEntityToDTO(school);
    }

    @PatchMapping("/{schoolId}/title")
    public SchoolDTO updateSchoolTitleById(@PathVariable Long schoolId, @RequestBody UpdateSchoolTitleDTO updateSchoolTitleDTO) {
        School updatedSchool = this.schoolService.updateSchoolTitleById(schoolId, updateSchoolTitleDTO.getTitle());
        return SchoolConverter.convertEntityToDTO(updatedSchool);
    }
}