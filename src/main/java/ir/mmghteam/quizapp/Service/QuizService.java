package ir.mmghteam.quizapp.Service;

import ir.mmghteam.quizapp.DAO.QuestionDAO;
import ir.mmghteam.quizapp.DAO.QuizDAO;
import ir.mmghteam.quizapp.Model.Question;
import ir.mmghteam.quizapp.Model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;


    public ResponseEntity<String> craeteQuiz(String category, Integer numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDAO.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
