package com.forohub.foro_hub.api.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; // ¡Añade esta importación!

import java.time.LocalDateTime;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@Setter // ¡Añade esta línea!
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime creationDate;
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    private String author; // ¡Asegúrate de que este campo exista!
    private String course; // ¡Asegúrate de que este campo exista!

    public Topic(String title, String message) {
        this.title = title;
        this.message = message;
        this.creationDate = LocalDateTime.now();
        this.status = TopicStatus.OPEN;
    }
}
