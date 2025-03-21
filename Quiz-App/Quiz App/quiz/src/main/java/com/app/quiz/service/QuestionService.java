package com.app.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.quiz.model.Question;
import com.app.quiz.repository.QuestionRepository;

@Service
public class QuestionService {
 
    @Autowired
    QuestionRepository quizRepository;

    public  ResponseEntity<List<Question>> getAllQuestion(){
        try{
            return new ResponseEntity<>(quizRepository.findAll(), HttpStatus.OK);  
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);    
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try{
            return new ResponseEntity<>(quizRepository.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByDifficulty(String difficultyLevel){

        try{
            return new ResponseEntity<>(quizRepository.findByDifficultyLevel(difficultyLevel), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        
    }
    public ResponseEntity<String> addQuestion(Question quiz){        
        try{
            quizRepository.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteById(Long id){
        
        try{
            quizRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.GONE);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        
    }

    
}
