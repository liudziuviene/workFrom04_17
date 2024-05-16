package org.example.workfrom0417.task1and2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.workfrom0417.task1and2.dto.*;
import org.example.workfrom0417.task1and2.converters.SchoolConverter;
import org.example.workfrom0417.task1and2.converters.StudentConverter;
import org.example.workfrom0417.task1and2.entities.School;
import org.example.workfrom0417.task1and2.entities.Student;
import org.example.workfrom0417.task1and2.SchoolService;

import org.example.workfrom0417.task1and2.exceptions.NotFoundErrorResponse;
import org.example.workfrom0417.task1and2.exceptions.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT', 'GUEST')")
    @GetMapping
    public ResponseEntity<?> getAllSchools() {
        log.debug("Getting all schools");
        List<School> schools = this.schoolService.getAllSchools();
        if (schools.isEmpty()) {
            log.info("No schools found");
            NotFoundErrorResponse response = new NotFoundErrorResponse();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        log.info("Found {} schools", schools.size());
        ResponseEntity<List<SchoolDTO>> response = ResponseEntity.status(HttpStatus.OK)
                .body(SchoolConverter.convertEntityListToDTOList(schools));
        log.debug("Response: {}", response);
        return response;
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping(value = "/{schoolId}")
    public ResponseEntity<?> getSchoolById(@PathVariable Long schoolId) {
        log.debug("Getting school by ID {}", schoolId);
        School school = this.schoolService.getSchoolById(schoolId);
        if (school == null) {
            log.info("No school found with ID {}", schoolId);
            NotFoundErrorResponse response = new NotFoundErrorResponse("school", "ID", schoolId.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        log.info("Found school with ID {}", schoolId);
        ResponseEntity<SchoolDTO> response = ResponseEntity.status(HttpStatus.OK)
                .body(SchoolConverter.convertEntityToDTO(school));
        log.debug("Response: {}", response);
        return response;
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'STUDENT')")
    @GetMapping(value = "/{schoolId}/students")
    public ResponseEntity<?> getStudentsBySchoolId(@PathVariable Long schoolId) {
        log.debug("Getting students by school ID {}", schoolId);
        List<Student> students = this.schoolService.getStudentsBySchoolId(schoolId);
        if (students.isEmpty()) {
            log.info("No students found");
            NotFoundErrorResponse response = new NotFoundErrorResponse("students", "schoolId", schoolId.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        log.info("Found {} students", students.size());
        ResponseEntity<List<StudentDTO>> response = ResponseEntity.status(HttpStatus.OK)
                .body(StudentConverter.convertEntityListToDTOList(students));
        log.debug("Response: {}", response);
        return response;
    }

    @GetMapping(value = "/{schoolId}/students/{studentId}")
    public ResponseEntity<?> getStudentByIdAndSchoolId(@PathVariable Long schoolId, @PathVariable Long studentId) {
        log.debug("Getting student by school ID {} and student ID {}", schoolId, studentId);
        Student student = this.schoolService.getStudentByIdAndSchoolId(schoolId, studentId);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundErrorResponse("school or student", "schoolId|studentId", schoolId.toString() + "|" + studentId.toString()));
        }
        log.info("Found student with ID {} by school ID {}", studentId, schoolId);
        ResponseEntity<StudentDTO> response = ResponseEntity.status(HttpStatus.OK)
                .body(StudentConverter.convertEntityToDTO(student));
        log.debug("Response: {}", response);
        return response;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public ResponseEntity<?> addSchool(@Valid @RequestBody CreateSchoolDTO createSchoolDTO, BindingResult bindingResult) {
        log.info("Adding school {}", createSchoolDTO);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ValidationErrorResponse(bindingResult.getFieldErrors()));
        }
        School school = this.schoolService.addSchool(createSchoolDTO);
        ResponseEntity<?> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(SchoolConverter.convertEntityToDTO(school));
        log.debug("Response: {}", response);
        return response;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/{schoolId}/students")
    public ResponseEntity<?> addStudentBySchoolId(@Valid @PathVariable Long schoolId, @RequestBody CreateStudentDTO createStudentDTO, BindingResult bindingResult) {
        log.debug("Adding student by school ID: {}, {}", schoolId, createStudentDTO);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ValidationErrorResponse(bindingResult.getFieldErrors()));
        }
        School school = this.schoolService.addStudentBySchoolId(createStudentDTO, schoolId);
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundErrorResponse("school", "ID", schoolId.toString()));
        }

        ResponseEntity<?> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(SchoolConverter.convertEntityToDTO(school));
        log.debug("Response: {}", response);
        return response;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PatchMapping("/{schoolId}/title")
    public ResponseEntity<?> updateSchoolTitleById(@PathVariable Long schoolId, @RequestBody UpdateSchoolTitleDTO updateSchoolTitleDTO, BindingResult bindingResult) {
        log.info("Updating school title {} for school ID {}", updateSchoolTitleDTO, schoolId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ValidationErrorResponse(bindingResult.getFieldErrors()));
        }
        School school = this.schoolService.updateSchoolTitleById(schoolId, updateSchoolTitleDTO.getTitle());
        if (school == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new NotFoundErrorResponse("school", "ID", schoolId.toString()));
        }
        ResponseEntity<?> response = ResponseEntity.status(HttpStatus.OK)
                .body(SchoolConverter.convertEntityToDTO(school));
        log.debug("Response: {}", response);
        return response;
    }
}