package com.college.studentportal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private StudentRepository studentRepository;

   @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/portal")
    public String explorePortal() {
        return "portal";
    }
    @GetMapping("/academics")
    public String viewAcademics(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("students", studentRepository.searchAcademics(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("students", studentRepository.findAll());
        }
        return "academics";
    }

   @GetMapping("/update-list")
    public String viewUpdateList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("students", studentRepository.searchAcademics(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("students", studentRepository.findAll());
        }
        return "update-list";
    }

    @GetMapping("/records")
    public String viewRecords(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "records";
    }
   @GetMapping("/students")
    public String viewStudents(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("students", studentRepository.searchAcademics(keyword));
        } else {
            model.addAttribute("students", studentRepository.findAll());
        }
        model.addAttribute("keyword", keyword);
        return "students";
    }

    @GetMapping("/add-student")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/save-student")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
           return "redirect:/students";  
    }
     @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student) {
        student.setId(id);
        studentRepository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }
@GetMapping("/profile")
    public String viewProfile(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("students", studentRepository.searchAcademics(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("students", studentRepository.findAll());
        }
        return "profile";
    }
}