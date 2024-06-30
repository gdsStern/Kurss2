package pro.sky.Kurs2.services;

import org.springframework.stereotype.Service;
import pro.sky.Kurs2.Question;
import pro.sky.Kurs2.exceptions.IncorrectAmountException;
import pro.sky.Kurs2.interfaces.ExaminerService;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (javaQuestionService.getAll().size()<amount || amount<=0) {
            throw new IncorrectAmountException();
        }
        Set<Question> questions = new HashSet<>();
        while (amount > questions.size()) {
                questions.add(javaQuestionService.getRandomQuestion());
        }
        return questions;
    }
}
