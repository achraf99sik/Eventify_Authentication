package com.eventify.authentication.services;

import com.eventify.authentication.entity.Event;
import com.eventify.authentication.entity.Registration;
import com.eventify.authentication.entity.User;
import com.eventify.authentication.entity.enums.RegistrationStatus;
import com.eventify.authentication.exception.EventNotFoundException;
import com.eventify.authentication.repository.EventRepository;
import com.eventify.authentication.repository.RegistrationRepository;
import com.eventify.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public Registration registerForEvent(UUID eventId, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        if (registrationRepository.existsByUserIdAndEventId(user.getId(), eventId)) {
            throw new RuntimeException("Already registered");
        }

        Registration registration = Registration.builder()
                .id(UUID.randomUUID())
                .user(user)
                .event(event)
                .status(RegistrationStatus.CONFIRMED)
                .build();

        return registrationRepository.save(registration);
    }

    public List<Registration> getUserRegistrations(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return registrationRepository.findByUserId(user.getId());
    }
}