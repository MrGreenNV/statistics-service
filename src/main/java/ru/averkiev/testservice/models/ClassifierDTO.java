package ru.averkiev.testservice.models;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO класса Classifier.
 * @author mrGreenNV
 */
@Getter
@Setter
public class ClassifierDTO {

    /** Тип классификатора */
    private String type;

    /** Описание типа классификатора */
    private String description;
}
