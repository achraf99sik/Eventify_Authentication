package com.eventify.authentication.services;

import com.eventify.authentication.entity.Event;
import com.eventify.authentication.entity.User;
import com.eventify.authentication.exception.EventNotFoundException;
import com.eventify.authentication.exception.UnauthorizedActionException;
import com.eventify.authentication.repository.EventRepository;
import com.eventify.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event, String organizerEmail) {
        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new RuntimeException("Organizer not found"));
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }

    public Event updateEvent(UUID id, Event updatedEvent, String organizerEmail) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
            throw new UnauthorizedActionException("You can only edit your own events");
        }

        event.setTitle(updatedEvent.getTitle());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setDateTime(updatedEvent.getDateTime());
        event.setCapacity(updatedEvent.getCapacity());

        return eventRepository.save(event);
    }

    public void deleteEvent(UUID id, String requesterEmail, boolean isAdmin) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (!isAdmin && !event.getOrganizer().getEmail().equals(requesterEmail)) {
            throw new UnauthorizedActionException("You can only delete your own events");
        }
        eventRepository.delete(event);
    }
}