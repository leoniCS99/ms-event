package ms.event.dto.response;

import lombok.Getter;
import lombok.Setter;
import ms.event.dto.EventDto;
import ms.event.enums.InstitutionTypeEnum;

import java.util.List;

@Getter
@Setter
public class InstitutionDetailsResponseDto {
    private Integer id;
    private String name;
    private InstitutionTypeEnum type;
    private List<EventDto> events;
}
