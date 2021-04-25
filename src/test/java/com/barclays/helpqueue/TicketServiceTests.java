package com.barclays.helpqueue;


import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.helpqueue.domain.Ticket;
import com.barclays.helpqueue.repo.TicketRepo;
import com.barclays.helpqueue.service.TicketService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TicketServiceTests {
	@Autowired
	private TicketService service;
	
	@MockBean
	private TicketRepo repo;
	
	@Test
	void addTicket() {
		final Ticket TEST_TICKET = new Ticket("title","author","description","status","urgency",null);
		final Ticket TEST_SAVED_TICKET = new Ticket("title","author","description","status","urgency",null);
		
		Mockito.when(this.repo.save(TEST_TICKET)).thenReturn(TEST_SAVED_TICKET);
		
		Assertions.assertThat(this.service.createTicket(TEST_TICKET)).isEqualTo(TEST_SAVED_TICKET);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_TICKET);
	}
	
	@Test
	void getAll() {
		final List<Ticket> TEST_TICKETS = Arrays.asList(
				new Ticket("first","author","description","status","urgency",null),
				new Ticket("second","author","description","status","urgency",null),
				new Ticket("third","author","description","status","urgency",null)
		);
		
		Mockito.when(this.repo.findAll()).thenReturn(TEST_TICKETS);
		
		Assertions.assertThat(this.service.getAll()).isEqualTo(TEST_TICKETS);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void updateTicket() {
		final Ticket TEST_TICKET = new Ticket("title","author","description","new_status","urgency",null);
		final Optional<Ticket> TEST_SAVED_TICKET = Optional.of(new Ticket("title","author","description","old_status","urgency",null));
		
		Mockito.when(this.repo.findById(TEST_TICKET.getId())).thenReturn(TEST_SAVED_TICKET);
		Mockito.when(this.repo.save(TEST_SAVED_TICKET.get())).thenReturn(TEST_TICKET);
		
		Assertions.assertThat(this.service.updateTicket(TEST_TICKET)).isEqualTo(TEST_TICKET);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(TEST_TICKET.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_SAVED_TICKET.get());
	}
	
	@Test
	void updateTicketDone() {
		final Ticket TEST_TICKET = new Ticket("title","author","description","done","urgency","solution");
		final Optional<Ticket> TEST_SAVED_TICKET = Optional.of(new Ticket("title","author","description","old_status","urgency",null));
		
		Mockito.when(this.repo.findById(TEST_TICKET.getId())).thenReturn(TEST_SAVED_TICKET);
		Mockito.when(this.repo.save(TEST_SAVED_TICKET.get())).thenReturn(TEST_TICKET);
		
		Assertions.assertThat(this.service.updateTicket(TEST_TICKET)).isEqualTo(TEST_TICKET);
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(TEST_TICKET.getId());
		Mockito.verify(this.repo, Mockito.times(1)).save(TEST_SAVED_TICKET.get());
	}
}
