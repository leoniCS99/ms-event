package ms.event.dto.response;

import lombok.Getter;
import lombok.Setter;
import ms.event.enums.InstitutionTypeEnum;

@Getter
@Setter
public class InstitutionResponseDto {
    private Integer id;
    private String name;
    private InstitutionTypeEnum type;
}
