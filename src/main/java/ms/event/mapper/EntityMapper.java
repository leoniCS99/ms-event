package ms.event.mapper;

import ms.event.dto.request.CreatedEventDto;
import ms.event.dto.request.CreatedInstitutionDto;
import ms.event.dto.response.EventResponseDto;
import ms.event.dto.response.InstitutionDetailsResponseDto;
import ms.event.dto.response.InstitutionResponseDto;
import ms.event.entity.EventEntity;
import ms.event.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    @Mapping(target = "institution.id", source = "idInstitution")
    EventEntity convertToEventDtoForEntity(CreatedEventDto createdEventDto);
    @Mapping(target = "idInstitution", source = "institution.id")
    EventResponseDto convertToEntityEventDto(EventEntity eventEntity);
    InstitutionResponseDto convertToEntityInstitutionDto(InstitutionEntity institutionEntity);
    InstitutionEntity convertToEntityInstitution(CreatedInstitutionDto createdInstitutionDto);
    InstitutionDetailsResponseDto toDto(InstitutionEntity institutionEntity);
}
