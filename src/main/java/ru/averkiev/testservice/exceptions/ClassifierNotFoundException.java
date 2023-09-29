package ru.averkiev.testservice.exceptions;

/**
 * Исключение, выбрасываемое при неудачном поиске объекта Classifier.
 * @author mrGreenNV
 */
public class ClassifierNotFoundException extends RuntimeException {

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке.
     * @param message сообщение об ошибке.
     */
    public ClassifierNotFoundException(String message) {
        super(message);
    }

    /**
     * Создаёт экземпляр исключения с указанным сообщением об ошибке и причиной.
     * @param message сообщение об ошибке.
     * @param cause причина исключения.
     */
    public ClassifierNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
