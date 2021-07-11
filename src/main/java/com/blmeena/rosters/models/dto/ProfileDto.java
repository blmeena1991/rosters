package com.blmeena.rosters.models.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class ProfileDto {
    private String name;
    private String title;
    private String description;
    private String imageUrl;

}
