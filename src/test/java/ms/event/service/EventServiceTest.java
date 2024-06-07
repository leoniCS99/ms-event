package ms.event.service;

import ms.event.dto.request.CreatedEventDto;
import ms.event.dto.request.UpdatedEventDto;
import ms.event.dto.response.EventResponseDto;
import ms.event.entity.EventEntity;
import ms.event.entity.InstitutionEntity;
import ms.event.exception.EventException;
import ms.event.mapper.EntityMapper;
import ms.event.repository.EventRepository;
import ms.event.repository.InstitutionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Spy
    private EntityMapper entityMapper;
    @InjectMocks
    private EventService eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private InstitutionRepository institutionRepository;

    @Test
    @DisplayName("Creating an event should return the corresponding response")
    void t1() {
        CreatedEventDto createdEventDto = new CreatedEventDto();
        createdEventDto.setName("Evento de Exemplo");
        createdEventDto.setStartDate(LocalDate.now());
        createdEventDto.setEndDate(LocalDate.now());
        createdEventDto.setIsActive(true);
        createdEventDto.setIdInstitution(1);

        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setId(1);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1);

        EventResponseDto eventResponseDto = new EventResponseDto();
        eventResponseDto.setName(createdEventDto.getName());
        eventResponseDto.setStartDate(LocalDate.now());
        eventResponseDto.setEndDate(LocalDate.now());
        eventResponseDto.setIsActive(true);
        eventResponseDto.setIdInstitution(1);


        when(institutionRepository.findById(createdEventDto.getIdInstitution())).thenReturn(Optional.of(institutionEntity));
        when(entityMapper.convertToEventDtoForEntity(createdEventDto)).thenReturn(eventEntity);
        when(entityMapper.convertToEntityEventDto(eventEntity)).thenReturn(eventResponseDto);
        when(eventRepository.save(eventEntity)).thenReturn(eventEntity);

        EventResponseDto sut = eventService.create(createdEventDto);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(createdEventDto.getName());
        assertThat(sut.getStartDate()).isEqualTo(createdEventDto.getStartDate());
        assertThat(sut.getEndDate()).isEqualTo(createdEventDto.getEndDate());
        assertThat(sut.getIsActive()).isEqualTo(createdEventDto.getIsActive());
        assertThat(sut.getIdInstitution()).isEqualTo(createdEventDto.getIdInstitution());
    }

    @Test
    @DisplayName("update an event")
    void t2() {
        Integer eventId = 1;
        UpdatedEventDto updatedEventDto = new UpdatedEventDto();
        updatedEventDto.setName("Evento de Exemplo");
        updatedEventDto.setStartDate(LocalDate.now());
        updatedEventDto.setEndDate(LocalDate.now());
        updatedEventDto.setIsActive(true);
        updatedEventDto.setIdInstitution(2);

        EventEntity eventEntity = new EventEntity();
        eventEntity.setId(1);

        EventResponseDto eventResponseDto = new EventResponseDto();
        eventResponseDto.setName("Evento de Exemplo");
        eventResponseDto.setStartDate(LocalDate.now());
        eventResponseDto.setEndDate(LocalDate.now());
        eventResponseDto.setIsActive(true);
        eventResponseDto.setIdInstitution(2);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(eventEntity));
        when(eventRepository.save(eventEntity)).thenReturn(eventEntity);
        when(entityMapper.convertToEntityEventDto(eventEntity)).thenReturn(eventResponseDto);

        EventResponseDto sut = eventService.update(eventId, updatedEventDto);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(updatedEventDto.getName());
        assertThat(sut.getStartDate()).isEqualTo(updatedEventDto.getStartDate());
        assertThat(sut.getEndDate()).isEqualTo(updatedEventDto.getEndDate());
        assertThat(sut.getIsActive()).isEqualTo(updatedEventDto.getIsActive());
        assertThat(sut.getIdInstitution()).isEqualTo(updatedEventDto.getIdInstitution());
    }

    @Test
    @DisplayName("Remove non-existing event and throws exception")
    void t3(){
        doThrow(new EventException()).when(eventRepository).existsById(99);
        assertThatCode(() -> eventService.delete(99)).isInstanceOf(EventException.class);
    }

    @Test
    @DisplayName("Remove existing event successfully")
    void t4(){
        when(eventRepository.existsById(1)).thenReturn(true);
        assertThatCode(() -> eventService.delete(1)).doesNotThrowAnyException();
    }
}
