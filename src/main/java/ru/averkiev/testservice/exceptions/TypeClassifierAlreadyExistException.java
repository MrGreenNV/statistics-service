package ru.averkiev.testservice.exceptions;

/**
 * Исключение, выбрасываемое если такой тип классификатора уже существует.
 * @author mrGreenNV
 */
public class TypeClassifierAlreadyExistException extends RuntimeException {

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке.
     * @param message сообщение об ошибке.
     */
    public TypeClassifierAlreadyExistException(String message) {
        super(message);
    }

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке и причиной.
     * @param message сообщение об ошибке.
     * @param cause причина исключения.
     */
    public TypeClassifierAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
