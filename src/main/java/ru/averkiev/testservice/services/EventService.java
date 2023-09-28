package ru.averkiev.testservice.services;

import ru.averkiev.testservice.exceptions.ClassifierNotFoundException;
import ru.averkiev.testservice.exceptions.EventNotFoundException;
import ru.averkiev.testservice.models.EventCreateDTO;
import ru.averkiev.testservice.models.EventDTO;

import java.util.List;

/**
 * Контракт взаимодействия с событиями, включающий в себя создание, обновление, удаление и поиск событий.
 * @author mrGreenNV
 */
public interface EventService {

    /**
     * Создаёт новое событие.
     * @param eventCreateDTO DTO с данными создаваемого события.
     * @return DTO с данными созданного события.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске передаваемого классификатора.
     */
    EventDTO createEvent(EventCreateDTO eventCreateDTO) throws ClassifierNotFoundException;

    /**
     * Обновление данных события.
     * @param eventID идентификатор обновляемого события
     * @param updatedEvent обновленные данные
     * @return DTO с данными обновленного события
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора
     */
    EventDTO updateEvent(Long eventID, EventDTO updatedEvent) throws EventNotFoundException, ClassifierNotFoundException;

    /**
     * Возвращает событие по указанному идентификатору.
     * @param eventId идентификатор искомого события
     * @return DTO с данными искомого события
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    EventDTO showEvent(Long eventId) throws EventNotFoundException;

    /**
     * Возвращает список всех событий
     * @return список содержащий DTO с данными событий.
     */
    List<EventDTO> showAllEvents();

    /**
     * Возвращает список событий, тип которых соответствует заданному типу классификатора.
     * @param classifierType заданный тип классификатора
     * @return список событий, соответствующих заданному типу классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    List<EventDTO> showEventsByClassifierType(String classifierType) throws ClassifierNotFoundException;

    /**
     * Удаляет событие.
     * @param eventId идентификатор удаляемого события.
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    void deleteEvent(Long eventId) throws EventNotFoundException;

    /**
     * Выполняет "soft" удаления события.
     * @param eventId идентификатор события.
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    void softDeleteEvent(Long eventId) throws EventNotFoundException;

}
