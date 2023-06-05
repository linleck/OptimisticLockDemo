package com.example.demo;

import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import com.example.demo.student.StudentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentService studentService;

	@Test
	void testConcurrentModification() throws InterruptedException {
		Student student = new Student(1,"Tanaka",21);
		studentService.createStudent(student);

		Student transactionA = studentRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("student not found"));

		Student transactionB = studentRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("student not found"));
		transactionA.setAge(student.getAge()+2);
		studentService.updateStudent(transactionA);

		assertThrows(OptimisticLockException.class, () -> {
			transactionB.setAge(student.getAge()+2);
			studentService.updateStudent(transactionB);
		});

	}

}
