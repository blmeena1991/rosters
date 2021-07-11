package com.blmeena.rosters.services;

import com.blmeena.rosters.models.UserLike;
import com.blmeena.rosters.models.dto.UserDto;
import com.blmeena.rosters.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface UserService {
    UserDto create(UserDto userDto);
    UserDto update(Long id, UserDto userDto);

    Page<UserDto> index(UserDto userDto, Pageable pageable);

    UserDto userVote(Map<String, Object> body, UserPrincipal currentUser);
}
