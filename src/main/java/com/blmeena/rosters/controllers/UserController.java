package com.blmeena.rosters.controllers;

import com.blmeena.rosters.models.UserLike;
import com.blmeena.rosters.models.dto.UserDto;
import com.blmeena.rosters.models.dto.UserSummaryDto;
import com.blmeena.rosters.security.CurrentUser;
import com.blmeena.rosters.security.UserPrincipal;
import com.blmeena.rosters.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public UserSummaryDto getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummaryDto userSummary = new UserSummaryDto(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }


    @PostMapping("/user-votes")
    public UserDto userVote(@CurrentUser UserPrincipal currentUser,
                             @RequestBody Map<String, Object> body) {
        return userService.userVote(body, currentUser);

    }

    @GetMapping(value = "")
    public Page<UserDto> index(UserDto userDto, Pageable pageable) {
        return userService.index(userDto, pageable);
    }
}
