package ru.averkiev.testservice.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.averkiev.testservice.models.EventCreateDTO;
import ru.averkiev.testservice.models.EventDTO;
import ru.averkiev.testservice.services.impl.EventServiceImpl;

import java.util.List;

/**
 * Класс представляет собой REST-контроллер для управления событиями.
 * @author mrGreenNV
 */
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventsController {

    /** Сервис для взаимодействия с событиями */
    private final EventServiceImpl eventService;

    /**
     * API-endpoint для создания события по классификатору.
     * @param eventCreateDTO DTO с данными создаваемого события.
     * @return DTO созданного события.
     */
    @PostMapping()
    public ResponseEntity<EventDTO> addEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.createEvent(eventCreateDTO));
    }

    /**
     * API-endpoint для редактирования сохранённого события.
     * @param eventId идентификатор редактируемого события.
     * @param updateEventDto DTO события с новыми данными.
     * @return DTO отредактированного события.
     */
    @PostMapping("/{eventId}")
    public ResponseEntity<EventDTO> editEvent(@PathVariable Long eventId, @RequestBody EventDTO updateEventDto) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.updateEvent(eventId, updateEventDto));
    }


    /**
     * API-endpoint для просмотра конкретного события.
     * @param eventId идентификатор события.
     * @return DTO найденного события.
     */
    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> showEvent(@PathVariable Long eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvent(eventId));
    }

    /**
     * API-endpoint для просмотра всех событий с возможностью фильтрации по классификатору.
     * @return список с DTO событий.
     */
    @GetMapping
    public ResponseEntity<List<EventDTO>> showAllEvents(
            @RequestParam(value = "filter", required = false) String typeClassifier,
            @Parameter(name = "page", description = "Номер отображаемой страницы") @RequestParam(value = "page", required = false) Integer page,
            @Parameter(name = "pageSize", description = "Количество отображаемых элементов на странице") @RequestParam(value = "pageSize", required = false) Integer pageSize

    ) {
        if (page == null || pageSize == null) {
            if (typeClassifier == null) {
                return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventsByClassifierType(typeClassifier));
            }
        } else if (typeClassifier == null) {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents(PageRequest.of(page, pageSize)));
        }

        return ResponseEntity.status(HttpStatus.OK).body(eventService.getEventsByClassifierType(typeClassifier, PageRequest.of(page, pageSize)));
    }

    /**
     * API-endpoint для удаления события
     * @param eventId идентификатор удаляемого события
     * @return HttpStatus запроса.
     */
    @DeleteMapping("/{eventId}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * API-endpoint для "soft" удаления события
     * @param eventId идентификатор удаляемого события
     * @return HttpStatus запроса.
     */
    @DeleteMapping("/{eventId}/soft")
    public ResponseEntity<HttpStatus> softDeleteEvent(@PathVariable Long eventId) {
        eventService.softDeleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}