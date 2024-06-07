package ms.event.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "TB_EVENT")
public class EventEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean isActive;
	@ManyToOne
	@JoinColumn(name = "institution_id")
	private InstitutionEntity institution;
    
    @PrePersist
    public void prePersist(){
        this.isActive = false;
    }
}
