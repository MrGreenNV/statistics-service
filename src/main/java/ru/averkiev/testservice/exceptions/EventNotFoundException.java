package ru.averkiev.testservice.exceptions;

/**
 * Исключение, выбрасываемое при неудачном поиске объекта Event.
 * @author mrGreenNV
 */
public class EventNotFoundException extends RuntimeException {

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке.
     * @param message сообщение об ошибке.
     */
    public EventNotFoundException(String message) {
        super(message);
    }

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке и причиной.
     * @param message сообщение об ошибке.
     * @param cause причина исключения.
     */
    public EventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
