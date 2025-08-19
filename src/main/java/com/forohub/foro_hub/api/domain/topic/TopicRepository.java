package com.forohub.foro_hub.api.domain.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitleAndMessage(String title, String message);

    // Nuevo método para búsqueda por curso y año
    @Query("SELECT t FROM Topic t WHERE t.course = :course AND YEAR(t.creationDate) = :year")
    List<Topic> findByCourseAndCreationDateYear(String course, int year);
}
