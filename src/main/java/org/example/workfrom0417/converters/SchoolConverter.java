package org.example.workfrom0417.converters;

import org.example.workfrom0417.dto.CreateSchoolDTO;
import org.example.workfrom0417.dto.SchoolDTO;
import org.example.workfrom0417.entities.School;

import java.util.ArrayList;
import java.util.List;

public class SchoolConverter {

    public static SchoolDTO convertEntityToDTO(School school) {
        SchoolDTO schoolDTO = null;
        if (school != null) {
            schoolDTO = new SchoolDTO();
            schoolDTO.setId(school.getId());
            schoolDTO.setTitle(school.getTitle());
            schoolDTO.setAddress(school.getAddress());
        }
        return schoolDTO;
    }

    public static List<SchoolDTO> convertEntityListToDTOList(List<School> schools) {

        List<SchoolDTO> schoolDTOList = null;
        if (schools != null && !schools.isEmpty()) {
            schoolDTOList = new ArrayList<>();
            for (School school : schools) {
                schoolDTOList.add(convertEntityToDTO(school));
            }
        }
        return schoolDTOList;
    }

    public static School convertCreateSchoolDTOToEntity(CreateSchoolDTO createSchoolDTO) {
        School school = null;
        if (createSchoolDTO != null) {
            school = new School();
            school.setTitle(createSchoolDTO.getTitle());
            school.setAddress(createSchoolDTO.getAddress());
            school.setNumberOfClasses(createSchoolDTO.getNumberOfClasses());
            school.setNumberOfStudents(createSchoolDTO.getNumberOfStudents());
            school.setNumberOfTeachers(createSchoolDTO.getNumberOfTeachers());
            school.setStudentList(new ArrayList<>());
        }
        return school;
    }
}
