package com.eventify.authentication.controller;

import com.eventify.authentication.entity.Event;
import com.eventify.authentication.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
public class OrganizerController {
    private final EventService eventService;

    @PostMapping("/events")
    @ResponseStatus(HttpStatus.CREATED)
    public Event createEvent(@RequestBody Event event, Authentication authentication) {
        return eventService.createEvent(event, authentication.getName());
    }

    @PutMapping("/events/{id}")
    public Event updateEvent(@PathVariable UUID id, @RequestBody Event event, Authentication authentication) {
        return eventService.updateEvent(id, event, authentication.getName());
    }

    @DeleteMapping("/events/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable UUID id, Authentication authentication) {
        eventService.deleteEvent(id, authentication.getName(), false);
    }
}
