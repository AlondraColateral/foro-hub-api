package com.forohub.foro_hub.api.controller;

import com.forohub.foro_hub.api.domain.topic.Topic;
import com.forohub.foro_hub.api.domain.topic.TopicCreateDto;
import com.forohub.foro_hub.api.domain.topic.TopicRepository;
import com.forohub.foro_hub.api.domain.topic.TopicUpdateDto;
import com.forohub.foro_hub.api.service.TopicService; // Importar el servicio
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicService topicService; // Inyecta el servicio

    @PostMapping
    @Transactional
    public ResponseEntity<Topic> createTopic(@RequestBody @Valid TopicCreateDto data) {
        // Lógica de negocio en el servicio o aquí para simple validación
        if (topicRepository.findByTitleAndMessage(data.title(), data.message()).isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }

        Topic newTopic = new Topic(data.title(), data.message());
        topicRepository.save(newTopic);
        return ResponseEntity.ok(newTopic);
    }

    @GetMapping
    public ResponseEntity<Page<Topic>> findAllTopics(Pageable pagination) {
        Page<Topic> topics = topicRepository.findAll(pagination);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Topic>> searchTopicsByCourseAndYear(@RequestParam String course, @RequestParam int year) {
        List<Topic> topics = topicRepository.findByCourseAndCreationDateYear(course, year);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> findTopicDetails(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody @Valid TopicUpdateDto topicUpdateDto) {
        // Delega la lógica de negocio al servicio
        Topic topic = topicService.updateTopic(id, topicUpdateDto);
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        if (!topicRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        topicRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
