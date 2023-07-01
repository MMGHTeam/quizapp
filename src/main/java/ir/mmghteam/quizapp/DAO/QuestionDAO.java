package ir.mmghteam.quizapp.DAO;

import ir.mmghteam.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question,Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM question q " +
                    "Where q.category=:category " +
                    "Order By RAND() LIMIT :numQ", //Random() is in postgreSQL and RAND() is in MySQL
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, Integer numQ);
}
