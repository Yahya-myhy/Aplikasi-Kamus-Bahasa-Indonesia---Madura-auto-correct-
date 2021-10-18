package com.example.skripsi.cobain;


public class algorithm {
    private final String frisWord;
    private final String seconword;


    public algorithm(String frisWord, String seconword) {
        this.frisWord = frisWord;
        this.seconword = seconword;
    }
    //terhubung dgn mainactivity (proses levenshtein)
    public float editDistance(String frisWord, String seconword) {
        int numberOfColumns;
        int numberOfRows;
        int[][] array;
        int optDistance;
        double opt;
        double colms;
        double rows;
        //levenstain
        String[] string1 = frisWord.split("");
        String[] string2 = seconword.split("");
        numberOfColumns = string1.length;
        numberOfRows = string2.length;
        array = new int[numberOfColumns][numberOfRows];
        for (int i = 0; i < numberOfColumns; i++) {
            array[i][0] = i;
        }
        for (int j = 0; j < numberOfRows; j++) {
            array[0][j] = j;
        }
        for (int i = 1; i < numberOfColumns; i++) {
            for (int j = 1; j < numberOfRows; j++) {
                int insert = array[i - 1][j] + 1;
                int delete = array[i][j - 1] + 1;
                int replace = array[i - 1][j - 1] + getReplaceCost(string1[i], string2[j]);
                int min = Math.min(insert, Math.min(delete, replace));
                array[i][j] = min;
            }
        }
        optDistance = array[numberOfColumns - 1][numberOfRows - 1];
        //similarity
        opt=optDistance;
        colms=numberOfColumns-1;
        rows=numberOfRows-1;
        if (numberOfColumns==numberOfRows){
            float similarity;
            similarity = (float) (100*(1-opt/rows));
            return similarity;
        }
        else if (numberOfColumns<numberOfRows){
            float similarity;
            similarity = (float) (100*(1-opt/colms));
            return similarity;
        }
        else {
            float similarity;
            similarity = (float) (100*(1-opt/rows));
            return similarity;
        }
    }

    private int getReplaceCost(String characterFromFirstString, String characterFromSecondString) {
        if (characterFromFirstString.equals(characterFromSecondString)) {
            return 0;
        } else {
            return 1;
        }
    }
}
