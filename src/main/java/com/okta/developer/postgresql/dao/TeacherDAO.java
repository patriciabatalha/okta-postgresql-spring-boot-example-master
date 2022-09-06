package com.okta.developer.postgresql.dao;

import com.okta.developer.postgresql.entities.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TeacherDAO extends CrudRepository<Teacher, UUID> {
    @Query(value = "SELECT distinct t.* FROM teacher t, jsonb_array_elements(reviews) with ordinality arr(item_object, position) WHERE arr.item_object->>'author'=?1", nativeQuery = true)
    List<Teacher> findTeachersByAuthorReview(String author);
}
