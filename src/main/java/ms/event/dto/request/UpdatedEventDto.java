package ms.event.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatedEventDto {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Integer idInstitution;
}
