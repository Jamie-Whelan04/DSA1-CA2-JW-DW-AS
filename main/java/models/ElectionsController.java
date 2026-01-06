package models;

import LinkedList.Node;
import hashtables.ElectionHashTable;
import hashtables.PoliticianHashTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Politician;
import sort.ElectionSorter;
import sort.PoliticianSorter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Optional;

public class ElectionsController {

    @FXML private ListView<Politician> politicianList;
    @FXML private ListView<Election>   electionList;
    @FXML private ListView<Candidate>  candidateList;
    @FXML private ListView<Election>   politicianElectionList;

    @FXML private TextField searchPolName;
    @FXML private TextField searchPolParty;
    @FXML private TextField searchPolCounty;

    @FXML private TextField searchElectionType;
    @FXML private TextField searchElectionYear;

    @FXML private Label lblPoliticianDetails;

    private PoliticianHashTable politicianTable = new PoliticianHashTable(101);
    private ElectionHashTable   electionTable   = new ElectionHashTable(101);

    private PoliticianSorter politicianSorter = new PoliticianSorter();
    private ElectionSorter   electionSorter   = new ElectionSorter();

    @FXML
    public void initialize() {
        politicianList.setOnMouseClicked(e -> onPoliticianClicked());
        electionList.setOnMouseClicked(e -> onElectionClicked());
        candidateList.setOnMouseClicked(e -> onCandidateClicked());
        politicianElectionList.setOnMouseClicked(e -> onPoliticianElectionClicked());

        refreshPoliticianList();
        refreshElectionList();
    }

    @FXML
    public void addPolitician() {
        try {
            int id      = Integer.parseInt(prompt("Add Politician", "Enter ID:"));
            String name = prompt("Add Politician", "Enter Name:");
            String party  = prompt("Add Politician", "Enter Party:");
            String county = prompt("Add Politician", "Enter County:");

            politicianTable.insert(new Politician(id, name, party, county));
            refreshPoliticianList();
        } catch (Exception ex) {
            showAlert("Invalid input for politician.");
        }
    }

    @FXML
    public void addElection() {
        try {
            int id       = Integer.parseInt(prompt("Add Election", "Enter Election ID:"));
            String type  = prompt("Add Election", "Enter Election Type (e.g. General, Local):");
            String loc   = prompt("Add Election", "Enter Location (County / Ireland):");
            int year     = Integer.parseInt(prompt("Add Election", "Enter Year:"));
            int seats    = Integer.parseInt(prompt("Add Election", "Enter Number of Seats:"));

            electionTable.insert(new Election(id, type, loc, year, seats));
            refreshElectionList();
        } catch (Exception ex) {
            showAlert("Invalid input for election.");
        }
    }

    @FXML
    public void addCandidate() {
        Election e = electionList.getSelectionModel().getSelectedItem();
        if (e == null) {
            showAlert("Select an election first.");
            return;
        }

        try {
            int polId = Integer.parseInt(prompt("Add Candidate", "Enter Politician ID:"));
            Politician p = politicianTable.get(polId);
            if (p == null) {
                showAlert("Politician not found.");
                return;
            }

            int votes   = Integer.parseInt(prompt("Add Candidate", "Enter Votes:"));
            String party = prompt("Add Candidate", "Enter Party in this Election:");

            e.addCandidate(new Candidate(p, e, votes, party));
            onElectionClicked();
        } catch (Exception ex) {
            showAlert("Invalid input for candidate.");
        }
    }

    @FXML
    public void editPolitician() {
        Politician p = politicianList.getSelectionModel().getSelectedItem();
        if (p == null) {
            showAlert("Select a politician to edit.");
            return;
        }

        String newName   = prompt("Edit Politician", "Update Name:",   p.getName());
        String newParty  = prompt("Edit Politician", "Update Party:",  p.getParty());
        String newCounty = prompt("Edit Politician", "Update County:", p.getCounty());

        p.setName(newName);
        p.setParty(newParty);
        p.setCounty(newCounty);

        refreshPoliticianList();
        onPoliticianClicked();
    }

