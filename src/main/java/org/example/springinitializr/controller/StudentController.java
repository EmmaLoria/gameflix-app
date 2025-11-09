package org.example.springinitializr.controller;

import org.example.springinitializr.model.Course;
import org.example.springinitializr.model.Student;
import org.example.springinitializr.service.CourseService;
import org.example.springinitializr.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    /** List all students */
    @GetMapping("/studentList")
    public String viewStudentPage(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());
        return "student_list";
    }

    /** Show empty form to create a new student */
    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // empty object to bind form fields
        Student student = new Student();
        // often you’ll need courses to populate a dropdown
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("student", student);
        model.addAttribute("allCourses", allCourses);
        return "new_student";
    }

    /** Persist a student (create) */
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    /** Example endpoint similar to the video’s @RequestMapping("/get/{sid}") */
    @GetMapping("/get/{sid}")
    public String getStudentId(@PathVariable("sid") Long sid, Model model) {
        Optional<Student> found = Optional.ofNullable(studentService.getStudentById(sid));
        model.addAttribute("title", "Data Student");
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("student", found.orElse(null));
        log.info("getStudentId :: {}", sid);
        return "redirect:/studentList";
    }

    /** Show update form populated with an existing student */
    @GetMapping("/showStudFormForUpdate/{sid}")
    public String showStudFormForUpdate(@PathVariable("sid") long sid, Model model) {
        Student student = studentService.getStudentById(sid);
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);
        model.addAttribute("student", student);
        return "update_student";
    }

    /** Delete a student and return to list */
    @GetMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable("sid") long sid) {
        studentService.deleteStudentById(sid);
        return "redirect:/studentList";
    }
}
