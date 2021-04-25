package com.barclays.helpqueue.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.helpqueue.domain.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long> {

}