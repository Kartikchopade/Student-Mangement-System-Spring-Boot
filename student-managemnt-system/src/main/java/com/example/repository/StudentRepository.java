package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>
{
	
}
