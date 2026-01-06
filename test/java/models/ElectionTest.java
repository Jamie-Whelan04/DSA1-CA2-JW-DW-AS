package models;

import LinkedList.DoublyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElectionTest {

    private Election election;
    private Politician p1;
    private Politician p2;
    private Candidate c1;
    private Candidate c2;

    @BeforeEach
    void setUp() {
        election = new Election(10, "General", "Dublin", 2020, 5);

        p1 = new Politician(1, "John Doe", "Fine Gael", "Dublin");
        p2 = new Politician(2, "Jane Smith", "Sinn Fein", "Cork");

        c1 = new Candidate(p1, election, 1200, "Fine Gael");
        c2 = new Candidate(p2, election, 900, "Sinn Fein");
    }

    @Test
    void getSeats() {
        assertEquals(5, election.getSeats());
    }

    @Test
    void getYear() {
        assertEquals(2020, election.getYear());
    }

    @Test
    void getLocation() {
        assertEquals("Dublin", election.getLocation());
    }

    @Test
    void getElectionType() {
        assertEquals("General", election.getElectionType());
    }

    @Test
    void getId() {
        assertEquals(10, election.getId());
    }

    @Test
    void getCandidates() {
        assertTrue(election.getCandidates() instanceof DoublyLinkedList);
    }

    @Test
    void setId() {
        election.setId(99);
        assertEquals(99, election.getId());
    }

    @Test
    void setElectionType() {
        election.setElectionType("Local");
        assertEquals("Local", election.getElectionType());
    }

    @Test
    void setLocation() {
        election.setLocation("Cork");
        assertEquals("Cork", election.getLocation());
    }

    @Test
    void setYear() {
        election.setYear(2025);
        assertEquals(2025, election.getYear());
    }

    @Test
    void setSeats() {
        election.setSeats(12);
        assertEquals(12, election.getSeats());
    }

    @Test
    void addCandidate() {
        election.addCandidate(c1);
        assertEquals(c1, election.getCandidates().getHead().getValue());
    }

    @Test
    void removeCandidate() {
        election.addCandidate(c1);
        election.addCandidate(c2);

        election.removeCandidate(c1);

        assertEquals(c2, election.getCandidates().getHead().getValue());
    }

    @Test
    void getCandidateById() {
        election.addCandidate(c1);
        election.addCandidate(c2);

        Candidate found = election.getCandidateById(2);

        assertEquals(c2, found);
        assertEquals("Jane Smith", found.getCandidateName());
    }

    @Test
    void testToString() {
        String expected = "General, Election Location: Dublin, Election Year: 2020, Number of Seats: 5";
        assertEquals(expected, election.toString());
    }
}