package net.lelyak.edu.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import net.lelyak.edu.entity.Auditorium;
import net.lelyak.edu.entity.Event;
import net.lelyak.edu.entity.Rating;
import net.lelyak.edu.repository.AuditoriumRepository;
import net.lelyak.edu.repository.EventRepository;
import net.lelyak.edu.utils.LocalDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("events")
public class EventsController {

	@Autowired
	private AuditoriumRepository auditoriumRepository;

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView openAllEvents() {
		ModelAndView mav = new ModelAndView("events/allEvents");
		mav.addObject("events", eventRepository.getAll());
		return mav;
	}

	@RequestMapping(value = "/loadFromFile", method = RequestMethod.POST)
	public String loadEventsFromXml(@RequestParam("fileWithEvents") MultipartFile file) throws IOException {
		XStream xStream = new XStream(new StaxDriver());
		@SuppressWarnings("unchecked")
		List<Event> events = (List<Event>) xStream.fromXML(file.getInputStream());
		for (Event event : events) {
			eventRepository.put(event);
		}
		return "redirect:/events/all";
	}

	@RequestMapping(value = "/all/get", method = RequestMethod.GET)
	public void getEventsInXml(HttpServletResponse response) throws IOException {
		List<Event> all = (List<Event>) eventRepository.getAll();
		XStream xStream = new XStream(new StaxDriver());
		response.setHeader("content-type", "application/xml");
		String xml = xStream.toXML(all);
		response.getWriter().write(xml);

	}

	@RequestMapping(path = "add", method = RequestMethod.POST)
	public String addEvent(@RequestParam Map<String, String> allRequestParams) {
		Event newEvent = new Event();
		// get auditorium id from request
		String auditoryIdString = allRequestParams.get("auditorium");
		Long auditoryId = Long.parseLong(auditoryIdString);
		Auditorium auditorium = auditoriumRepository.getById(auditoryId);
		newEvent.setAuditorium(auditorium);
		// get name
		String name = allRequestParams.get("name");
		newEvent.setName(name);
		// get base price
//		String basePriceString = allRequestParams.get("basePrice");
//		long basePrice = Long.parseLong(basePriceString);
//		newEvent.setPrice(basePrice);

		// get start
		String start = allRequestParams.get("start");
		LocalDate startDate = LocalDateConverter.convert(start);
		newEvent.setAirDate(Date.valueOf(startDate));

		// get stop
//		String end = allRequestParams.get("end");
//		LocalDateTime endDateTime = LocalDateTimeConverter.convert(end);
//		newEvent.setEnd(endDateTime);

		String ratingString = allRequestParams.get("rating");
		Rating rating = Rating.valueOf(ratingString);
		newEvent.setEnumRating(rating);

		int eventId = eventRepository.put(newEvent);
		return "redirect:/event/" + eventId;
	}

	@RequestMapping("book/{eventId}")
	public ModelAndView openBookTicketForEventPage(@PathVariable("eventId") Long eventId) {
		ModelAndView openBookTicketForEventModelAndView = new ModelAndView("events/book");
		Event event = eventRepository.getById(eventId);
		openBookTicketForEventModelAndView.addObject("event", event);
		return openBookTicketForEventModelAndView;
	}

}
