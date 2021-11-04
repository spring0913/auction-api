package com.auction.api.controller.user;

import com.auction.api.ApiResult;
import com.auction.api.model.user.Email;
import com.auction.api.model.user.User;
import com.auction.api.service.user.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
        Optional<User> opt = userService.findByEmail(new Email(joinRequest.getEmail()));
        if(opt.isPresent()){
            throw new DuplicateKeyException("중복된 이메일입니다.");
        }

        User user = userService.join(
            joinRequest.getName(),
            new Email(joinRequest.getEmail()),
            joinRequest.getCredential()
        );

        return OK(new UserDto(user));
    }
}
