package org.example.schoolsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can not be empty!")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @Positive(message = "Age must be a positive number!")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Major can not be empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;







}
