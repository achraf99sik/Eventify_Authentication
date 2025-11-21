package com.eventify.authentication.services;


import com.eventify.authentication.entity.Event;
import com.eventify.authentication.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {


    private EventRepository eventRepository;

    //create
    public Event creatEvent(Event e){
        return eventRepository.save(e);
    }

    //getAll
    public List<Event> getAll()
    {
        return eventRepository.findAll();
    }

    //getById
    public Event findById(UUID id){
        return  eventRepository.findById(id)
                .orElseThrow(()->new NotFoundException("the Event not exist in our database avec id = "+id));
    }


    //delete
    public void deletById(UUID id){

        eventRepository.deleteById(id);
    }

    //update
    public Event updateEvent(Event e) {

        Event updated=findById(e.getId());
        updated.setId(e.getId());
        updated.setCapacity(e.getCapacity());
        updated.setOrganizer(e.getOrganizer());
        updated.setLocation(e.getLocation());
        updated.setDateTime(e.getDateTime());
        updated.setRegistrations(e.getRegistrations());

        return eventRepository.save(updated);
    }

}
