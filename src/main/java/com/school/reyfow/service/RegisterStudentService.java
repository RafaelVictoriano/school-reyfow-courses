package com.school.reyfow.service;

import com.school.reyfow.dto.RegisterStudentDTO;
import com.school.reyfow.mapper.CourseMapper;
import com.school.reyfow.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@Slf4j
@ApplicationScoped
public class RegisterStudentService {

    public static final Integer EVENT_CODE = 20;

    @Inject
    CourseRepository repository;

    @Inject
    CourseMapper mapper;

    @Inject
    FindCoursesService findCoursesService;

    @Inject
    PublishNotificationService publishNotificationService;


    public String start(final RegisterStudentDTO registerStudentDTO) {
        final var course = findCoursesService.byId(registerStudentDTO.getCourseId());
        final var studentRegister = mapper.courseToEvent(registerStudentDTO, course);
        repository.save(studentRegister);
        log.info("O estudante foi registrado no curso, curso:{}, estudante:{}", studentRegister.getName(),
                studentRegister.getStudentName());
        publishNotificationService.publish(mapper.courseToEvent(studentRegister), Map.of("EVENT",
                MessageAttributeValue.builder()
                        .dataType("String")
                        .stringValue("REGISTERED_COURSE")
                        .build()));
        return studentRegister.getId();
    }
}
