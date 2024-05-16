package org.example.workfrom0417.task1and2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String address;
    private int numberOfStudents;
    private int numberOfTeachers;
    private int numberOfClasses;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "school_id")
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", numberOfStudents=" + numberOfStudents +
                ", numberOfTeachers=" + numberOfTeachers +
                ", numberOfClasses=" + numberOfClasses +
                ", students=" + students +
                '}';
    }
}
