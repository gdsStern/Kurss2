package pro.sky.Kurs2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class QuestionAlreadyAddedException extends RuntimeException{
}
