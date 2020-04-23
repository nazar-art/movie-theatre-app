package net.lelyak.edu.dao.impl;

import net.lelyak.edu.dao.BaseDAO;
import net.lelyak.edu.entity.Ticket;

import java.util.Arrays;

public class TicketDAO extends BaseDAO<Ticket> {

    private static final String TICKET_TABLE_NAME = "t_ticket";
    private static final String[] ticketFields = {"name", "price", "onDate", "event_id", "user_id"};

    public TicketDAO() {
        super(Ticket.class, TICKET_TABLE_NAME, Arrays.asList(ticketFields));
    }
}
