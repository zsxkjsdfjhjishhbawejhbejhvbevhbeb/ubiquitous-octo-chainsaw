package com.barclays.helpqueue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.helpqueue.domain.Ticket;
import com.barclays.helpqueue.service.TicketService;

@CrossOrigin
@RestController
public class HelpController {
	
	@Autowired
	private TicketService service;
	
	// Create
    @PostMapping("/create")
    public Ticket createTicket(@RequestBody Ticket ticket) {
    	return this.service.createTicket(ticket);
    }
    
    // get all
    @GetMapping("/getAll")
    public List<Ticket> getAll() {
        return this.service.getAll();
    }
    
    // update
    @PostMapping("/update")
    public Ticket updateTicket(@RequestBody Ticket ticket) {
    	return this.service.updateTicket(ticket);
    }
}
