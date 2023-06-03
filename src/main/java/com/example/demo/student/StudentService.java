package com.example.demo.student;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository productRepository) {
        this.studentRepository = productRepository;
    }

    @Transactional
    public Student createStudent(Student student){
        return studentRepository.save(student);
    }
    @Transactional
    public Student getUpdateStudent(Student updateStudent) {
        Student student = studentRepository.findById(updateStudent.getId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        if (updateStudent.getVersion() == student.getVersion()){
            student.setAge(updateStudent.getAge());
            student.setVersion(updateStudent.getVersion()+1);
            return createStudent(student);
        } else {
            throw new OptimisticLockException("Expected version ="+updateStudent.getVersion()+"but got"+student.getVersion()+". Concurrent modification detected");
        }
    }

}
