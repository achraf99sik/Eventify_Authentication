package com.eventify.authentication.controller;


import com.eventify.authentication.entity.Event;
import com.eventify.authentication.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private  EventService eventService;

    @GetMapping
    ResponseEntity<List<Event>> getAllEvents() {
        List<Event> all=eventService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/create")
    Event creatEvent(@RequestBody Event event){
        return eventService.creatEvent(event);
    }

    @GetMapping("s")
    String gets()
    {
        return  "hello";
    }

}
