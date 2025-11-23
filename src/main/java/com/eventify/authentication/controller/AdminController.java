package com.eventify.authentication.controller;

import com.eventify.authentication.entity.enums.Role;
import org.springframework.web.bind.annotation.RestController;

import com.eventify.authentication.entity.User;
import com.eventify.authentication.services.EventService;
import com.eventify.authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final EventService eventService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/users/{id}/role")
    public User updateUserRole(@PathVariable UUID id, @RequestParam Role role) {
        return userService.updateUserRole(id, role);
    }

    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventAny(@PathVariable UUID id) {
        eventService.deleteEvent(id, null, true);
    }
}