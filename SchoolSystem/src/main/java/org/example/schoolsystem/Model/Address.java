package org.example.schoolsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {

    @Id
    private Integer id;

    @Column(columnDefinition = "varchar(20) not null")
    private String street;

    @Column(columnDefinition = "int")
    private Integer buildingNumber;

    ///////////

    @OneToOne
    @MapsId // will take id from teacher
    @JsonIgnore
    private Teacher teacher;


}
