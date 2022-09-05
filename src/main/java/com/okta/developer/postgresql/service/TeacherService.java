package com.okta.developer.postgresql.service;

import com.okta.developer.postgresql.entities.Review;

import javax.validation.constraints.NotNull;


public interface TeacherService {

    /**
     *
     * @param teacherID
     * @param review
     * @throws javax.persistence.EntityNotFoundException
     */
    void addReview(@NotNull String teacherID, @NotNull Review review);
}
