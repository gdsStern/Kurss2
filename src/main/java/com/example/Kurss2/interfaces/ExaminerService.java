package pro.sky.Kurs2.interfaces;

import pro.sky.Kurs2.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
