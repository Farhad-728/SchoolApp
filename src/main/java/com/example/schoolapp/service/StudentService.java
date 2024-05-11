package com.example.schoolapp.service;

import com.example.schoolapp.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDTO create(StudentDTO studentDTO);
    Optional<StudentDTO> findById(Long id);
    List<StudentDTO> findAll();
    StudentDTO update(StudentDTO studentDTO);
    void deleteById(Long id);
}
