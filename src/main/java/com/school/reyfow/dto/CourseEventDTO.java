package com.school.reyfow.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class CourseEventDTO {

    private String studentName;
    private String courseName;
    private String email;
}
