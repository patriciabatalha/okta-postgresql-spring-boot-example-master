ALTER TABLE teacher
  ADD COLUMN reviews jsonb;


CREATE INDEX idx_teacher_reviews_gin ON teacher USING GIN (reviews);