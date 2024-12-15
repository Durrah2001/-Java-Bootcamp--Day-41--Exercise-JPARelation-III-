package org.example.schoolsystem.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiResponse;
import org.example.schoolsystem.DTO_Out.CourseDTO;
import org.example.schoolsystem.Model.Course;
import org.example.schoolsystem.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/school-system/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getAllCourses(){

        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.status(200).body(courses);
    }

    //Flow 1, when add a course assign it to teacher at same time

//    @PostMapping("/add/{teacherId}")
//    public ResponseEntity<ApiResponse> addCourse(@PathVariable Integer teacherId, @RequestBody @Valid Course course){
//
//        courseService.addCourse(teacherId, course);
//        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully!"));
//
//    }

    //Flow 2, add a course without assign

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody @Valid Course course){

        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course added successfully!"));

    }

    @PutMapping("/assign-course/{teacherId}/{courseId}")
    public ResponseEntity assignCourseToTeacher(@PathVariable Integer teacherId, @PathVariable Integer courseId){

        courseService.assignCourseToTeacher(teacherId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Course assigned to a teacher successfully!"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable Integer id, @RequestBody @Valid Course course){

        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully!"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable Integer id){
        courseService.deleteCourse(id);

        return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully!"));

    }

    ////////////////

    @GetMapping("/get-teacher-by-course/{courseId}")
    public ResponseEntity getTeacherOfCourse(@PathVariable Integer courseId){
       String name =  courseService.getTeacherOfCourse(courseId);
        return ResponseEntity.status(200).body(new ApiResponse(name));

    }







}
