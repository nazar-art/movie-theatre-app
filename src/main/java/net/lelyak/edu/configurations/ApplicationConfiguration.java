package net.lelyak.edu.configurations;

import net.lelyak.edu.dao.impl.*;
import net.lelyak.edu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan({ "net.lelyak.edu.repository", "net.lelyak.edu.service" })
@Import({ DatabaseDAOConfiguration.class })
public class ApplicationConfiguration {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private EventDAO eventDAO;

	@Autowired
	private TicketDAO ticketDAO;

	@Autowired
	private AuditoriumDAO auditoriumDAO;

	@Autowired
	private UserAccountDAO userAccountDAO;

	@Bean
	public AuditoriumRepository auditoriumRepository() {
		AuditoriumRepository auditoriumRepository = new AuditoriumRepository();
		auditoriumRepository.setDao(auditoriumDAO);
		return auditoriumRepository;
	}

	@Bean
	public EventRepository eventRepository() {
		EventRepository eventRepository = new EventRepository();
		eventRepository.setDao(eventDAO);
		return eventRepository;
	}

	@Bean
	public UserRepository userRepository() {
		UserRepository userRepository = new UserRepository();
		userRepository.setDao(userDAO);
		return userRepository;
	}

	@Bean
	public TicketRepository ticketRepository() {
		TicketRepository ticketRepository = new TicketRepository();
		ticketRepository.setDao(ticketDAO);
		return ticketRepository;
	}

	@Bean
	public UserAccountRepository userAccountRepository() {
		UserAccountRepository userAccountRepository = new UserAccountRepository();
		userAccountRepository.setDao(userAccountDAO);
		return userAccountRepository;
	}

	/*@Bean
	public Application application() {
		return new Application();
	}*/
}
