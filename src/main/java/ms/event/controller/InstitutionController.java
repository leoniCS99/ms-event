package ms.event.controller;

import ms.event.dto.request.CreatedInstitutionDto;
import ms.event.dto.request.UpdateInstitutionDto;
import ms.event.dto.response.InstitutionDetailsResponseDto;
import ms.event.dto.response.InstitutionResponseDto;
import ms.event.service.InstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/institutions")
public class InstitutionController {
    private final InstitutionService institutionService;

    public InstitutionController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @PostMapping
    ResponseEntity<InstitutionResponseDto> save(@Valid @RequestBody CreatedInstitutionDto createdInstitutionDto) {
        return ResponseEntity.ok(institutionService.create(createdInstitutionDto));
    }

    @GetMapping
    ResponseEntity<List<InstitutionDetailsResponseDto>> findAll() {
        return ResponseEntity.ok(institutionService.findAll());
    }

    @PutMapping("/{institutionId}")
    ResponseEntity<InstitutionResponseDto> update(@PathVariable Integer institutionId, @Valid @RequestBody UpdateInstitutionDto updateInstitutionDto) {
        InstitutionResponseDto updatedInstitution = institutionService.update(institutionId, updateInstitutionDto);
        return ResponseEntity.ok(updatedInstitution);
    }

    @DeleteMapping("/{institutionId}")
    ResponseEntity<Void> delete(@PathVariable Integer institutionId) {
        institutionService.delete(institutionId);
        return ResponseEntity.noContent().build();
    }
}
