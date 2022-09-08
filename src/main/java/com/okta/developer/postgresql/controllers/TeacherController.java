package com.okta.developer.postgresql.controllers;

import com.okta.developer.postgresql.entities.Review;
import com.okta.developer.postgresql.entities.Teacher;
import com.okta.developer.postgresql.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers/")
    public ResponseEntity<List<Teacher>> getTeachers() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/teachers/author/{author}")
    public ResponseEntity<List<Teacher>> getTeachersByAuthor(@PathVariable("author") String author) {
        try {
            List<Teacher> teachersByAuthorReview = teacherService.findTeachersByAuthorReview(author);
            return ResponseEntity.ok(teachersByAuthorReview);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/teachers/{id}/review")
    public ResponseEntity<Teacher> addReview(@RequestBody Review review, @PathVariable("id") String teacherID) {
        try {
            Teacher teacher = teacherService.addReview(teacherID, review);
            return ResponseEntity.ok(teacher);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/teachers/{id}/review")
    public ResponseEntity<Teacher> putReview(@RequestBody Review review, @PathVariable("id") String teacherID) {
        try {
            Teacher teacher = teacherService.changeReview(teacherID, review);
            return ResponseEntity.ok(teacher);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/teachers/{id}/review/author/{author}")
    public ResponseEntity removeReviewByAuthor(@PathVariable("id") String teacherID, @PathVariable("author") String author) {
        try {
            Teacher teacher = teacherService.removeReviewByAuthor(teacherID, author);
            return ResponseEntity.ok(teacher);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
