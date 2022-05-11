package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketRepositoryTest {
    TicketRepository repository = new TicketRepository();

    @Test
    public void testEmptyRepository() {
        Ticket[] tickets = repository.findAll();
        assertEquals(tickets.length, 0);
    }

    @Test
    public void testOneItemRepository() {
        Ticket ticket = new Ticket(1, 7_450, 80, "OGZ", "GOJ");
        repository.add(ticket);
        Ticket[] tickets = repository.findAll();
        assertEquals(tickets.length, 1);
    }
}