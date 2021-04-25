package com.barclays.helpqueue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.barclays.helpqueue.controller.HelpController;
import com.barclays.helpqueue.domain.Ticket;
import com.barclays.helpqueue.service.TicketService;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class HelpControllerTests {
	@Autowired
	private HelpController controller;
	
	@MockBean
	private TicketService service;
	
	@Test
	void createTicket() {
		final Ticket TEST_TICKET = new Ticket("title","author","description","status","urgency",null);
		final Ticket TEST_SAVED_TICKET = new Ticket("title","author","description","status","urgency",null);
		
		Mockito.when(this.service.createTicket(TEST_TICKET)).thenReturn(TEST_SAVED_TICKET);
		
		Assertions.assertThat(this.controller.createTicket(TEST_TICKET)).isEqualTo(TEST_SAVED_TICKET);
		
		Mockito.verify(this.service, Mockito.times(1)).createTicket(TEST_TICKET);
	}
	
	@Test
	void getAll() {
		final List<Ticket> TEST_TICKETS = Arrays.asList(
				new Ticket("first","author","description","status","urgency",null),
				new Ticket("second","author","description","status","urgency",null),
				new Ticket("third","author","description","status","urgency",null)
		);
		
		Mockito.when(this.service.getAll()).thenReturn(TEST_TICKETS);
		
		Assertions.assertThat(this.controller.getAll()).isEqualTo(TEST_TICKETS);
		
		Mockito.verify(this.service, Mockito.times(1)).getAll();
	}
	
	@Test
	void updateTicket() {
		final Ticket TEST_TICKET = new Ticket("title","author","description","new_status","urgency",null);
		
		Mockito.when(this.service.updateTicket(TEST_TICKET)).thenReturn(TEST_TICKET);
		
		Assertions.assertThat(this.controller.updateTicket(TEST_TICKET)).isEqualTo(TEST_TICKET);
		
		Mockito.verify(this.service, Mockito.times(1)).updateTicket(TEST_TICKET);
	}
}
