package com.wawrze.asd.exercise4;

public class Algorithm {

    private final Input input;
    int numberOfBanks;
    int firstBank;
    int secondBank;
    double[][] edgesMatrix;

    public Algorithm(final Input input) {
        this.input = input;
    }

    public int[][] runAlgorithm() {
        prepareData();
        int[][] result = new int[2][];
        result[0] = relations(firstBank);
        result[1] = relations(secondBank);
        return result;
    }

    private void prepareData() {
        numberOfBanks = input.getNumberOfBanks();
        firstBank = input.getFirstBank();
        secondBank = input.getSecondBank();
        for(int i = 0;i < numberOfBanks;i++) {
            for(int j = 0;j < numberOfBanks;j++) {
                if(input.getSafetyFactor() * (1 - input.getSrMatrix()[i][j])
                        > input.getChfPrice() - input.getEurPrice()) {
                    edgesMatrix[i][j] = 1;
                }
                else {
                    edgesMatrix[i][j] = 0;
                }
            }
        }
    }

    private int[] relations(int bankNumber) {
        //run for bank one and bank two
        return null;
    }

}