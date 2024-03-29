package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("students")
public class StudentController {


    //htttp://localhost:8080/student
    @GetMapping("")
    public Student getStudent()
    {
        return new Student(1, "Siddhant", "Jaiswal");
    }

    //Making a REST API which will return a list of objects

    @GetMapping("/getall")
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

    @GetMapping("/{id}")
    public Student StudentPathVariable(@PathVariable("id") int studentID)
    {

        return new Student(studentID, "SIDDHANT ID ", "JAISWAL ID ");
    }
    //SpringBoot REST API with PathVariable with Multiple Columns

    @GetMapping("/{id}/{firstName}/{lastName}")
    public Student studentpathVariableWithdifferentColumn(@PathVariable("id") int studentId, @PathVariable("firstName") String firstName,
       @PathVariable("lastName") String lastName)
    {

        return new Student(studentId, firstName, lastName);

    }


    //Springboot REST API with Request Param
    //http://localhost:8080/students/query?id=1
    @GetMapping("/querychange")
    public Student studentRequestVariable(@RequestParam int id)
    {
        return new Student(id, "Siddu", "Jaiswal");
    }

    //Springboot REST API with Multiple Request Param
    //http://localhost:8080/students/query?id=1&firstName=Siddhu&lastName=Jaiswal
    @GetMapping("/query")
    public Student studentRequestVariableWithMultipleQuery(@RequestParam int id,
                                                           @RequestParam String firstName,
                                                           @RequestParam String lastName)

    {
        return new Student(id, firstName, lastName);
    }


    //SpringBoot REST API that handles HTTP POST Request
    //@Post Mapping and @RequestBody

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student)
    {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        student.setFirstName("NAME CHANGED");
        return  student;
    }

    //Spring Boot rest api that handles HTTP PUT request - updating existing resource
//    http://localhost:8080/students/1/update
    @PutMapping("/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId)
    {
            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            return  student;
    }

    //Spring Boot rest api that handles HTTP DELETE request - updating existing resource
//    http://localhost:8080/students/1/update
    @DeleteMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId)
    {
        return  "Student Deleted Successfully with Id "+studentId;
    }



    //Response Entity as a Response
    @GetMapping("studentResponse")
    public ResponseEntity<Student> getStudentasResponseEntity()
    {

        Student s1 =  new Student(1, "Siddhant", "Jaiswal");
        return  new ResponseEntity<>(s1,HttpStatus.OK);
    }



}
