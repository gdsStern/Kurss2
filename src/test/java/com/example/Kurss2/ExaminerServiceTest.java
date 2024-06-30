package pro.sky.Kurs2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Kurs2.exceptions.IncorrectAmountException;
import pro.sky.Kurs2.interfaces.QuestionService;
import pro.sky.Kurs2.services.ExaminerServiceImpl;
import pro.sky.Kurs2.services.JavaQuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceTest {
    @Mock
    private JavaQuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl  examinerService;

    private final List<Question> questionList = List.of(
            new Question("В1", "О1"),
            new Question("В2", "О2"),
            new Question("В3", "О3"),
            new Question("В4", "О4"),
            new Question("В5", "О5")
    );

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questionList);
    }

    @Test
    public void getQuestionsNegativeTest() {
        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(6));

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(() -> examinerService.getQuestions(-1));
    }

    @Test
    public void getQuestionsTest() {
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("В2", "О2"),
                new Question("В2", "О2"),
                new Question("В1", "О1"),
                new Question("В4", "О4"),
                new Question("В4", "О4"),
                new Question("В5", "О5")
        );

        assertThat(examinerService.getQuestions(4)).containsExactlyInAnyOrder(
                new Question("В1", "О1"),
                new Question("В2", "О2"),
                new Question("В4", "О4"),
                new Question("В5", "О5")
        );
        //1
    }
}
