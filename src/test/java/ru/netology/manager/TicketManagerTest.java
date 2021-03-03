package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.manager.TicketManager;
import ru.netology.repository.TicketRepository;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    TicketRepository repository = new TicketRepository();
    private Ticket first = new Ticket(1, 3290, "LED", "ROV", 140);
    private Ticket second = new Ticket(2, 2890, "LED", "ROV", 148);
    private Ticket third = new Ticket(3, 5800, "LED", "PRG", 120);
    private Ticket fouth = new Ticket(4, 3000, "LED", "STO", 75);
    private Ticket fifth = new Ticket(5, 12000, "LED", "ROV", 75);

    @Test
    public void shouldSortFromLedToRov() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fouth);
        repository.save(fifth);

        TicketManager manager = new TicketManager(repository);
        Ticket[] expected = new Ticket[]{second, first, fifth};
        Ticket[] actual = manager.search("LED", "ROV");
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldSortFromLedToSto() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fouth);
        repository.save(fifth);

        TicketManager manager = new TicketManager(repository);
        Ticket[] expected = new Ticket[]{fouth};
        Ticket[] actual = manager.search("LED", "STO");
        assertArrayEquals(actual, expected);
    }

    @Test
    public void shouldWorkIfZero() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fouth);
        repository.save(fifth);

        TicketManager manager = new TicketManager(repository);
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.search("LED", "DME");
        assertArrayEquals(actual, expected);
    }
}