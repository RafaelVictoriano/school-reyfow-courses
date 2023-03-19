package com.school.reyfow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtPayload {

    private String sub;
    private String name;
}
