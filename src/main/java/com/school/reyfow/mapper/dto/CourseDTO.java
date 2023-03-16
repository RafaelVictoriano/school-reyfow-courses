package com.school.reyfow.mapper.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseDTO implements Serializable {
    private String id;
    private String name;
    private Integer hours;
}
