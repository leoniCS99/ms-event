package ms.event.service;

import ms.event.dto.request.CreatedInstitutionDto;
import ms.event.dto.request.UpdateInstitutionDto;
import ms.event.dto.response.InstitutionDetailsResponseDto;
import ms.event.dto.response.InstitutionResponseDto;
import ms.event.entity.InstitutionEntity;
import ms.event.enums.InstitutionTypeEnum;
import ms.event.exception.InstitutionException;
import ms.event.mapper.EntityMapper;
import ms.event.repository.InstitutionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstitutionServiceTest {
    @Spy
    private EntityMapper entityMapper;
    @InjectMocks
    private InstitutionService institutionService;
    @Mock
    private InstitutionRepository institutionRepository;

    @Test
    @DisplayName("created institution")
    void t1() {
        CreatedInstitutionDto createdInstitutionDto = new CreatedInstitutionDto();
        createdInstitutionDto.setName("Institution");
        createdInstitutionDto.setType(InstitutionTypeEnum.CONFEDERATION);

        InstitutionEntity institutionEntity = new InstitutionEntity();

        InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto();
        institutionResponseDto.setId(1);
        institutionResponseDto.setName("Institution");
        institutionResponseDto.setType(InstitutionTypeEnum.CONFEDERATION);

        when(institutionRepository.save(institutionEntity)).thenReturn(institutionEntity);
        when(entityMapper.convertToEntityInstitution(createdInstitutionDto)).thenReturn(institutionEntity);
        when(entityMapper.convertToEntityInstitutionDto(institutionEntity)).thenReturn(institutionResponseDto);

        InstitutionResponseDto sut = institutionService.create(createdInstitutionDto);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(createdInstitutionDto.getName());
        assertThat(sut.getType()).isEqualTo(createdInstitutionDto.getType());
    }

    @Test
    @DisplayName("institution update")
    void t2() {
        UpdateInstitutionDto updateInstitutionDto = new UpdateInstitutionDto();
        updateInstitutionDto.setName("Institution");
        updateInstitutionDto.setType(InstitutionTypeEnum.CONFEDERATION);

        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setId(1);
        institutionEntity.setName("Institution");
        institutionEntity.setType(InstitutionTypeEnum.CONFEDERATION);

        InstitutionResponseDto institutionResponseDto = new InstitutionResponseDto();
        institutionResponseDto.setId(1);
        institutionResponseDto.setName("Institution");
        institutionResponseDto.setType(InstitutionTypeEnum.CONFEDERATION);

        when(institutionRepository.save(institutionEntity)).thenReturn(institutionEntity);
        when(institutionRepository.findById(1)).thenReturn(Optional.of(institutionEntity));
        when(entityMapper.convertToEntityInstitutionDto(institutionEntity)).thenReturn(institutionResponseDto);

        InstitutionResponseDto sut = institutionService.update(1, updateInstitutionDto);

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(updateInstitutionDto.getName());
        assertThat(sut.getType()).isEqualTo(updateInstitutionDto.getType());
    }

    @Test
    @DisplayName("institution listing details")
    void t3() {
        InstitutionEntity institutionEntity = new InstitutionEntity();
        institutionEntity.setId(1);
        institutionEntity.setName("Institution");
        institutionEntity.setType(InstitutionTypeEnum.SINGULAR);
        institutionEntity.setEvents(new ArrayList<>());

        List<InstitutionEntity> institutionEntityList = new ArrayList<>() {
            {
                add(institutionEntity);
            }
        };

        when(institutionRepository.findAll()).thenReturn(institutionEntityList);

        List<InstitutionDetailsResponseDto> sut = institutionService.findAll();

        assertThat(sut).isNotEmpty().hasSize(1);
    }

    @Test
    @DisplayName("Remove non-existing institution and throws exception")
    void t4() {
        doThrow(new InstitutionException()).when(institutionRepository).existsById(99);
        assertThatCode(() -> institutionService.delete(99)).isInstanceOf(InstitutionException.class);
    }

    @Test
    @DisplayName("Remove existing institution successfully")
    void t5() {
        when(institutionRepository.existsById(1)).thenReturn(true);
        assertThatCode(() -> institutionService.delete(1)).doesNotThrowAnyException();
    }
}
