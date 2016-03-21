package net.lelyak.edu.controller;

import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Rating;
import net.lelyak.edu.repository.EventRepository;
import net.lelyak.edu.service.AuditoriumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("events")
public class EventsManageController {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private AuditoriumService auditoriumService;

	@RequestMapping("manage")
	public ModelAndView openManageEvents() {
		ModelAndView openManageEventsModelAndView = new ModelAndView("events/manage");
		List<Event> allEvents = (List<Event>) eventRepository.getAll();
		openManageEventsModelAndView.addObject("allEvents", allEvents);
		return openManageEventsModelAndView;
	}

	@RequestMapping("manage/add")
	public ModelAndView openAddEventPage() {
		ModelAndView openAddEventPageModelAndView = new ModelAndView("events/addEvent");
		openAddEventPageModelAndView.addObject("ratingOptions", Rating.values());
		openAddEventPageModelAndView.addObject("auditoriums", auditoriumService.getAuditoriums());
		return openAddEventPageModelAndView;
	}

}
