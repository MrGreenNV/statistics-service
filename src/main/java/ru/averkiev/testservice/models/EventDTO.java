package ru.averkiev.testservice.models;

import lombok.Data;

import java.util.Date;

/**
 * DTO класса Event.
 * @author mrGreenNV
 */
@Data
public class EventDTO {

    /** Идентификатор события */
    private String id;

    /** Название события */
    private String eventName;

    /** Дата и время возникновения события */
    private Date happenedAt;

    /** Тип классификатора события */
    private String classifierType;
}
