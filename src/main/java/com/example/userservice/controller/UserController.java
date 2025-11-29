package com.example.userservice.controller;

import org.springframework.web.bind.annotation.*;

import com.example.userservice.dto.UserLoginRequest;
import com.example.userservice.dto.UserLoginResponse;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.mapper.UserLoginMapper;
import com.example.userservice.model.User;
import com.example.userservice.service.JwtService;
import com.example.userservice.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Provides methods for managing users")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @GetMapping
    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "List of all users", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/mail/{mail}")    
    @Operation(summary = "Get user by mail")
    @ApiResponse(responseCode = "200", description = "User by mail", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public UserResponse findByMail(@PathVariable String mail) {
        return userService.findByMail(mail);
    }

    @GetMapping("/userName/{nameUser}")
    @Operation(summary = "Get user by username")
    @ApiResponse(responseCode = "200", description = "User by username", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public UserResponse findByUsername(@PathVariable String nameUser) {
        return userService.findByUsername(nameUser);
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get users by role ID")
    @ApiResponse(responseCode = "200", description = "List of users by roleId", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public List<UserResponse> findByRoleId(@PathVariable Long roleId) {
        return userService.findByRoleId(roleId);
    }

    @GetMapping("/gym/{gymId}")
    @Operation(summary = "Get users by gym ID")
    @ApiResponse(responseCode = "200", description = "List of users by gymId", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public List<UserResponse> findByGymId(@PathVariable Long gymId) {
        return userService.findByGymId(gymId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    @ApiResponse(responseCode = "200", description = "User by ID", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "200", description = "User create", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserResponse createdUser = userService.create(userRequest);
        return ResponseEntity
                .created(URI.create("/api/v1/users/" + createdUser.getId()))
                .body(createdUser);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update user by ID")
    @ApiResponse(responseCode = "200", description = "User update", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public UserResponse update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        return userService.update(id, userRequest);
    }

    // @DeleteMapping("/{id}")
    // @Operation(summary = "Delete user by ID")
    // @ApiResponse(responseCode = "200", description = "User delete", content = {
    //      @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    // public Void delete(@PathVariable Long id) {
    //     return ResponseEntity.noContent().build();
    // }

    @GetMapping(value = "paginationAll", params = { "page", "pageSize" })
    @Operation(summary = "Get all users with pagination")
    @ApiResponse(responseCode = "200", description = "List of all users paginated", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    public List<UserResponse> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<UserResponse> users = userService.getAll(page, pageSize);
        return users;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody UserLoginRequest userLoginRequest) {             
        User authenticatedUser = userService.authenticate(userLoginRequest);        
        UserLoginResponse userLoginResponse = UserLoginMapper.toResponse(authenticatedUser);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        userLoginResponse.setToken(jwtToken);
        userLoginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(userLoginResponse);
    }
}