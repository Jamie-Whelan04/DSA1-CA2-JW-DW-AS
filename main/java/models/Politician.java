package models;

public class Politician {

    private int id;
    private String name;
    private String party;
    private String county;

    public Politician(int id, String name, String party, String county) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.county = county;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getCounty() {
        return county;
    }

    // ====== REQUIRED FOR EDITING ======

    public void setName(String name) {
        this.name = name;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + party + ", " + county + ")";
    }
}