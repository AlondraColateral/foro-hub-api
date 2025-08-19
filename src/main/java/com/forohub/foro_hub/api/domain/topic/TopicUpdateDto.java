package com.forohub.foro_hub.api.domain.topic;

import jakarta.validation.constraints.Size;

public record TopicUpdateDto(
        String title,
        String message,
        String author,
        String course) {
}
