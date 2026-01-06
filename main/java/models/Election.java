package models;

import LinkedList.DoublyLinkedList;
import LinkedList.Node;

public class Election {

    //Fields
    private int id;
    private String electionType;
    private String location;
    private int year;
    private int seats;
    private DoublyLinkedList<Candidate>  candidates;

    //Constructor
    public Election(int id, String electionType, String location, int year, int seats) {
        this.id = id;
        this.electionType = electionType;
        this.location = location;
        this.year = year;
        this.seats = seats;
        this.candidates = new DoublyLinkedList<>();
    }

    //Getters
    public int getSeats() {
        return seats;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public String getElectionType() {
        return electionType;
    }

    public int getId() {
        return id;
    }

    public DoublyLinkedList<Candidate> getCandidates() {
        return candidates;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    //Methods
    public void addCandidate(Candidate candidate) {
        candidates.insertAtTail(candidate);
    }

    public void removeCandidate(Candidate candidate) {
        candidates.deleteByValue(candidate);
    }

    public Candidate getCandidateById(int politicianId) {
        Node<Candidate> current = candidates.getHead();
        while (current != null) {
            Candidate candidate = current.getValue();
            if (candidate.getPolitician().equals(politicianId)) {
                return candidate;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        return electionType + ", Election Location: " + location + ", Election Year: " + year + ", Number of Seats: " + seats;
    }

}
