package ru.averkiev.testservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс описывает модель классификатора.
 * @author mrGreenNV
 */
@Entity
@Table(name = "classifiers")
@Getter
@Setter
public class Classifier extends BaseEntity {

    /** Тип классификатора */
    @Column(name = "type")
    private String type;

    /** Описание типа классификатора */
    @Column(name = "description")
    private String description;

}