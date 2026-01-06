package models;

public class Candidate {

    //Fields
    private Politician politician;
    private Election election;
    private int votes;
    private String party;

    //Constructor
    public Candidate(Politician politician, Election election, int votes, String party) {
        this.politician = politician;
        this.election = election;
        this.votes = votes;
        this.party = party;
    }

    //Getters and Setters
    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Politician getPolitician() {
        return politician;
    }

    public Election getElection() {
        return election;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    //Methods
    public String getCandidateName() {
        return politician.getName();
    }

    @Override
    public String toString() {
        return politician.getName() + ", [" + party + "], :" + votes;
    }

}
