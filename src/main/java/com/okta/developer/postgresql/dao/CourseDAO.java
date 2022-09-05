package com.okta.developer.postgresql.dao;

import com.okta.developer.postgresql.entities.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CourseDAO extends CrudRepository<Course, UUID> {
}
