package com.db.connectville.controller;

import com.db.connectville.dtos.LoginRequest;
import com.db.connectville.dtos.LoginResponse;
import com.db.connectville.exception.UserNotFoundException;
import com.db.connectville.model.User;
import com.db.connectville.repository.UserRepository;
import com.db.connectville.service.JWTUtils;
import com.db.connectville.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    @ApiOperation(value = "user login", response = LoginResponse.class, notes = "Returns a response for the user trying to login")
    @ApiResponses(value = {@ApiResponse(code = 500, message = "Server error"), @ApiResponse(code = 404, message = "Service not found"), @ApiResponse(code = 401, message = "Bad Request"), @ApiResponse(code = 200, message = "Successful operation", response = LoginResponse.class, responseContainer = "List")})
    @PostMapping("/login")
    public LoginResponse login(@ApiParam(value = "username", required = true, defaultValue = "Joe") @RequestBody LoginRequest loginRequest) {
        User loginUser;
        try {
            loginUser = userService.getByUsername(loginRequest.getUsername());
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        String token = jwtUtils.generateJWT(loginUser);
        loginUser.setJwt(token);
        userService.updateUser(loginUser);
        return new LoginResponse(token);

    }

    @PostMapping("/verify")
    public boolean verify(@RequestHeader("jwt") String token) {
        return jwtUtils.validate(token);
    }

}
