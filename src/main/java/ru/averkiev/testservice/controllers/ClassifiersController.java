package ru.averkiev.testservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.averkiev.testservice.models.ClassifierDTO;
import ru.averkiev.testservice.services.impl.ClassifierServiceImpl;

import java.util.List;

/**
 * Класс представляет собой REST-контроллер для управления классификаторами и сбора статистики.
 * @author mrGreenNV
 */
@RestController
@RequestMapping("/api/classifiers")
@RequiredArgsConstructor
public class ClassifiersController {

    /** Сервис для взаимодействия с классификаторами */
    private final ClassifierServiceImpl classifierService;

    /**
     * API-endpoint для создания классификатора.
     * @param classifierDTO DTO с данными создаваемого классификатора.
     * @return DTO созданного классификатора.
     */
    @PostMapping()
    public ResponseEntity<ClassifierDTO> addClassifier(@RequestBody ClassifierDTO classifierDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.createClassifier(classifierDTO));
    }

    /**
     * API-endpoint для редактирования сохранённого классификатора.
     * @param classifierId идентификатор редактируемого классификатора.
     * @param updateClassifierDto DTO классификатора с новыми данными.
     * @return DTO отредактированного классификатора.
     */
    @PostMapping("/{classifierId}")
    public ResponseEntity<ClassifierDTO> editClassifier(@PathVariable Long classifierId, @RequestBody ClassifierDTO updateClassifierDto) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.updateClassifier(classifierId, updateClassifierDto));
    }

    /**
     * API-endpoint для просмотра конкретного классификатора.
     * @param classifierId идентификатор классификатора.
     * @return DTO найденного классификатора.
     */
    @GetMapping("/{classifierId}")
    public ResponseEntity<ClassifierDTO> showClassifier(@PathVariable Long classifierId) {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getClassifier(classifierId));
    }

    /**
     * Api-endpoint для просмотра всех классификаторов.
     * @return список DTO классификаторов.
     */
    @GetMapping()
    public ResponseEntity<List<ClassifierDTO>> showAllClassifier() {
        return ResponseEntity.status(HttpStatus.OK).body(classifierService.getAllClassifier());
    }

    /**
     * API-endpoint для удаления классификатора
     * @param classifierId идентификатор удаляемого классификатора
     * @return HttpStatus запроса.
     */
    @DeleteMapping("/{classifierId}")
    public ResponseEntity<HttpStatus> deleteClassifier(@PathVariable Long classifierId) {
        classifierService.deleteClassifier(classifierId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * API-endpoint для "soft" удаления классификатора
     * @param classifierId идентификатор удаляемого классификатора
     * @return HttpStatus запроса.
     */
    @DeleteMapping("/{classifierId}/soft")
    public ResponseEntity<HttpStatus> softDeleteEvent(@PathVariable Long classifierId) {
        classifierService.softDeleteClassifier(classifierId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
