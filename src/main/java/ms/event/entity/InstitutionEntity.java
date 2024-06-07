package ms.event.entity;

import lombok.Getter;
import lombok.Setter;
import ms.event.enums.InstitutionTypeEnum;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity(name = "TB_INSTITUTION")
public class InstitutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private InstitutionTypeEnum type;
    @OneToMany(mappedBy = "institution", cascade = CascadeType.ALL)
    private List<EventEntity> events;
}
