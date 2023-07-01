package ir.mmghteam.quizapp.Service;

import ir.mmghteam.quizapp.DAO.QuestionDAO;
import ir.mmghteam.quizapp.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestion() {
        return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDAO.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "Question added successfully";
    }
    public String deleteQuestion(int id) {
        questionDAO.deleteById(id);
        return "Question deleted successfully";
    }

}
