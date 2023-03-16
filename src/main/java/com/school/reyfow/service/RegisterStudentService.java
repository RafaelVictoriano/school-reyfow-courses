package com.school.reyfow.service;

import com.school.reyfow.mapper.dto.RegisterStudentDTO;
import com.school.reyfow.mapper.CourseMapper;
import com.school.reyfow.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Map;

import static com.school.reyfow.enumeration.EventTypeEnum.STUDENT_REGISTERED;

@Slf4j
@ApplicationScoped
public class RegisterStudentService {

    public static final String EVENT_NAME = "EVENT_NAME";

    @Inject
    CourseRepository repository;

    @Inject
    CourseMapper mapper;

    @Inject
    CourseService courseService;

    @Inject
    PublishNotificationService publishNotificationService;


    public String start(final RegisterStudentDTO registerStudentDTO) {
        final var course = courseService.findById(registerStudentDTO.getCourseId());
        final var studentRegister = mapper.courseDTOToCourse(registerStudentDTO, course);
        repository.save(studentRegister);
        final var messageAttributeValue = MessageAttributeValue.builder()
                .stringValue(STUDENT_REGISTERED.toString())
                .build();
        log.info("O estudante foi registrado no curso, curso:{}, estudante:{}", studentRegister.getName(),
                studentRegister.getStudentName());
        publishNotificationService.publish(studentRegister, Map.of(EVENT_NAME, messageAttributeValue));
        return studentRegister.getId();
    }
}
