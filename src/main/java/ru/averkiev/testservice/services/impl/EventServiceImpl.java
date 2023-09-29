package ru.averkiev.testservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.averkiev.testservice.exceptions.ClassifierNotFoundException;
import ru.averkiev.testservice.exceptions.EventNotFoundException;
import ru.averkiev.testservice.models.*;
import ru.averkiev.testservice.repositories.EventRepository;
import ru.averkiev.testservice.services.EventService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса по взаимодействию с событиями.
 * @author mrGreenNV
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    /** Репозиторий для обращения к базе данных объектов Event */
    private final EventRepository eventRepository;

    /** Сервис для взаимодействия с объектами Classifier */
    private final ClassifierServiceImpl classifierService;

    /** Позволяет преобразовать классы моделей к DTO */
    private final ModelMapper modelMapper;

    /**
     * Создаёт новое событие.
     * @param eventCreateDTO DTO с данными создаваемого события.
     * @return DTO с данными созданного события.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске передаваемого классификатора.
     */
    @Override
    public EventDTO createEvent(EventCreateDTO eventCreateDTO) throws ClassifierNotFoundException {

        Classifier classifier = classifierService.getClassifierByType(eventCreateDTO.getClassifierType());

        Event event = new Event();
        event.setHappenedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        event.setEventName(eventCreateDTO.getEventName());
        event.setClassifier(classifier);

        event = eventRepository.save(event);

        log.info("IN createEvent - событие: {} успешно создано", event.getEventName());

        return modelMapper.map(event, EventDTO.class);

    }


    /**
     * Обновление данных события.
     * @param eventID идентификатор обновляемого события
     * @param updatedEvent обновленные данные
     * @return DTO с данными обновленного события
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public EventDTO updateEvent(Long eventID, EventDTO updatedEvent) throws EventNotFoundException, ClassifierNotFoundException {

        String updatedEventName = updatedEvent.getEventName();
        Classifier updatedClassifier = classifierService.getClassifierByType(updatedEvent.getClassifierType());

        Event event = getEventById(eventID);

        if (updatedEventName != null && !updatedEventName.equals("")) {
            event.setEventName(updatedEventName);
        }

        event.setClassifier(updatedClassifier);

        event = eventRepository.save(event);
        log.info("IN updateEvent - событие: {} успешно обновлено", updatedEventName);

        return modelMapper.map(event, EventDTO.class);
    }

    /**
     * Возвращает событие по указанному идентификатору.
     * @param eventId идентификатор искомого события
     * @return DTO с данными искомого события
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    @Override
    public EventDTO getEvent(Long eventId) throws EventNotFoundException {
        return modelMapper.map(getEventById(eventId), EventDTO.class);
    }

    /**
     * Возвращает список всех событий
     * @return список содержащий DTO с данными событий.
     */
    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAllByOrderByHappenedAtDesc().stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }


    /**
     * Возвращает список всех событий с пагинацией страниц
     * @param pageRequest пагинация
     * @return список содержащий DTO с данными событий.
     */
    public List<EventDTO> getAllEvents(PageRequest pageRequest) {
        return eventRepository.findAllByOrderByHappenedAtDesc(pageRequest).stream()
                .map(event -> modelMapper.map(event, EventDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список событий, тип которых соответствует заданному типу классификатора.
     * @param classifierType заданный тип классификатора
     * @return список событий, соответствующих заданному типу классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public List<EventDTO> getEventsByClassifierType(String classifierType) throws ClassifierNotFoundException {
        Classifier classifier = classifierService.getClassifierByType(classifierType);
        return getAllEvents().stream()
                .filter(eventDTO -> eventDTO.getClassifierType().equals(classifier.getType()))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список событий, тип которых соответствует заданному типу классификатора с пагинацией страниц.
     * @param classifierType заданный тип классификатора
     * @param pageRequest пагинация
     * @return список событий, соответствующих заданному типу классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    public List<EventDTO> getEventsByClassifierType(String classifierType, PageRequest pageRequest) throws ClassifierNotFoundException {
        Classifier classifier = classifierService.getClassifierByType(classifierType);
        return getAllEvents(pageRequest).stream()
                .filter(eventDTO -> eventDTO.getClassifierType().equals(classifier.getType()))
                .collect(Collectors.toList());
    }

    /**
     * Удаляет событие.
     * @param eventId идентификатор удаляемого события.
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    @Override
    public void deleteEvent(Long eventId) throws EventNotFoundException {
        Event event = getEventById(eventId);
        eventRepository.delete(event);
        log.info("IN deleteEvent - событие с идентификатором: {} успешно удалено", eventId);
    }

    /**
     * Выполняет "soft" удаления события.
     * @param eventId идентификатор события.
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    @Override
    public void softDeleteEvent(Long eventId) throws EventNotFoundException {

        Event event = getEventById(eventId);
        event.setEntityStatus(EntityStatus.DELETED);

        eventRepository.save(event);
        log.info("IN softDeleteEvent - событие с идентификатором: {} успешно помечено на удаление", eventId);
    }

    /**
     * Возвращает событие по переданному идентификатору.
     * @param eventID идентификатор искомого события.
     * @return найденное событие.
     * @throws EventNotFoundException выбрасывает, если возникает ошибка при поиске события по переданному идентификатору.
     */
    private Event getEventById(Long eventID) throws EventNotFoundException {
        Optional<Event> event = eventRepository.findById(eventID);

        if (event.isEmpty()) {
            log.error("IN getEventById - событие с идентификатором: {} не найдено", eventID);
            throw new EventNotFoundException("Событие с идентификатором: " + eventID + " не найдено");
        }

        log.info("IN getEventById - событие с идентификатором: {} успешно найдено", eventID);
        return event.get();
    }
}