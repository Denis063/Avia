package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {

    TicketRepository repository = new TicketRepository();
    TicketManager manager = new TicketManager(repository);
    Ticket first = new Ticket(1, 3_390, 60, "KUF", "DME");
    Ticket second = new Ticket(2, 4_080, 60, "KUF", "LED");
    Ticket thirst = new Ticket(3, 4_000, 60, "KUF", "RTW");
    Ticket fourth = new Ticket(4, 13_340, 60, "KUF", "KHV");
    Ticket fifth = new Ticket(5, 2_585, 60, "KUF", "SVO");
    Ticket sixth = new Ticket(6, 2_900, 60, "KUF", "LED");
    Ticket seventh = new Ticket(7, 7_900, 120, "KUF", "FRU");

    @BeforeEach
    public void setup() {
        repository.add(first);
        repository.add(second);
        repository.add(thirst);
        repository.add(fourth);
        repository.add(fifth);
        repository.add(sixth);

    }

    @Test //Тест сортировка по стоимости билета по возрастанию
    public void shouldSortByIdPrice() {
        Ticket[] expected = new Ticket[]{fifth, sixth, first, thirst, second, fourth};
        Ticket[] actual = new Ticket[]{fifth, sixth, first, thirst, second, fourth};
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // тест добавления еще одного элемента
    public void shouldAddOneMore() {
        repository.add(seventh);
        Ticket[] actual = repository.findAll();
        Ticket[] expected = new Ticket[]{first, second, thirst, fourth, fifth, sixth, seventh};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Тест сортировка по времени
    public void shouldSortByTime() {
        Ticket[] actual = manager.findAll("KUF", "DME");
        Ticket[] expected = new Ticket[]{first};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Тест неверный аэропорт вылета
    public void shouldWrongFrom() {
        Ticket[] actual = manager.findAll("", "RTW");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // Тест неверный аэропорт прилета
    public void shouldWrongTo() {
        Ticket[] actual = manager.findAll("KUF", "");
        Ticket[] expected = new Ticket[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // Тест удаление одного элемента
    public void shouldRemoveExist() {
        repository.removeById(4);
        assertEquals(repository.findAll().length, 5);
    }

    @Test // Тест сохранить элементы
    public void shouldSaveAndFindAll() {
        assertEquals(repository.findAll().length, 6);
    }

    @Test // поиск пустой репозиторий
    public void testFindInEmptyRepository() {
        TicketRepository repository = new TicketRepository();
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll(" ", " ");
        assertEquals(tickets.length, 0);
    }

    @Test // поиск в репозитории с 1 элементом
    public void testFindInOneItemRepository() {
        TicketRepository repository = new TicketRepository();
        Ticket ticket = new Ticket(1, 10_600, 10, "BRS", "MSC");
        repository.add(ticket);
        manager = new TicketManager(repository);
        Ticket[] tickets = manager.findAll("BRS", "MSC");
        assertEquals(tickets.length, 1);
    }
}
