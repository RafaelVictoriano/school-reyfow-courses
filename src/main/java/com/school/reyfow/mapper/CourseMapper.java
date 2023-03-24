package com.school.reyfow.mapper;

import com.school.reyfow.dto.CourseDTO;
import com.school.reyfow.dto.CourseRequestDTO;
import com.school.reyfow.dto.CourseEventDTO;
import com.school.reyfow.dto.RegisterStudentDTO;
import com.school.reyfow.model.Course;
import org.mapstruct.*;

import static java.util.Objects.nonNull;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "cdi")
public interface CourseMapper {

    @Mapping(target = "sortKey", constant = "COURSE")
    Course courseToEvent(CourseRequestDTO courseDTO);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "studentId", source = "dto.studentId"),
            @Mapping(target = "studentName", source = "dto.studentName"),
            @Mapping(target = "name", source = "course.name"),
    })
    Course courseToEvent(RegisterStudentDTO dto, CourseDTO course);


    @Mappings({
            @Mapping(target = "courseName", source = "name"),
    })
    CourseEventDTO courseToEvent(Course course);

    CourseDTO courseToCourseDTO(Course course);

    @AfterMapping
    default void afterCourseDTOToCourse(@MappingTarget Course course, RegisterStudentDTO registerStudentDTO, CourseDTO courseDTO) {
        if (nonNull(registerStudentDTO)) {
            course.setSortKey(String.format("%s#%s#%s", registerStudentDTO.getStudentId(),
                    registerStudentDTO.getStudentName(), registerStudentDTO.getCourseId()));
        }
    }
}
