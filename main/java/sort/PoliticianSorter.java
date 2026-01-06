package sort;

import models.Politician;

public class PoliticianSorter {

    /**
     * Sorts the politician by name in ascending order using selection sort
     * @param data - array consisting of all politicians
     */
    public void sortByName(Politician[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] != null && data[minIndex] != null &&
                        data[j].getName().compareTo(data[minIndex].getName()) < 0) {
                    minIndex = j;
                }
            }

            Politician temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }

    /**
     * Sorts the politicians by party name in ascending order using selection sort
     */
    public void sortByParty(Politician[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] != null && data[minIndex] != null &&
                        data[j].getParty().compareTo(data[minIndex].getParty()) < 0) {
                    minIndex = j;
                }
            }

            Politician temp = data[i];
            data[i] = data[minIndex];
            data[minIndex] = temp;
        }
    }
}
