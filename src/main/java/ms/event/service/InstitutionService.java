package ms.event.service;

import ms.event.dto.request.CreatedInstitutionDto;
import ms.event.dto.request.UpdateInstitutionDto;
import ms.event.dto.response.InstitutionResponseDto;
import ms.event.dto.response.InstitutionDetailsResponseDto;
import ms.event.entity.InstitutionEntity;
import ms.event.exception.InstitutionException;
import ms.event.mapper.EntityMapper;
import ms.event.repository.InstitutionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    private final EntityMapper entityMapper;

    public InstitutionService(InstitutionRepository institutionRepository, EntityMapper entityMapper) {
        this.institutionRepository = institutionRepository;
        this.entityMapper = entityMapper;
    }

    @Transactional
    public InstitutionResponseDto create(CreatedInstitutionDto createdInstitutionDto) {
        InstitutionEntity institutionEntity = entityMapper.convertToEntityInstitution(createdInstitutionDto);
        InstitutionEntity saveInstitutionEvent = institutionRepository.save(institutionEntity);
        return entityMapper.convertToEntityInstitutionDto(saveInstitutionEvent);
    }
    public List<InstitutionDetailsResponseDto> findAll() {
        List<InstitutionEntity> institutions = institutionRepository.findAll();
        return institutions.stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public InstitutionResponseDto update(Integer institutionId, UpdateInstitutionDto updateInstitutionDto) {
        InstitutionEntity existingInstitution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionException("Institution not found"));

        existingInstitution.setName(updateInstitutionDto.getName());
        existingInstitution.setType(updateInstitutionDto.getType());

        InstitutionEntity updatedInstitution = institutionRepository.save(existingInstitution);
        return entityMapper.convertToEntityInstitutionDto(updatedInstitution);
    }

    public void delete(Integer institutionId) {
        if (!institutionRepository.existsById(institutionId)) {
            throw new InstitutionException("Institution not found");
        }
        institutionRepository.deleteById(institutionId);
    }
}
