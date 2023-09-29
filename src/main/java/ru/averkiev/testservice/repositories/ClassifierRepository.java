package ru.averkiev.testservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.averkiev.testservice.models.Classifier;

/**
 * Репозиторий для взаимодействия объектов класса Classifier с базой данных.
 * @author mrGreenNV
 */
@Repository
public interface ClassifierRepository extends JpaRepository<Classifier, Long> {
    boolean existsClassifierByType(String type);

    Classifier findClassifierByType(String classifierType);
}
