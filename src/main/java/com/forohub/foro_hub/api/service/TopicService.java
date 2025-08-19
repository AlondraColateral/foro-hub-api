package com.forohub.foro_hub.api.service;

import com.forohub.foro_hub.api.domain.topic.Topic;
import com.forohub.foro_hub.api.domain.topic.TopicRepository;
import com.forohub.foro_hub.api.domain.topic.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public Topic updateTopic(Long id, TopicUpdateDto topicUpdateDto) {
        // Busca el tópico por ID
        Topic topicToUpdate = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid topic ID"));

        // Aplica los cambios si los campos no son nulos
        if (topicUpdateDto.title() != null) {
            topicToUpdate.setTitle(topicUpdateDto.title());
        }
        if (topicUpdateDto.message() != null) {
            topicToUpdate.setMessage(topicUpdateDto.message());
        }
        if (topicUpdateDto.author() != null) {
            topicToUpdate.setAuthor(topicUpdateDto.author());
        }
        if (topicUpdateDto.course() != null) {
            topicToUpdate.setCourse(topicUpdateDto.course());
        }


        return topicToUpdate;
    }
}
