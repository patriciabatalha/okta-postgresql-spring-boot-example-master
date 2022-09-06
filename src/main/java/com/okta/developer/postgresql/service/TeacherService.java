package com.okta.developer.postgresql.service;

import com.okta.developer.postgresql.entities.Review;
import com.okta.developer.postgresql.entities.Teacher;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface TeacherService {

    /**
     *
     * @param teacherID
     * @param review
     * @throws javax.persistence.EntityNotFoundException
     */
    Teacher addReview(@NotNull String teacherID, @NotNull Review review);

    List<Teacher> findTeachersByAuthorReview(String author);
}
