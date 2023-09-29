package ru.averkiev.testservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.averkiev.testservice.exceptions.ClassifierNotFoundException;
import ru.averkiev.testservice.exceptions.TypeClassifierAlreadyExistException;
import ru.averkiev.testservice.models.Classifier;
import ru.averkiev.testservice.models.ClassifierDTO;
import ru.averkiev.testservice.models.EntityStatus;
import ru.averkiev.testservice.repositories.ClassifierRepository;
import ru.averkiev.testservice.services.ClassifierService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для взаимодействия с классификаторами.
 * @author mrGreenNV
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ClassifierServiceImpl implements ClassifierService {

    /** Репозиторий для обращения к базе данных объектов Classifier */
    private final ClassifierRepository classifierRepository;

    /** Позволяет преобразовать классы моделей к DTO */
    private final ModelMapper modelMapper;

    /**
     * Создание классификатора.
     * @param classifierDTO DTO с данными создаваемого классификатора.
     * @return DTO созданного классификатора
     * @throws TypeClassifierAlreadyExistException выбрасывает, если классификатор с таким типом уже существует.
     */
    @Override
    public ClassifierDTO createClassifier(ClassifierDTO classifierDTO) throws TypeClassifierAlreadyExistException {

        if (classifierRepository.existsClassifierByType(classifierDTO.getType())) {
            log.error("IN createClassifier - классификатор с типом: {} не создан", classifierDTO.getType());
            throw new TypeClassifierAlreadyExistException("Классификатор с данным типом уже существует");
        }

        Classifier classifier = modelMapper.map(classifierDTO, Classifier.class);
        classifier = classifierRepository.save(classifier);

        log.info("IN createClassifier - классификатор с типом: {} успешно создан", classifierDTO.getType());

        return modelMapper.map(classifier, ClassifierDTO.class);
    }

    /**
     * Обновление данных классификатора.
     * @param classifierId идентификатор обновляемого классификатора.
     * @param updateClassifier обновленный данные.
     * @return DTO с обновленными данными.
     * @throws TypeClassifierAlreadyExistException выбрасывает, если классификатор с таким типом уже существует.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public ClassifierDTO updateClassifier(Long classifierId, ClassifierDTO updateClassifier) throws TypeClassifierAlreadyExistException, ClassifierNotFoundException {

        String updatedTypeClassifier = updateClassifier.getType();

        Classifier classifier = getClassifierById(classifierId);

        if (updatedTypeClassifier != null && !updatedTypeClassifier.equals("")) {
            if (classifierRepository.existsClassifierByType(updatedTypeClassifier)) {
                log.error("IN updateClassifier - классификатор: {} не обновлен", classifier.getType());
                throw new TypeClassifierAlreadyExistException("Классификатор с данным типом уже существует");
            }
            classifier.setType(updatedTypeClassifier);
        }

        classifier.setDescription(updateClassifier.getDescription());

        classifier = classifierRepository.save(classifier);
        log.info("IN updateClassifier - классификатор: {} успешно обновлен", classifier.getType());

        return modelMapper.map(classifier, ClassifierDTO.class);
    }

    /**
     * Возвращает классификатор по идентификатору.
     * @param classifierId идентификатор искомого классификатора.
     * @return DTO искомого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public ClassifierDTO getClassifier(Long classifierId) throws ClassifierNotFoundException {
        return modelMapper.map(getClassifierById(classifierId), ClassifierDTO.class);
    }

    /**
     * Возвращает список всех классификаторов.
     * @return список DTO классификаторов.
     */
    @Override
    public List<ClassifierDTO> getAllClassifier() {
        return classifierRepository.findAll().stream()
                .map(classifier -> modelMapper.map(classifier, ClassifierDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Удаляет классификатор
     * @param classifierId идентификатор удаляемого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public void deleteClassifier(Long classifierId) throws ClassifierNotFoundException {
        Classifier classifier = getClassifierById(classifierId);
        classifierRepository.delete(classifier);

        log.info("IN deleteClassifier - классификатор с типом: {} успешно удален", classifier.getType());
    }

    /**
     * Выполняет "soft" удаление классификатора.
     * @param classifierId идентификатор удаляемого классификатора.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    @Override
    public void softDeleteClassifier(Long classifierId) throws ClassifierNotFoundException {
        Classifier classifier = getClassifierById(classifierId);
        classifier.setEntityStatus(EntityStatus.DELETED);

        classifierRepository.save(classifier);
        log.info("IN softDeleteClassifier - классификатор с типом: {} успешно помечен на удаление", classifier.getType());

    }

    /**
     * Выполняет поиск классификатора по идентификатору.
     * @param classifierId идентификатор классификатора.
     * @return классификатор.
     * @throws ClassifierNotFoundException выбрасывает, если возникает ошибка при поиске классификатора.
     */
    private Classifier getClassifierById(Long classifierId) throws ClassifierNotFoundException {
        Optional<Classifier> classifier = classifierRepository.findById(classifierId);
        if (classifier.isEmpty()) {
            log.error("IN getClassifierById - классификатор с идентификатором: {} не найден", classifierId);
            throw new ClassifierNotFoundException("Классификатор с идентификатором: " + classifierId + " не найден");
        }
        log.info("IN getClassifierById - классификатор с идентификатором: {} успешно найден", classifierId);
        return classifier.get();
    }
}
