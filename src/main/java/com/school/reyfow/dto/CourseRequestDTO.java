package com.school.reyfow.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CourseRequestDTO implements Serializable {
    private String name;
    private String sortKey;
    private Integer hours;
}
