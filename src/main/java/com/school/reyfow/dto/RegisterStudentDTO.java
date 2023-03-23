package com.school.reyfow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterStudentDTO {
    private String studentId;
    private String studentName;
    private String email;
    private String courseId;
}

