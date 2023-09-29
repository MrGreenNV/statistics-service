package ru.averkiev.testservice.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.averkiev.testservice.models.Event;

import java.util.List;

/**
 * Репозиторий для взаимодействия объектов класса Event с базой данных.
 * @author mrGreenNV
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Выполняет поиск всех событий в базе данных и сортирует их по дате в порядке убывания.
     * @return отсортированный список всех событий.
     */
    List<Event> findAllByOrderByHappenedAtDesc();

    /**
     * Выполняет поиск всех событий в базе данных и сортирует их по дате в порядке убывания с пагинацией страниц.
     * @return отсортированный список всех событий.
     */
    List<Event> findAllByOrderByHappenedAtDesc(PageRequest pageRequest);
}
