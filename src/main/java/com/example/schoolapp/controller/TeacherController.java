package com.example.schoolapp.controller;

import com.example.schoolapp.dto.TeacherDTO;
import com.example.schoolapp.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping(value = "/create")
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacher) {
        return ResponseEntity.ok(teacherService.create(teacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/all")
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Long id, @RequestBody TeacherDTO teacherDetails) {
        TeacherDTO updateTeacher = teacherService.findById(id).get();

        return ResponseEntity.ok(teacherService.update(updateTeacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
