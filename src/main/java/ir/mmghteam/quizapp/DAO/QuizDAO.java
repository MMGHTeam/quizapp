package ir.mmghteam.quizapp.DAO;

import ir.mmghteam.quizapp.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
