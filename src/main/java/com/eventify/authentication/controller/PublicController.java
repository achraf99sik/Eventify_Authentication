package com.eventify.authentication.controller;

import com.eventify.authentication.entity.Event;
import com.eventify.authentication.entity.User;
import com.eventify.authentication.services.EventService;
import com.eventify.authentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {
    private final UserService userService;
    private final EventService eventService;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
}