package ru.averkiev.testservice.models;

import lombok.Data;

/**
 * DTO для создания класса Event.
 * @author mrGreenNV
 */
@Data
public class EventCreateDTO {

    /** Название события */
    private String eventName;
    
    /** Тип классификатора события */
    private String classifierType;
}
