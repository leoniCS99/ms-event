package ms.event.service;

import ms.event.entity.EventEntity;
import ms.event.repository.EventRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventStatusScheduler {
	
	private final EventRepository eventRepository;
	
	public EventStatusScheduler(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	@Transactional
	@Scheduled(fixedRate = 60000)
	public void updateEventStatus() {
		LocalDate dateNow = LocalDate.now();
		List<EventEntity> eventsToUpdate = eventRepository.findAll();
		for (EventEntity event : eventsToUpdate) {
			boolean isActive = isEventActive(event, dateNow);
			if (event.getIsActive() != isActive) {
				event.setIsActive(isActive);
				eventRepository.save(event);
			}
		}
	}
	
	private boolean isEventActive(EventEntity event, LocalDate dateNow) {
		LocalDate startDate = event.getStartDate();
		LocalDate endDate = event.getEndDate();
		return dateNow.isEqual(startDate) || (dateNow.isAfter(startDate) && dateNow.isBefore(endDate));
	}
}