    @FXML
    public void editElection() {
        Election e = electionList.getSelectionModel().getSelectedItem();
        if (e == null) {
            showAlert("Select an election to edit.");
            return;
        }

        String newType = prompt("Edit Election", "Update Type:", e.getElectionType());
        String newLoc  = prompt("Edit Election", "Update Location:", e.getLocation());
        int newYear    = Integer.parseInt(prompt("Edit Election", "Update Year:", String.valueOf(e.getYear())));
        int newSeats   = Integer.parseInt(prompt("Edit Election", "Update Seats:", String.valueOf(e.getSeats())));

        e.setElectionType(newType);
        e.setLocation(newLoc);
        e.setYear(newYear);
        e.setSeats(newSeats);

        refreshElectionList();
        onElectionClicked();
    }

    @FXML
    public void editCandidate() {
        Election e = electionList.getSelectionModel().getSelectedItem();
        Candidate c = candidateList.getSelectionModel().getSelectedItem();

        if (e == null || c == null) {
            showAlert("Select an election and candidate to edit.");
            return;
        }

        int newVotes = Integer.parseInt(prompt("Edit Candidate", "Update Votes:", String.valueOf(c.getVotes())));
        String newParty = prompt("Edit Candidate", "Update Party:", c.getParty());

        c.setVotes(newVotes);
        c.setParty(newParty);

        onElectionClicked();
    }

    @FXML
    public void deletePolitician() {
        Politician selected = politicianList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a politician to delete.");
            return;
        }

        Politician[] all = politicianTable.toArray();
        PoliticianHashTable newTable = new PoliticianHashTable(all.length);
        for (Politician p : all) {
            if (p != null && p.getId() != selected.getId()) {
                newTable.insert(p);
            }
        }
        politicianTable = newTable;

        // remove from elections' candidate lists
        Election[] els = electionTable.toArray();
        for (Election e : els) {
            if (e == null) continue;
            Candidate c = e.getCandidateById(selected.getId());
            if (c != null) e.removeCandidate(c);
        }

