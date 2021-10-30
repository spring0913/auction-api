package com.auction.api.controller;

import com.auction.api.ApiResult;
import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import com.auction.api.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.auction.api.ApiResult.OK;

@RestController
@RequestMapping("api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "user/join")
    public ApiResult<UserDto> join(@RequestBody JoinRequest joinRequest){
        User user = userService.join(
            joinRequest.getName(),
            new Email(joinRequest.getEmail()),
            joinRequest.getCredential()
        );

        return OK(new UserDto(user));
    }
}
