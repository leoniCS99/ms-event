package ms.event.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class CreatedEventDto {
    @NotBlank
    @NotNull
    private String name;
    
    @NotNull
    @FutureOrPresent
    private LocalDate startDate;
    
    @NotNull
    @Future
    private LocalDate endDate;
    
    private Boolean isActive;
    
    @NotNull
    private Integer idInstitution;
}
