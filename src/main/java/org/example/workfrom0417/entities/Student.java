package org.example.workfrom0417.entities;

import jakarta.persistence.*;
import lombok.*;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
