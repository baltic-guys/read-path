package com.baltic.road.path.app.controllers

import com.baltic.road.path.app.dto.TestDto
import com.baltic.road.path.app.service.CredentialService
import io.swagger.annotations.Api
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("play")
@Api("Controller for test")
class PlaygroundController(val credentialService: CredentialService) {


    @PostMapping("/hello")
    fun test(@RequestBody msg: TestDto): ResponseEntity<String> {
        if (!credentialService.checkCredentional(msg.token)) {
            return ResponseEntity.badRequest().body("error")
        }
        return ResponseEntity.ok(msg.msg)
    }
}