package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.StudentDTO;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.repository.StudentRepository;
import com.example.schoolapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDTO create(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        Optional<Student> studentId = studentRepository.findById(id);

        return studentId.map(this::convertToDTO);
    }

    @Override
    public List<StudentDTO> findAll() {
        List<Student> studentsFindAll = studentRepository.findAll();

        return studentsFindAll
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getId());
        if (optionalStudent.isPresent()) {
            Student student = convertToEntity(studentDTO);
            Student updateStudent = studentRepository.save(student);
            return convertToDTO(updateStudent);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getAge(),
                student.getGrade()
        );
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return new Student(studentDTO.getId(),
                studentDTO.getFirstName(),
                studentDTO.getLastName(),
                studentDTO.getAge(),
                studentDTO.getGrade()
        );
    }
}
