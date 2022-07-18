package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.entity.Student;
import com.example.service.StudentService;
import com.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	
	
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		studentRepository = studentRepository;
	}



	@Override
	public List<Student> getAllStudents()
	{
		
		return studentRepository.findAll();		
	}



	@Override
	public Student saveStudent(Student student)
	{
		return studentRepository.save(student);
	}



	@Override
	public Student getStudentById(Long id) {
		
		return studentRepository.findById(id).get();
	}



	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}



	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}



	@Override
	public Page<Student> findPaginated(int pageNo, int pageSize) {
	 Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
	 return this.studentRepository.findAll(pageable);
	}
}
