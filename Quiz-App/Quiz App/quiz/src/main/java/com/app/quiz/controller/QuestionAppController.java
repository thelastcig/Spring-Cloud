package com.app.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quiz.model.Question;
import com.app.quiz.service.QuestionService;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionAppController {
    @Autowired
    QuestionService quizService;


    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getallQuestion(){
        return quizService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return quizService.getQuestionByCategory(category);
    }

    @GetMapping("/difficulty/{difficultyLevel}")
    public ResponseEntity<List<Question>> getQuestionByDifficulty(@PathVariable String difficultyLevel){
        return quizService.getQuestionByDifficulty(difficultyLevel);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question quiz){
        return quizService.addQuestion(quiz);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return quizService.deleteById(id);
    }
}
