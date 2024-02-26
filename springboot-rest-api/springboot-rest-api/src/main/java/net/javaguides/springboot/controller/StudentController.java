package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class StudentController {


    //htttp://localhost:8080/student
    @GetMapping("student")
    public Student getStudent()
    {
        return new Student(1, "Siddhant", "Jaiswal");
    }

    //Making a REST API which will return a list of objects

    @GetMapping("students")
    public List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Siddhant", "Jaiswal"));
        students.add(new Student(2, "ABC", "XYZ"));
        students.add(new Student(3, "KLM", "NOP"));

        return  students;
    }

    //SpringBoot REST API with Path Variable
    //{id} - URI template variable
    //http://localhost:8080/student/1

    @GetMapping("students/{id}")
    public Student StudentPathVariable(@PathVariable("id") int studentID)
    {

        return new Student(studentID, "SIDDHANT ID ", "JAISWAL ID ");
    }
    //SpringBoot REST API with PathVariable with Multiple Columns

    @GetMapping("students/{id}/{firstName}/{lastName}")
    public Student studentpathVariableWithdifferentColumn(
       @PathVariable("id") int studentId,
       @PathVariable("firstName") String firstName,
       @PathVariable("lastName") String lastName)
    {

        return new Student(studentId, firstName, lastName);

    }
}
