package com.example.demostudent;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Student>  studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setName(email);
        }
    }
}
