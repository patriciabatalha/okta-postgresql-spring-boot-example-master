package com.okta.developer.postgresql.service;

import com.okta.developer.postgresql.dao.TeacherDAO;
import com.okta.developer.postgresql.entities.Review;
import com.okta.developer.postgresql.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SimpleTeacherService implements TeacherService {
    private final TeacherDAO teacherDAO;

    @Autowired
    public SimpleTeacherService(TeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Teacher addReview(String teacherID, Review review) {
        Objects.requireNonNull(teacherID);
        Objects.requireNonNull(review);

        Teacher teacher = teacherDAO
                .findById(UUID.fromString(teacherID))
                .orElseThrow(() -> new EntityNotFoundException(teacherID));

        review.setDate(LocalDate.now());

        if(teacher.getReviews() == null)
            teacher.setReviews(new ArrayList<>());

        teacher.getReviews().add(review);

        return teacherDAO.save(teacher);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Teacher changeReview(String teacherID, Review review) {
        Teacher teacher = teacherDAO
                .findById(UUID.fromString(teacherID))
                .orElseThrow(() -> new EntityNotFoundException(teacherID));
        Review persistedReview = teacher.getReviews().stream().filter(r -> r.getAuthor().equalsIgnoreCase(review.getAuthor())).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author %s not found", review.getAuthor())));
        persistedReview.setReview(review.getReview());
        persistedReview.setDate(LocalDate.now());
        return teacherDAO.save(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return StreamSupport
                .stream(teacherDAO.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Teacher> findTeachersByAuthorReview(String author) {
        return teacherDAO.findTeachersByAuthorReview(author);
    }

    @Override
    public Teacher removeReviewByAuthor(String teacherID, String author) {
        Teacher teacher = teacherDAO
                .findById(UUID.fromString(teacherID))
                .orElseThrow(() -> new EntityNotFoundException(teacherID));
        Review review = teacher.getReviews().stream().filter(r -> r.getAuthor().equalsIgnoreCase(author)).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(String.format("Author %s not found", author)));
        teacher.getReviews().remove(review);
        return teacherDAO.save(teacher);
    }
}
