/**
 * DatabaseDAOConfig.java
 *
 */
package net.lelyak.edu.config;

import net.lelyak.edu.dao.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(DerbyDatabaseConfiguration.class)
public class DatabaseDAOConfiguration {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDAO userDAO(){
		UserDAO userDatabaseDAO = new UserDAO();
		userDatabaseDAO.setDataSource(dataSource);
		return userDatabaseDAO; 
	}

	@Bean
	public EventDAO eventDAO(){
		EventDAO eventDatabaseDAO = new EventDAO();
		eventDatabaseDAO.setDataSource(dataSource);
		return eventDatabaseDAO;
	}

	@Bean
	public TicketDAO ticketDAO(){
		TicketDAO ticketDatabaseDAO = new TicketDAO();
		ticketDatabaseDAO.setDataSource(dataSource);
		return ticketDatabaseDAO;
	}
	
	@Bean
	public AuditoriumDAO auditoriumDAO(){
		AuditoriumDAO auditoriumDAO = new AuditoriumDAO();
		auditoriumDAO.setDataSource(dataSource);
		return auditoriumDAO;
	}
	
	@Bean
	public AspectCounterDAO aspectCounterDAO(){
		AspectCounterDAO aspectCounterDAO = new AspectCounterDAO();
		aspectCounterDAO.setDataSource(dataSource);
		return aspectCounterDAO;
		
	}
}
