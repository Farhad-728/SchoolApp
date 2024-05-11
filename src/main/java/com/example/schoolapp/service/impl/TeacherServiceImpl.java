package com.example.schoolapp.service.impl;

import com.example.schoolapp.dto.StudentDTO;
import com.example.schoolapp.dto.TeacherDTO;
import com.example.schoolapp.entity.Student;
import com.example.schoolapp.entity.Teacher;
import com.example.schoolapp.repository.TeacherRepository;
import com.example.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherDTO create(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    @Override
    public Optional<TeacherDTO> findById(Long id) {

        Optional<Teacher> teacherId = teacherRepository.findById(id);

        return teacherId.map(this::convertToDTO);
    }

    @Override
    public List<TeacherDTO> findAll() {
        List<Teacher> teachersFindAll = teacherRepository.findAll();

        return teachersFindAll
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherDTO.getId());
        if (optionalTeacher.isPresent()) {
            Teacher teacher = convertToEntity(teacherDTO);
            Teacher updateTeacher = teacherRepository.save(teacher);
            return convertToDTO(updateTeacher);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        return new TeacherDTO(teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSubject(),
                teacher.getAge()
        );
    }

    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        return new Teacher(teacherDTO.getId(),
                teacherDTO.getFirstName(),
                teacherDTO.getLastName(),
                teacherDTO.getAge(),
                teacherDTO.getSubject()
        );
    }
}
