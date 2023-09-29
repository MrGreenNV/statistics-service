package ru.averkiev.testservice.services;

import ru.averkiev.testservice.exceptions.TypeClassifierAlreadyExistException;
import ru.averkiev.testservice.exceptions.ClassifierNotFoundException;
import ru.averkiev.testservice.models.ClassifierDTO;

import java.util.List;

/**
 * Контракт взаимодействия с событиями, включающий в себя создание, обновление, удаление и поиск классификаторов.
 * @author mrGreenNV
 */
public interface ClassifierService {

    /**
     * Создание классификатора.
     * @param classifierDTO DTO с данными создаваемого классификатора.
     * @return DTO созданного классификатора
     * @throws TypeClassifierAlreadyExistException выбрасывает, если классификатор с таким типом уже существует.
     */
    ClassifierDTO createClassifier(ClassifierDTO classifierDTO) throws TypeClassifierAlreadyExistException;

    /**
     * Обновление данных классификатора.
     * @param classifierId идентификатор обновляемого классификатора.
     * @param updateClassifier обновленный данные.
     * @return DTO с обновленными данными.
     * @throws TypeClassifierAlreadyExistException выбрасывает, если классификатор с таким типом уже существует.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    ClassifierDTO updateClassifier(Long classifierId, ClassifierDTO updateClassifier) throws TypeClassifierAlreadyExistException, ClassifierNotFoundException;

    /**
     * Возвращает классификатор по идентификатору.
     * @param ClassifierId идентификатор искомого классификатора.
     * @return DTO искомого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    ClassifierDTO getClassifier(Long ClassifierId) throws ClassifierNotFoundException;

    /**
     * Возвращает список всех классификаторов.
     * @return список DTO классификаторов.
     */
    List<ClassifierDTO> getAllClassifier();

    /**
     * Удаляет классификатор
     * @param classifierId идентификатор удаляемого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    void deleteClassifier(Long classifierId) throws ClassifierNotFoundException;

    /**
     * Выполняет "soft" удаление классификатора.
     * @param classifierId идентификатор удаляемого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    void softDeleteClassifier(Long classifierId) throws ClassifierNotFoundException;

}
