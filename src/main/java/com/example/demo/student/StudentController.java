package com.example.demo.student;

import com.example.demo.Department.Department;
import com.example.demo.Department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    private final StudentService studentService;

    private final DepartmentService departmentService;

    @Autowired
    public StudentController(StudentService studentService, DepartmentService departmentService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping(path = "/register")
    public void registerStudent(@RequestBody Student student) {
        Optional<Department> department = departmentService.findById(student.getDepartment().getId());
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/student")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student.getId(), student.getName(), student.getEmail());
    }
}
