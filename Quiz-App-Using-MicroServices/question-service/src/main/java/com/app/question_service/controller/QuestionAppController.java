package com.app.question_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.question_service.model.Question;
import com.app.question_service.model.QuestionWrapper;
import com.app.question_service.model.Response;
import com.app.question_service.service.QuestionService;
import java.util.List;

//mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8082" // to run on other instances
@RestController
@RequestMapping("/questions")
public class QuestionAppController {
    @Autowired
    QuestionService questionService;

    @Autowired
    org.springframework.core.env.Environment environment;


    @GetMapping("/allquestions")
    public ResponseEntity<List<Question>> getallQuestion(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/difficulty/{difficultyLevel}")
    public ResponseEntity<List<Question>> getQuestionByDifficulty(@PathVariable String difficultyLevel){
        return questionService.getQuestionByDifficulty(difficultyLevel);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question quiz){
        return questionService.addQuestion(quiz);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return questionService.deleteById(id);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsFromQuiz(categoryName, numQuestions);
    } 

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Long> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
