package com.eventify.authentication.controller;

import com.eventify.authentication.entity.Registration;
import com.eventify.authentication.entity.User;
import com.eventify.authentication.services.RegistrationService;
import com.eventify.authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/profile")
    public User getProfile(Authentication authentication) {
        return userService.getProfile(authentication.getName());
    }

    @PostMapping("/events/{id}/register")
    public Registration registerForEvent(@PathVariable UUID id, Authentication authentication) {
        return registrationService.registerForEvent(id, authentication.getName());
    }

    @GetMapping("/registrations")
    public List<Registration> getMyRegistrations(Authentication authentication) {
        return registrationService.getUserRegistrations(authentication.getName());
    }
}