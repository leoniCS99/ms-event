package ms.event.controller;

import ms.event.dto.request.CreatedEventDto;
import ms.event.dto.response.EventResponseDto;
import ms.event.dto.request.UpdatedEventDto;
import ms.event.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    ResponseEntity<EventResponseDto> save(@Valid @RequestBody CreatedEventDto createdEventDto) {
        return ResponseEntity.ok(eventService.create(createdEventDto));
    }

    @PutMapping("/{eventId}")
    ResponseEntity<EventResponseDto> update(@PathVariable Integer eventId, @RequestBody UpdatedEventDto updatedEventDto) {
        EventResponseDto updatedEvent = eventService.update(eventId, updatedEventDto);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{eventId}")
    ResponseEntity<Void> delete(@PathVariable Integer eventId) {
        eventService.delete(eventId);
        return ResponseEntity.noContent().build();
    }
}
