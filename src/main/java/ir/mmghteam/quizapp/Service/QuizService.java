package ir.mmghteam.quizapp.Service;

import ir.mmghteam.quizapp.DAO.QuestionDAO;
import ir.mmghteam.quizapp.DAO.QuizDAO;
import ir.mmghteam.quizapp.Model.Question;
import ir.mmghteam.quizapp.Model.QuestionWrapper;
import ir.mmghteam.quizapp.Model.Quiz;
import ir.mmghteam.quizapp.Model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz =quizDAO.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q: questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(),
                                                     q.getQuestionTitle(),
                                                     q.getOption1(),
                                                     q.getOption2(),
                                                     q.getOption3(),
                                                     q.getOption4() );
            questionsForUser.add(qw);

        }


        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponse> quizResponses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for (QuizResponse quizResponse: quizResponses ){
            if (quizResponse.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
