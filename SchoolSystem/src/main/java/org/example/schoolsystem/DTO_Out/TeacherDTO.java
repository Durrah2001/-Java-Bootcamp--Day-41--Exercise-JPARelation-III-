package org.example.schoolsystem.DTO_Out;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeacherDTO {

    private String name;

    private Integer age;

    private List<CourseDTO> courses;





}
