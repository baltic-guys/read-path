package com.baltic.road.path.app.controllers

import com.baltic.road.path.app.dto.AuthDto
import com.baltic.road.path.app.dto.UserDto
import com.baltic.road.path.app.service.AuthService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("auth")
@Api(description = "Controller for login or register")
class AuthController(val authService: AuthService) {

    @PostMapping("/reg")
    @ApiOperation("Registration new user")
    fun reg(@RequestBody user: UserDto): ResponseEntity<AuthDto?> {
        if (authService.exist(user) != null) {
            return ResponseEntity.badRequest().body(AuthDto(null, null))
        }
        val header = authService.registration(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(header)
    }

    @PostMapping("/login")
    @ApiOperation("Login user")
    fun login(@RequestBody user: UserDto): ResponseEntity<AuthDto> {
        val header = authService.login(user) ?: return ResponseEntity.badRequest().body(AuthDto(null, null))
        return ResponseEntity.ok().body(header)
    }
}
