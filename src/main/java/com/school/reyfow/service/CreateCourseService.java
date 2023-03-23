package com.school.reyfow.service;

import com.school.reyfow.mapper.CourseMapper;
import com.school.reyfow.dto.CourseRequestDTO;
import com.school.reyfow.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class CreateCourseService {

    @Inject
    CourseRepository courseRepository;

    @Inject
    CourseMapper mapper;

    public String start(CourseRequestDTO courseRequestDTO) {
        final var course = mapper.courseToEvent(courseRequestDTO);
        this.courseRepository.save(course);
        return course.getId();
    }

}
