package net.lelyak.edu.web.controller;

import net.lelyak.edu.entity.Ticket;
import net.lelyak.edu.entity.User;
import net.lelyak.edu.service.TicketService;
import net.lelyak.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("tickets")
public class TicketsController {

	@Autowired
	private UserService userService;

	@Autowired
	private TicketService ticketService;

	@RequestMapping("/my")
	public ModelAndView openMyTicketsPage() {
		ModelAndView modelAndView = new ModelAndView("tickets/userTickets");
		User user = userService.getAll().get(0);
		List<Ticket> ticketsForUser = ticketService.getTicketsForUser(user);
		modelAndView.addObject("tickets", ticketsForUser);
		return modelAndView;
	}

	@RequestMapping(path = "/my/get", produces = { "application/pdf" })
	public ModelAndView getTicketsAsPdfFile() {
		User user = userService.getAll().get(0);
		List<Ticket> ticketsForUser = ticketService.getTicketsForUser(user);
		return new ModelAndView("ticketsPdfView", "tickets", ticketsForUser);
	}

}
