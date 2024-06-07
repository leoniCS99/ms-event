package ms.event.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventResponseDto {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private Integer idInstitution;
}
