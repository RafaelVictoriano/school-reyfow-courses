package com.school.reyfow.service;

import com.school.reyfow.dto.CourseDTO;
import com.school.reyfow.mapper.CourseMapper;
import com.school.reyfow.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Slf4j
@ApplicationScoped
public class FindCoursesService {

    private static final String COURSE = "COURSE";
    @Inject
    CourseRepository courseRepository;

    @Inject
    CourseMapper mapper;

    public CourseDTO byId(String id) {
        return this.courseRepository.findById(id, COURSE)
                .map(mapper::courseToCourseDTO)
                .orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public Optional<List<CourseDTO>> all() {
        return Optional.of(courseRepository.findAll(COURSE)
                .filter(not(List::isEmpty))
                .stream()
                .flatMap(List::stream)
                .map(mapper::courseToCourseDTO)
                .collect(Collectors.toList()));
    }


}
