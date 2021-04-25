package com.barclays.helpqueue.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.barclays.helpqueue.domain.Ticket;
import com.barclays.helpqueue.repo.TicketRepo;

@Service
public class TicketService {
	private TicketRepo repo;

	public TicketService(TicketRepo repo) {
		super();
		this.repo = repo;
	}

	public Ticket createTicket(Ticket ticket) {
		ticket.setTimeCreated(LocalDateTime.now());
		return this.repo.save(ticket);
	}

	public List<Ticket> getAll() {
		return this.repo.findAll();
	}

	public Ticket updateTicket(Ticket ticket) {
		Optional<Ticket> optionalOld = this.repo.findById(ticket.getId());
		Ticket old = optionalOld.get();
		
		old.setStatus(ticket.getStatus());
		System.out.println(ticket.getStatus());
		if(ticket.getStatus() != null && ticket.getStatus().toLowerCase().equals("done")) {
			old.setSolution(ticket.getSolution());
		}else {
			old.setSolution(null);
		}
		
		return this.repo.save(old);
	}
}
