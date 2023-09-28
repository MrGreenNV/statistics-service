package ru.averkiev.testservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Базовый класс с общими полями для всех сущностей.
 * @author mrGreenNV
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

    /** Идентификатор сущности */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Время создания сущности. */
    @Column(name = "created_at")
    @CreationTimestamp
    @JsonIgnore
    private Date createdAt;

    /** Время обновления сущности. */
    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonIgnore
    private Date updatedAt;

    /** Статус сущности в системе. */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonIgnore
    private EntityStatus entityStatus = EntityStatus.ACTIVE;
}