package pro.sky.Kurs2.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Kurs2.Question;
import pro.sky.Kurs2.interfaces.ExaminerService;
import pro.sky.Kurs2.services.ExaminerServiceImpl;
import pro.sky.Kurs2.services.JavaQuestionService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    private final ExaminerServiceImpl examinerService;

    public ExamController(ExaminerServiceImpl examinerService) {
        this.examinerService = examinerService;
    }


    @GetMapping("/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return examinerService.getQuestions(amount);
    }
}
