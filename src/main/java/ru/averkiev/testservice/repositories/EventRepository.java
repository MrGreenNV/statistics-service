package ru.averkiev.testservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.averkiev.testservice.models.Event;

/**
 * Репозиторий для взаимодействия объектов класса Event с базой данных.
 * @author mrGreenNV
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
