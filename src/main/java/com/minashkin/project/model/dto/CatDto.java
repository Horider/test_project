package com.minashkin.project.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level= AccessLevel.PRIVATE)
public class CatDto {
    private String name;
    private Integer age;
    private Integer weight;
}
