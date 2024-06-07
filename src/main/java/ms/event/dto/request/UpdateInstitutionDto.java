package ms.event.dto.request;

import lombok.Getter;
import lombok.Setter;
import ms.event.enums.InstitutionTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateInstitutionDto {
    @NotBlank
    @NotNull
    private String name;
    
    @NotNull
    private InstitutionTypeEnum type;
}
