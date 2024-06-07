package ms.event.service;

import ms.event.dto.request.CreatedEventDto;
import ms.event.dto.request.UpdatedEventDto;
import ms.event.dto.response.EventResponseDto;
import ms.event.entity.EventEntity;
import ms.event.entity.InstitutionEntity;
import ms.event.exception.EventException;
import ms.event.exception.InstitutionException;
import ms.event.mapper.EntityMapper;
import ms.event.repository.EventRepository;
import ms.event.repository.InstitutionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final InstitutionRepository institutionRepository;
    private final EntityMapper entityMapper;

    public EventService(EventRepository eventRepository, InstitutionRepository institutionRepository, EntityMapper entityMapper) {
        this.eventRepository = eventRepository;
        this.institutionRepository = institutionRepository;
        this.entityMapper = entityMapper;
    }
    @Transactional
    public EventResponseDto create(CreatedEventDto eventCreationDto) {
        InstitutionEntity institution = institutionRepository.findById(eventCreationDto.getIdInstitution())
                .orElseThrow(() -> new InstitutionException("Institution not found"));

        EventEntity eventEntity = entityMapper.convertToEventDtoForEntity(eventCreationDto);
        eventEntity.setInstitution(institution);
        EventEntity savedEventEntity = eventRepository.save(eventEntity);
        return entityMapper.convertToEntityEventDto(savedEventEntity);
    }

    @Transactional
    public EventResponseDto update(Integer eventId, UpdatedEventDto updatedEventDto) {
        EventEntity existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventException("Event not found"));

        existingEvent.setName(updatedEventDto.getName());
        existingEvent.setStartDate(updatedEventDto.getStartDate());
        existingEvent.setEndDate(updatedEventDto.getEndDate());
        existingEvent.setIsActive(updatedEventDto.getIsActive());

        EventEntity updatedEvent = eventRepository.save(existingEvent);
        return entityMapper.convertToEntityEventDto(updatedEvent);
    }

    public void delete(Integer eventId) {
        if (!eventRepository.existsById(eventId)) {
            throw new EventException("Event not found");
        }
        eventRepository.deleteById(eventId);
    }
}
