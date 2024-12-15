package org.example.schoolsystem.Service;

import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiException;
import org.example.schoolsystem.DTO_Out.CourseDTO;
import org.example.schoolsystem.Model.Course;
import org.example.schoolsystem.Model.Teacher;
import org.example.schoolsystem.Repository.CourseRepository;
import org.example.schoolsystem.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;


//    public List<Course> getAllCourses(){
//        return courseRepository.findAll();
//    }

    public List<CourseDTO> getAllCourses(){

        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> courseDTOList = new ArrayList<>();

        for(Course c  : courses){
            CourseDTO courseDTO = new CourseDTO(c.getName());
            courseDTOList.add(courseDTO);
        }

        return courseDTOList;
    }



    //Flow 1, when add a course assign it to teacher at same time

//    public void addCourse(Integer teacherId, Course course){
//
//        Teacher teacher = teacherRepository.findTeacherById(teacherId);
//
//        if(teacher == null) {
//            throw new ApiException("Teacher not found!");
//        }
//
//        course.setTeacher(teacher);
//        courseRepository.save(course);
//
//    }

    //Flow 2, add a course without assign

    public void addCourse(Course course){
        courseRepository.save(course);
    }

    //Assign endpoint

    public void assignCourseToTeacher(Integer teacherId, Integer courseId){

        //checks

        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course = courseRepository.findCourseById(courseId);

        if(teacher == null || course ==null){
            throw new ApiException("Can not assign course to teacher!");
        }

        course.setTeacher(teacher);
        courseRepository.save(course);

    }

    public void updateCourse(Integer id, Course course){

        Course c = courseRepository.findCourseById(id);

        if(c == null)
            throw new ApiException("Course not found!");

        c.setName(course.getName());
        courseRepository.save(c);
    }

    public void deleteCourse(Integer id){


        Course c = courseRepository.findCourseById(id);

        if(c== null)
            throw new ApiException("Course not found!");
        courseRepository.delete(c);

    }

    ///////////////////////

    public String getTeacherOfCourse(Integer courseId){

        Course course = courseRepository.findCourseById(courseId);
        if(course == null)
            throw new ApiException("Course not found!");

        List<Teacher> teachers = teacherRepository.findAll();

        //loop through teacher
        for (Teacher teacher : teachers) {
            if (teacher.getCourses().contains(course)) {
                return teacher.getName();
            }
        }

        throw new ApiException("Teacher for the specified course not found!");
    }







}
