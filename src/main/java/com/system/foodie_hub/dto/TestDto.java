package com.system.foodie_hub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestDto {

    private Integer id;

    @NotNull(message = "Please enter valid age")
    private Integer age;

    @NotNull
    private String name;
}
