package org.example.workfrom0417.task1and2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.workfrom0417.task1and2.dto.CreateStudentDTO;
import org.example.workfrom0417.task1and2.dto.SchoolDTO;
import org.example.workfrom0417.task1and2.dto.UpdateSchoolTitleDTO;
import org.example.workfrom0417.task1and2.converters.SchoolConverter;
import org.example.workfrom0417.task1and2.converters.StudentConverter;
import org.example.workfrom0417.task1and2.dto.CreateSchoolDTO;
import org.example.workfrom0417.task1and2.entities.School;
import org.example.workfrom0417.task1and2.entities.Student;
import org.example.workfrom0417.task1and2.SchoolService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        log.info("Getting all schools");
        return this.schoolService.getAllSchools();
    }

    @GetMapping(value = "/{id}")
    public SchoolDTO getSchoolById(@PathVariable Long id) {
        log.debug("Getting school by ID {}", id);
        School school = this.schoolService.getSchoolById(id);
        if (school != null) {
            return SchoolConverter.convertEntityToDTO(school);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found");
        }
    }

    @GetMapping(value = "/{id}/students")
    public List<Student> getStudentsBySchoolId(@PathVariable Long id) {
        log.debug("Getting students by school ID {}", id);
        return this.schoolService.getStudentsBySchoolId(id);
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public Student getStudentByIdAndSchoolId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        log.debug("Getting student by school ID {} and student ID {}", schoolId, studentId);
        return this.schoolService.getStudentByIdAndSchoolId(schoolId, studentId).orElse(null);
    }

    @PostMapping("/schools")
    public SchoolDTO addSchool(@Valid @RequestBody CreateSchoolDTO createSchoolDTO) {
        log.info("Adding school {}", createSchoolDTO);
        return SchoolConverter.convertEntityToDTO(this.schoolService.addSchool(SchoolConverter.convertCreateSchoolDTOToEntity(createSchoolDTO)));
    }

    @PostMapping("/{schoolId}/students")
    public SchoolDTO addStudentBySchoolId(@PathVariable Long schoolId, @RequestBody CreateStudentDTO createStudentDTO) {
        log.info("Adding student by school ID {}", schoolId);
        return SchoolConverter.convertEntityToDTO(this.schoolService.addStudentBySchoolId(StudentConverter.convertCreateStudentDTOToEntity(createStudentDTO), schoolId));
    }

    @PatchMapping("/{schoolId}/title")
    public SchoolDTO updateSchoolTitleById(@PathVariable Long schoolId, @RequestBody UpdateSchoolTitleDTO updateSchoolTitleDTO) {
        log.info("Updating school title {} for school ID {}", updateSchoolTitleDTO, schoolId);
        return SchoolConverter.convertEntityToDTO(this.schoolService.updateSchoolTitleById(schoolId, updateSchoolTitleDTO.getTitle()));
    }
}