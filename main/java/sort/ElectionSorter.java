package sort;

import models.Election;

public class ElectionSorter {
    public void sortByYear(Election[] data){
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] != null && data[minIndex] != null &&
                        data[j].getYear() < data[minIndex].getYear()) {
                    minIndex = j;
                }
            }

            Election temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }
    public void sortBySeats(Election[] data){
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] != null && data[minIndex] != null &&
                        data[j].getSeats() < data[minIndex].getSeats()) {
                    minIndex = j;
                }
            }

            Election temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }
    public void sortByType(Election[] data){
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] != null && data[minIndex] != null &&
                        data[j].getElectionType().compareToIgnoreCase(data[minIndex].getElectionType()) < 0) {
                    minIndex = j;
                }
            }

            Election temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }
}
