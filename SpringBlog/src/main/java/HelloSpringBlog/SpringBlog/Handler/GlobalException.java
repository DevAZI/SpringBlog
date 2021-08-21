package HelloSpringBlog.SpringBlog.Handler;

import HelloSpringBlog.SpringBlog.dto.ResponceDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalException {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponceDto<String> handleArgumentException(IllegalArgumentException e) {
        return new ResponceDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
