package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {

    private Politician politician;
    private Election election;
    private Candidate candidate;

    @BeforeEach
    void setUp() {
        politician = new Politician(1, "John Doe", "Fine Gael", "Dublin");
        election = new Election(10, "General", "Ireland", 2020, 5);
        candidate = new Candidate(politician, election, 1200, "Fine Gael");
    }

    @Test
    void getVotes() {
        assertEquals(1200, candidate.getVotes());
    }

    @Test
    void setVotes() {
        candidate.setVotes(1500);
        assertEquals(1500, candidate.getVotes());
    }

    @Test
    void getPolitician() {
        assertEquals(politician, candidate.getPolitician());
        assertEquals("John Doe", candidate.getPolitician().getName());
    }

    @Test
    void getElection() {
        assertEquals(election, candidate.getElection());
        assertEquals(2020, candidate.getElection().getYear());
    }

    @Test
    void getParty() {
        assertEquals("Fine Gael", candidate.getParty());
    }

    @Test
    void setParty() {
        candidate.setParty("Independent");
        assertEquals("Independent", candidate.getParty());
    }

    @Test
    void getCandidateName() {
        assertEquals("John Doe", candidate.getCandidateName());
    }

    @Test
    void testToString() {
        String expected = "John Doe, [Fine Gael], :1200";
        assertEquals(expected, candidate.toString());
    }
}