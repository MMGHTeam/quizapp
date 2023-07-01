package ir.mmghteam.quizapp.Controller;

import ir.mmghteam.quizapp.Model.Question;
import ir.mmghteam.quizapp.Model.QuestionWrapper;
import ir.mmghteam.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam Integer numQ,
                                             @RequestParam String title) {
        return quizService.craeteQuiz(category,numQ,title);
        //example: http://localhost:8080/quiz/create?category=Math&numQ=2&title=MathQuiz
    }
    @RequestMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
        //example: http://localhost:8080/quiz/get/1
    }
}
