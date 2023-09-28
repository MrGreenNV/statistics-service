package ru.averkiev.testservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Класс описывает модель событий.
 * @author mrGreenNV
 */
@Entity
@Table(name = "events")
@Getter
@Setter
public class Event extends BaseEntity {

    /** Название события */
    @Column(name = "name")
    private String eventName;

    /** Дата и время возникновения события */
    @Column(name = "happened_at")
    private Date happenedAt;

    /** Тип классификатора события */
    @ManyToOne
    @JoinColumn(name = "classifier_id")
    private Classifier classifier;

}