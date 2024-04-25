package org.example.workfrom0417.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String birthDate;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
