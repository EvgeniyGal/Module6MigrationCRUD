package edu.goit.services;

import edu.goit.entity.Client;
import org.junit.jupiter.api.*;

import java.util.List;


class ClientServiceTest {

    private static ClientService clientService;

    @BeforeAll
    static void setUp() {
        clientService = new ClientService();
    }

    @Test
    void createTest() {
        long expected = 11;
        long actual = clientService.create("John Wilgeim");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getByIdTest() {
        String expected = "Olivia Thompson";
        String actual = clientService.getById(9);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void setNameTest() {
        String expectedName = "John Carpeton";
        long clientID = 5;
        clientService.setName(clientID, expectedName);
        String actualName = clientService.getById(clientID);

        Assertions.assertEquals(expectedName, actualName);
    }

    @Test
    void deleteByIdTest() {
        long clientID = 11;
        clientService.deleteById(clientID);

        Assertions.assertNull(clientService.getById(clientID));
    }

    @Test
    void listAllTest() {
        List<Client> clients = clientService.listAll();

        int expectedClientsNumber = 10;
        String expectedName = "Sophia Taylor";
        long clientID = 6;
        String actualName = "";

        for (Client client : clients
        ) {
            if (client.getID() == clientID) {
                actualName = client.getName();
                break;
            }
        }

        Assertions.assertEquals(expectedClientsNumber, clients.size());
        Assertions.assertEquals(expectedName, actualName);
    }

}