        refreshPoliticianList();
        refreshElectionList();
        candidateList.getItems().clear();
        politicianElectionList.getItems().clear();
        lblPoliticianDetails.setText("");
    }

    @FXML
    public void deleteElection() {
        Election selected = electionList.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select an election to delete.");
            return;
        }

        Election[] all = electionTable.toArray();
        ElectionHashTable newTable = new ElectionHashTable(all.length);
        for (Election e : all) {
            if (e != null && e.getId() != selected.getId()) {
                newTable.insert(e);
            }
        }
        electionTable = newTable;

        refreshElectionList();
        candidateList.getItems().clear();
    }

    @FXML
    public void deleteCandidate() {
        Election e = electionList.getSelectionModel().getSelectedItem();
        Candidate c = candidateList.getSelectionModel().getSelectedItem();
        if (e == null || c == null) {
            showAlert("Select an election and candidate to delete.");
            return;
        }

        e.removeCandidate(c);
        onElectionClicked();
    }

    @FXML
    public void searchPoliticians() {
        String name   = searchPolName.getText().toLowerCase();
        String party  = searchPolParty.getText().toLowerCase();
        String county = searchPolCounty.getText().toLowerCase();

        Politician[] all = politicianTable.toArray();
        int count = 0;
        for (Politician p : all) {
            if (p == null) continue;
            boolean matches = true;

            if (!name.isEmpty() && !p.getName().toLowerCase().contains(name)) matches = false;
            if (!party.isEmpty() && !p.getParty().toLowerCase().equals(party)) matches = false;
            if (!county.isEmpty() && !p.getCounty().toLowerCase().equals(county)) matches = false;

            if (matches) count++;
        }

        Politician[] results = new Politician[count];
        int idx = 0;
        for (Politician p : all) {
            if (p == null) continue;
            boolean matches = true;

            if (!name.isEmpty() && !p.getName().toLowerCase().contains(name)) matches = false;
            if (!party.isEmpty() && !p.getParty().toLowerCase().equals(party)) matches = false;
            if (!county.isEmpty() && !p.getCounty().toLowerCase().equals(county)) matches = false;

            if (matches) results[idx++] = p;
        }

        politicianSorter.sortByName(results);

        ObservableList<Politician> items = FXCollections.observableArrayList();
        for (Politician p : results) items.add(p);
        politicianList.setItems(items);
    }

    @FXML
    public void searchElections() {
        String type = searchElectionType.getText().toLowerCase();
        String yearText = searchElectionYear.getText();

        Integer year = null;
        if (!yearText.isEmpty()) {
            try {
                year = Integer.parseInt(yearText);
            } catch (NumberFormatException ex) {
                showAlert("Year must be a number.");
                return;
            }
        }

        Election[] all = electionTable.toArray();
        int count = 0;
        for (Election e : all) {
            if (e == null) continue;

            boolean matches = true;
            if (!type.isEmpty() && !e.getElectionType().toLowerCase().equals(type)) matches = false;
            if (year != null && e.getYear() != year) matches = false;

            if (matches) count++;
        }

        Election[] results = new Election[count];
        int idx = 0;
        for (Election e : all) {
            if (e == null) continue;

            boolean matches = true;
            if (!type.isEmpty() && !e.getElectionType().toLowerCase().equals(type)) matches = false;
            if (year != null && e.getYear() != year) matches = false;

            if (matches) results[idx++] = e;
        }

        electionSorter.sortByYear(results);

        ObservableList<Election> items = FXCollections.observableArrayList();
        for (Election e : results) items.add(e);
        electionList.setItems(items);
    }

    private void onPoliticianClicked() {
        Politician p = politicianList.getSelectionModel().getSelectedItem();
        if (p == null) return;

        lblPoliticianDetails.setText(
                "Name: " + p.getName() +
                        "\nParty: " + p.getParty() +
                        "\nCounty: " + p.getCounty()
        );

        Election[] all = electionTable.toArray();
        ObservableList<Election> related = FXCollections.observableArrayList();

        for (Election e : all) {
            if (e == null) continue;
            Node<Candidate> cur = e.getCandidates().getHead();
            while (cur != null) {
                if (cur.getValue().getPolitician().getId() == p.getId()) {
                    related.add(e);
                    break;
                }
                cur = cur.getNext();
            }
        }

        politicianElectionList.setItems(related);
    }

    private void onPoliticianElectionClicked() {
        Election e = politicianElectionList.getSelectionModel().getSelectedItem();
        if (e == null) return;

        electionList.getSelectionModel().select(e);
        onElectionClicked();
    }

    private void onElectionClicked() {
        Election e = electionList.getSelectionModel().getSelectedItem();
        if (e == null) {
            candidateList.getItems().clear();
            return;
        }

        Node<Candidate> cur = e.getCandidates().getHead();
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.getNext();
        }

        Candidate[] arr = new Candidate[count];
        cur = e.getCandidates().getHead();
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.getValue();
            cur = cur.getNext();
        }

        sortCandidatesByVotesDescending(arr);

        // highlight winners (top seats)
        int seats = e.getSeats();
        candidateList.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Candidate c, boolean empty) {
                super.updateItem(c, empty);
                if (empty || c == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(c.toString());
                    if (getIndex() < seats) {
                        setStyle("-fx-background-color: gold; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        ObservableList<Candidate> items = FXCollections.observableArrayList(arr);
        candidateList.setItems(items);
    }

    private void onCandidateClicked() {
        Candidate c = candidateList.getSelectionModel().getSelectedItem();
        if (c == null) return;

        Politician p = c.getPolitician();
        politicianList.getSelectionModel().select(p);
        onPoliticianClicked();
    }

    @FXML
    public void saveData() {
        try (PrintWriter pw = new PrintWriter("elections.csv")) {

            // politicians
            Politician[] pols = politicianTable.toArray();
            for (Politician p : pols) {
                if (p == null) continue;
                pw.println("POL," + p.getId() + "," + p.getName() + "," +
                        p.getParty() + "," + p.getCounty());
            }

            // elections + candidates
            Election[] els = electionTable.toArray();
            for (Election e : els) {
                if (e == null) continue;
                pw.println("ELE," + e.getId() + "," + e.getElectionType() + "," +
                        e.getLocation() + "," + e.getYear() + "," + e.getSeats());

                Node<Candidate> cur = e.getCandidates().getHead();
                while (cur != null) {
                    Candidate c = cur.getValue();
                    pw.println("CAN," + e.getId() + "," + c.getPolitician().getId() + "," +
                            c.getParty() + "," + c.getVotes());
                    cur = cur.getNext();
                }
            }

        } catch (Exception ex) {
            showAlert("Error saving data.");
        }
    }

    @FXML
    public void loadData() {
        politicianTable = new PoliticianHashTable(101);
        electionTable   = new ElectionHashTable(101);

        try (BufferedReader br = new BufferedReader(new FileReader("elections.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);

                switch (parts[0]) {
                    case "POL": {
                        int id = Integer.parseInt(parts[1]);
                        String name = parts[2];
                        String party = parts[3];
                        String county = parts[4];
                        politicianTable.insert(new Politician(id, name, party, county));
                        break;
                    }
                    case "ELE": {
                        int id = Integer.parseInt(parts[1]);
                        String type = parts[2];
                        String loc  = parts[3];
                        int year    = Integer.parseInt(parts[4]);
                        int seats   = Integer.parseInt(parts[5]);
                        electionTable.insert(new Election(id, type, loc, year, seats));
                        break;
                    }
                    case "CAN": {
                        int electionId = Integer.parseInt(parts[1]);
                        int polId      = Integer.parseInt(parts[2]);
                        String party   = parts[3];
                        int votes      = Integer.parseInt(parts[4]);

                        Election e = electionTable.get(electionId);
                        Politician p = politicianTable.get(polId);
                        if (e != null && p != null) {
                            e.addCandidate(new Candidate(p, e, votes, party));
                        }
                        break;
                    }
                }
            }

            refreshPoliticianList();
            refreshElectionList();
            candidateList.getItems().clear();
            politicianElectionList.getItems().clear();
            lblPoliticianDetails.setText("");

        } catch (Exception ex) {
            showAlert("Error loading data.");
        }
    }

    @FXML
    public void resetAll() {
        politicianTable = new PoliticianHashTable(101);
        electionTable   = new ElectionHashTable(101);

        refreshPoliticianList();
        refreshElectionList();
        candidateList.getItems().clear();
        politicianElectionList.getItems().clear();
        lblPoliticianDetails.setText("");
    }

    private void refreshPoliticianList() {
        ObservableList<Politician> items = FXCollections.observableArrayList();
        for (Politician p : politicianTable.toArray()) {
            if (p != null) items.add(p);
        }
        politicianList.setItems(items);
    }

    private void refreshElectionList() {
        ObservableList<Election> items = FXCollections.observableArrayList();
        for (Election e : electionTable.toArray()) {
            if (e != null) items.add(e);
        }
        electionList.setItems(items);
    }

    private void sortCandidatesByVotesDescending(Candidate[] arr) {
        // simple selection sort, descending by votes
        for (int i = 0; i < arr.length - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] != null && arr[maxIndex] != null &&
                        arr[j].getVotes() > arr[maxIndex].getVotes()) {
                    maxIndex = j;
                }
            }
            if (arr[i] != arr[maxIndex]) {
                Candidate temp = arr[i];
                arr[i] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
        }
    }

    private String prompt(String title, String header) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        Optional<String> result = dialog.showAndWait();
        return result.orElseThrow(() -> new RuntimeException("Cancelled"));
    }

    private String prompt(String title, String header, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(defaultValue);
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}