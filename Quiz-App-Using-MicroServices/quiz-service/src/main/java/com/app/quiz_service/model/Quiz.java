package com.app.quiz_service.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Quiz {
    
    //ALTER TABLE quiz MODIFY id BIGINT AUTO_INCREMENT;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  
    private String title;

    @ElementCollection
    private List<Integer> questionIds;

    
}
