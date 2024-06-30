package pro.sky.Kurs2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.Kurs2.exceptions.QuestionAlreadyAddedException;
import pro.sky.Kurs2.exceptions.QuestionEmptyException;
import pro.sky.Kurs2.exceptions.QuestionNotFoundException;
import pro.sky.Kurs2.interfaces.QuestionService;
import pro.sky.Kurs2.services.JavaQuestionService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();
    private final List<Question> questionList = List.of(
            new Question("В1", "О1"),
            new Question("В2", "О2"),
            new Question("В3", "О3")
    );

    @BeforeEach
    void beforeEach() {
        questionList.forEach(questionService::add);
    }

    @AfterEach
    void afterEach() {
        new ArrayList<>(questionService.getAll()).forEach(questionService::remove);
    }

    @Test
    public void addTest() {
        int was = questionService.getAll().size();
        Question expected = new Question("B4", "O4");
        assertThat(questionService.getAll()).doesNotContain(expected);

        Question actual = questionService.add("B4", "O4");
        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);
    }

    @Test
    public void add2Test() {
        int was = questionService.getAll().size();
        Question expected = new Question("B4", "O4");
        assertThat(questionService.getAll()).doesNotContain(expected);

        Question actual = questionService.add(new Question("B4", "O4"));
        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);
    }

    @Test
    public void addNegativeTest() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("В1", "О1"));
    }

    @Test
    public void addNegative2Test() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(new Question( "В1", "О1")));
    }
    @Test
    public void removeTest() {
        int was = questionService.getAll().size();
        Question expected = new Question("В1", "О1");
        assertThat(questionService.getAll()).contains(expected);

        Question actual = questionService.remove(new Question( "В1", "О1"));
        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was - 1);
        assertThat(questionService.getAll()).doesNotContain(expected);
    }
    @Test
    public void removeNegative2Test() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question( "В4", "О4")));
    }

    @Test
    public void getAllTest() {
        assertThat(questionService.getAll()).containsExactlyInAnyOrderElementsOf(questionList);
    }

    @Test
    public void getRandomTest(){
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    @Test
    public void getRandomNegativeTest(){
        afterEach();
        assertThatExceptionOfType(QuestionEmptyException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }



}
