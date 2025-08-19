package com.forohub.foro_hub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record TopicCreateDto(
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotBlank
        String author,
        @NotBlank
        String course) {
}