package ru.averkiev.testservice.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.averkiev.testservice.utils.ErrorResponse;

/**
 * Класс отлавливает все исключения возникающие на уровне контроллера, для предоставления ошибки клиенту в виде JSON.
 * @author mrGreenNV
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Позволяет обработать исключения связанные с поиском классификатора в базе данных.
     * @param cnfEx ошибка при поиске классификатора.
     * @param request HTTP запрос.
     * @return ResponseEntity, содержащий информацию об ошибке.
     */
    @ExceptionHandler(ClassifierNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClassifierNotFoundException(ClassifierNotFoundException cnfEx, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND,
                cnfEx.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Позволяет обработать исключения связанные с поиском события в базе данных.
     * @param enfEx ошибка при поиске события.
     * @param request HTTP запрос.
     * @return ResponseEntity, содержащий информацию об ошибке.
     */
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(EventNotFoundException enfEx, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND,
                enfEx.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Позволяет обработать исключения связанные с дублированием типа классификатора.
     * @param taeEx ошибка при дублировании типа классификатора.
     * @param request HTTP запрос.
     * @return ResponseEntity, содержащий информацию об ошибке.
     */
    @ExceptionHandler(TypeClassifierAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleTypeClassifierAlreadyExistException(TypeClassifierAlreadyExistException taeEx, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                taeEx.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    /**
     * Позволяет обработать прочие исключения при взаимодействии с сервисом.
     * @param ex ошибка при взаимодействии с сервисом.
     * @param request HTTP запрос.
     * @return ResponseEntity, содержащий информацию об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_GATEWAY,
                ex.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
