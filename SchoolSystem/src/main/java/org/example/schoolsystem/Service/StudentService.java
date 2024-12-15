package org.example.schoolsystem.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.schoolsystem.ApiResponse.ApiException;
import org.example.schoolsystem.DTO_Out.CourseDTO;
import org.example.schoolsystem.DTO_Out.StudentDTO;
import org.example.schoolsystem.Model.Course;
import org.example.schoolsystem.Model.Student;
import org.example.schoolsystem.Model.Teacher;
import org.example.schoolsystem.Repository.CourseRepository;
import org.example.schoolsystem.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;


    public List<StudentDTO> getAllStudents(){

        List<Student> students = studentRepository.findAll();

        List<StudentDTO> studentDTOList = new ArrayList<>();
        List<CourseDTO> courses = courseService.getAllCourses();

        for(Student s : students){

            StudentDTO studentDTO = new StudentDTO(s.getName(), s.getAge(), s.getMajor(), courses);

            studentDTOList.add(studentDTO);

        }
        return studentDTOList;
    }


    public void addStudent(StudentDTO studentDTO){

        Student student = new Student(null, studentDTO.getName(), studentDTO.getAge(), studentDTO.getMajor(), null );
        studentRepository.save(student);
    }


    //Assign

    public void assignCourseToStudent(Integer studentId, Integer courseId){

        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);

        if(student == null || course==null)
            throw new ApiException("Can't assign!");

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    public void updateStudent(Integer id, StudentDTO studentDTO){
        Student s = studentRepository.findStudentById(id);

        if(s ==null)
            throw new ApiException("Student not found!");

        s.setName(studentDTO.getName());
        s.setAge(studentDTO.getAge());
        s.setMajor(studentDTO.getMajor());

        studentRepository.save(s);
    }

    public void deleteStudents(Integer id){

        Student s = studentRepository.findStudentById(id);

        if(s ==null)
            throw new ApiException("Student not found!");

        studentRepository.delete(s);
    }

    public void changeMajor(Integer studentId, String major){

        Student student = studentRepository.findStudentById(studentId);


        if(student == null)
            throw new ApiException("Student not found !");

        student.setMajor(major);


        List<Course> coursesToRemove = new ArrayList<>(student.getCourses());

        for (Course course : coursesToRemove) {
            student.getCourses().remove(course);
            course.getStudents().remove(student);
            courseRepository.save(course);
        }

        studentRepository.save(student);

    }


    public List<StudentDTO>getStudentsByCourseId(Integer courseId){

        Course course = courseRepository.findCourseById(courseId);
        if (course == null) {
            throw new ApiException("Course not found!");
        }

        Set<Student> students = course.getStudents();


        //convert   to DTO
        List<StudentDTO> studentDTOList = new ArrayList<>();

        for (Student student : students) {

            List<CourseDTO> courseDTOList = new ArrayList<>();
            for (Course studentCourse : student.getCourses()) {
                courseDTOList.add(new CourseDTO(studentCourse.getName()));
            }

            StudentDTO studentDTO = new StudentDTO(
                    student.getName(),
                    student.getAge(),
                    student.getMajor(),
                    courseDTOList
            );
            studentDTOList.add(studentDTO);
        }
        return studentDTOList;



    }






}
