package org.example.schoolsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiResponse;
import org.example.schoolsystem.DTO_Out.StudentDTO;
import org.example.schoolsystem.Model.Student;
import org.example.schoolsystem.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-system/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getAllStudents(){
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.status(200).body(students);
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid StudentDTO studentDTO){
        studentService.addStudent(studentDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Student added successfully!"));

    }

    @PutMapping("/assign/{studentId}/{courseId}")
    public ResponseEntity assignCourseToStudent(@PathVariable Integer studentId, @PathVariable Integer courseId){
        studentService.assignCourseToStudent(studentId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Course assigned to student!"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id,@RequestBody @Valid StudentDTO student ){
        studentService.updateStudent(id, student);

        return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully!"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudents(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully!"));

    }


    @PutMapping("/change-major/{studentId}/{major}")
    public ResponseEntity changeMajor(@PathVariable Integer studentId, @PathVariable String major){
        studentService.changeMajor(studentId, major);
        return ResponseEntity.status(200).body(new ApiResponse("Major changed and all the course that the student attended drop successfully!"));
    }


    @GetMapping("/student-byCourse/{courseId}")
    public ResponseEntity getStudentsByCourse(@PathVariable Integer courseId) {
        List<StudentDTO> students = studentService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(students);
    }








}
