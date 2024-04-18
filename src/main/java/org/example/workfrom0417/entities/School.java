package org.example.workfrom0417.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Student> studentList = new ArrayList<>();
}