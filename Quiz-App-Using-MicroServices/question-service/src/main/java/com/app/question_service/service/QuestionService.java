package com.app.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.app.question_service.model.Question;
import com.app.question_service.model.QuestionWrapper;
import com.app.question_service.model.Response;
import com.app.question_service.repository.QuestionRepository;

@Service
public class QuestionService {
 
    @Autowired
    QuestionRepository questionRepository;

    public  ResponseEntity<List<Question>> getAllQuestion(){
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);  
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);    
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByDifficulty(String difficultyLevel){

        try{
            return new ResponseEntity<>(questionRepository.findByDifficultyLevel(difficultyLevel), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        
    }
    public ResponseEntity<String> addQuestion(Question quiz){        
        try{
            questionRepository.save(quiz);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<String> deleteById(Long id){
        
        try{
            questionRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.GONE);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        
    }

    public ResponseEntity<List<Integer>> getQuestionsFromQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Long> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Long id : questionIds){
            questions.add(questionRepository.findById(id).get());
        }
        for(Question question : questions){
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
        
    }

    public ResponseEntity<Integer> getScore(List<Response> responses){
        int correct = 0;
        for(Response response : responses){
            Question question = questionRepository.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())){
                correct++;
            }
            
        }
        return new ResponseEntity<>(correct, HttpStatus.OK);
    }

    
}
