package com.okta.developer.postgresql.dao;

import com.okta.developer.postgresql.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TeacherDAO extends CrudRepository<Teacher, UUID> {
}
