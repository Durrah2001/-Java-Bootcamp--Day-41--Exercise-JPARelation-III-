package org.example.schoolsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name can not be empty!")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotEmpty(message = "Email can not be empty!")
    @Email(message = "Email must be at valid format!")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    @Positive(message = "Age can not be negative number!")
    @Min(22)
    @Column(columnDefinition = "int")
    private Integer age;

    @Positive(message = "Salary can not be negative number!")
    @Column(columnDefinition = "DOUBLE")
    private Double salary;

    //////////

    @OneToOne
    @PrimaryKeyJoinColumn
    private Address address;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private Set<Course> courses;




}
