package com.school.reyfow.mapper;

import com.school.reyfow.mapper.dto.CourseDTO;
import com.school.reyfow.mapper.dto.CourseRequestDTO;
import com.school.reyfow.mapper.dto.RegisterStudentDTO;
import com.school.reyfow.model.Course;
import org.mapstruct.*;

import static java.util.Objects.nonNull;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface CourseMapper {

    @Mapping(target = "sortKey", constant = "COURSE")
    Course courseDTOToCourse(CourseRequestDTO courseDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "studentId", source = "dto.studentId"),
            @Mapping(target = "studentName", source = "dto.studentName"),
            @Mapping(target = "name", source = "course.name"),
    })
    Course courseDTOToCourse(RegisterStudentDTO dto, CourseDTO course);

    CourseDTO courseToCourseDTO(Course course);

    @AfterMapping
    default void afterCourseDTOToCourse(@MappingTarget Course course, RegisterStudentDTO registerStudentDTO, CourseDTO courseDTO) {
        if (nonNull(registerStudentDTO)) {
            course.setSortKey(String.format("%s#%s#%s", registerStudentDTO.getStudentId(),
                    registerStudentDTO.getStudentName(), registerStudentDTO.getCourseId()));
        }
    }
}
