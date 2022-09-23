package com.db.connectville.controller;

import com.db.connectville.dtos.LoginRequest;
import com.db.connectville.dtos.LoginResponse;
import com.db.connectville.repository.UserRepository;
import com.db.connectville.service.JWTUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;

    @ApiOperation(value = "user login", response = LoginResponse.class,
            notes = "Returns a response for the user trying to login")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "Successful operation",
                    response = LoginResponse.class, responseContainer = "List")})
    @PostMapping("/login")
    public LoginResponse login(@ApiParam(value = "username", required = true, defaultValue = "Joe")
                                   @RequestBody LoginRequest loginRequest) {
        if (!userRepository.findByUsername(loginRequest.getUsername()).getUsername().equals("")) {
            String token = jwtUtils.generateJWT(loginRequest);
            return new LoginResponse(token);
        }
        return new LoginResponse("");
    }

    @PostMapping("/verify")
    public boolean verify(@RequestHeader("jwt") String token) {
        return jwtUtils.validate(token);
    }

}
