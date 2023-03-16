package com.school.reyfow.service;

import com.school.reyfow.mapper.dto.CourseDTO;
import com.school.reyfow.mapper.dto.CourseRequestDTO;
import com.school.reyfow.mapper.CourseMapper;
import com.school.reyfow.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@Slf4j
@ApplicationScoped
public class CourseService {

    private static final String COURSE = "COURSE";
    @Inject
    CourseRepository courseRepository;

    @Inject
    CourseMapper mapper;

    public CourseDTO findById(String id) {
        return this.courseRepository.findById(id, COURSE)
                .map(mapper::courseToCourseDTO)
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public String create(CourseRequestDTO courseRequestDTO) {
        final var course = mapper.courseDTOToCourse(courseRequestDTO);
        this.courseRepository.save(course);
        return course.getId();
    }

}
