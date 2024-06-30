package pro.sky.Kurs2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Kurs2.Question;
import pro.sky.Kurs2.interfaces.QuestionService;
import pro.sky.Kurs2.services.JavaQuestionService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService javaQuestionService;

    public JavaQuestionController(QuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer) {

        return javaQuestionService.add(question, answer);
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return javaQuestionService.getAll();
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer) {
        return javaQuestionService.remove(new Question(question, answer));
    }
}
