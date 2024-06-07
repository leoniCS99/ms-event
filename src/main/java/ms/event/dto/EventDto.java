package ms.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventDto {
    private Integer id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}
