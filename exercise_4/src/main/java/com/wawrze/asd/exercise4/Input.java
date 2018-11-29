package com.wawrze.asd.exercise4;

public final class Input {

    private final int numberOfBanks;
    private final int safetyFactor;
    private final double chfPrice;
    private final double eurPrice;
    private final int firstBank;
    private final int secondBank;
    private final double[][] srMatrix;

    public Input(final int numberOfBanks, final int safetyFactor, final double chfPrice, final double eurPrice,
                 final int firstBank, final int secondBank, final double[][] srMatrix) {
        this.numberOfBanks = numberOfBanks;
        this.safetyFactor = safetyFactor;
        this.chfPrice = chfPrice;
        this.eurPrice = eurPrice;
        this.firstBank = firstBank;
        this.secondBank = secondBank;
        this.srMatrix = srMatrix;
    }

    public int getNumberOfBanks() {
        return numberOfBanks;
    }

    public int getSafetyFactor() {
        return safetyFactor;
    }

    public double getChfPrice() {
        return chfPrice;
    }

    public double getEurPrice() {
        return eurPrice;
    }

    public int getFirstBank() {
        return firstBank;
    }

    public int getSecondBank() {
        return secondBank;
    }

    public double[][] getSrMatrix() {
        return srMatrix;
    }

}