package com.app.question_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Data

public class Question {
    //ALTER TABLE quiz MODIFY id BIGINT AUTO_INCREMENT;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; 

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
    private String category;


}
