package com.example.schoolapp.service;

import com.example.schoolapp.dto.TeacherDTO;
import com.example.schoolapp.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    TeacherDTO create(TeacherDTO teacherDTO);
    Optional<TeacherDTO> findById(Long id);
    List<TeacherDTO> findAll();
    TeacherDTO update(TeacherDTO teacherDTO);
    void deleteById(Long id);
}
