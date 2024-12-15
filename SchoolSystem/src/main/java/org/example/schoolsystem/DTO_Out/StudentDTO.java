package org.example.schoolsystem.DTO_Out;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.schoolsystem.Model.Course;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class StudentDTO {


    private String name;


    private Integer age;


    private String major;


    private List<CourseDTO> courses;





}
