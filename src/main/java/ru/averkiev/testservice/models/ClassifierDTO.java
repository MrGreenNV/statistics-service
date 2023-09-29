package ru.averkiev.testservice.models;

import lombok.Data;

/**
 * DTO класса Classifier.
 * @author mrGreenNV
 */
@Data
public class ClassifierDTO {

    /** Тип классификатора */
    private String type;

    /** Описание типа классификатора */
    private String description;
}
