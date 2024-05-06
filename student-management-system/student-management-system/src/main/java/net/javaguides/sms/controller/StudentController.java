package net.javaguides.sms.controller;

import java.util.*;
import net.javaguides.sms.dto.StudentDto;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    //Handler method to handle list student request

    private StudentService studentService;

    public StudentController (StudentService studentService)
    {
        this.studentService = studentService;
    }
    //handler method to handle list students request
    @GetMapping("/students")
    public String listStudents(Model model)
    {
        List<StudentDto>  students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";

    }

}
