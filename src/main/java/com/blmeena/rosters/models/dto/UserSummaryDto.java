package com.blmeena.rosters.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserSummaryDto {
    private Long id;
    private String userName;
    private String name;
}
