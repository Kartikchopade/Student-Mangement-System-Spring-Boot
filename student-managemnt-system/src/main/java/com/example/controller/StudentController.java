package com.example.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import com.example.entity.Student;
import com.example.service.StudentService;

@Controller
public class StudentController 
{
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("students")
	public String listStudents(Model model)
	{
		//model.addAttribute("students",studentService.getAllStudents());		
		return findPaginated(1,model);
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model)
	{
		Student student=new Student();
		model.addAttribute("student",student);
		return "create_student";
		
	}
	
	@GetMapping("page/students/new")
	public String createStudent(Model model)
	{
		Student student=new Student();
		model.addAttribute("student",student);
		return "create_student";
		
	}
	
	
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student)
	{
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/student/edit/{id}")
	public String editStudentForm(@PathVariable Long id, Model model)
	{
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model)
	{
		//get student from database
		Student existingStudent=studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		//save updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";		
				
	}
	
	@GetMapping("/student/{id}")
	public String deleteStudent(@PathVariable Long id)
	{
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = 5;

	    Page <Student> page = studentService.findPaginated(pageNo, pageSize);
	    List <Student> listStudents = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("students", listStudents);
	    return "students";
	}
	
}

