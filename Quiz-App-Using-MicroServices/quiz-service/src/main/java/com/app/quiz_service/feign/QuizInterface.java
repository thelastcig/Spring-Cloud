package com.app.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.quiz_service.model.QuestionWrapper;
import com.app.quiz_service.model.Response;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    @PostMapping("/questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);

    @PostMapping("/questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
    
}
