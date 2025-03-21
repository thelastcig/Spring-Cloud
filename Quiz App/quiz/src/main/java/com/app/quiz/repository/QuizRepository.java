package com.app.quiz.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quiz.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>{
    
}
