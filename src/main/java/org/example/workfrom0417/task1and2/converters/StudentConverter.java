package org.example.workfrom0417.task1and2.converters;

import org.example.workfrom0417.task1and2.dto.CreateStudentDTO;
import org.example.workfrom0417.task1and2.dto.StudentDTO;
import org.example.workfrom0417.task1and2.entities.Student;

import java.util.ArrayList;
import java.util.List;

public abstract class StudentConverter {

    public StudentConverter() {
    }

    public static StudentDTO convertEntityToDTO(Student student) {
        StudentDTO studentDTO = null;
        if (student != null) {
            studentDTO = new StudentDTO();
            studentDTO.setId(student.getId());
            studentDTO.setName(student.getName());
            studentDTO.setSurname(student.getSurname());
        }
        return studentDTO;
    }

    public static List<StudentDTO> convertEntityListToDTOList(List<Student> students) {

        List<StudentDTO> studentDTOList = null;
        if (students != null && !students.isEmpty()) {
            studentDTOList = new ArrayList<>();
            for (Student student : students) {
                studentDTOList.add(convertEntityToDTO(student));
            }
        }
        return studentDTOList;
    }

    public static Student convertCreateStudentDTOToEntity(CreateStudentDTO createStudentDTO) {
        Student student = null;
        if (createStudentDTO != null) {
            student = new Student();
            student.setName(createStudentDTO.getName());
            student.setSurname(createStudentDTO.getSurname());
            student.setEmail(createStudentDTO.getEmail());
            student.setPhone(createStudentDTO.getPhone());
            student.setAddress(createStudentDTO.getAddress());
        }
        return student;
    }
}
