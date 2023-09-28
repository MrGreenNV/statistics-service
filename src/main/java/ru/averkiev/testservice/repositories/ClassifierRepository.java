package ru.averkiev.testservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.averkiev.testservice.models.Classifier;

/**
 * Репозиторий для взаимодействия объектов класса Classifier с базой данных.
 * @author mrGreenNV
 */
public interface ClassifierRepository extends JpaRepository<Classifier, Long> {
}
