package pro.sky.Kurs2.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import pro.sky.Kurs2.Question;
import pro.sky.Kurs2.exceptions.QuestionAlreadyAddedException;
import pro.sky.Kurs2.exceptions.QuestionEmptyException;
import pro.sky.Kurs2.exceptions.QuestionNotFoundException;
import pro.sky.Kurs2.interfaces.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    Random random = new Random();
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    private void init() {
        add("1+1", "2");
        add("2+2", "4");
        add("2+3", "5");
        add("5+1", "6");
        add("5+2", "7");
        add("4+4", "8");
        add("4+5", "9");
        add("5+5", "10");
        add("10+1", "11");
        add("9+3", "12");
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyAddedException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)) {
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new QuestionEmptyException();
        }
        return questions.stream()
                .skip(random.nextInt(questions.size()))
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }
}
