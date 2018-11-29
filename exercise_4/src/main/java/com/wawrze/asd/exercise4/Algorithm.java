package com.wawrze.asd.exercise4;

import java.util.*;

public class Algorithm {

    private final Input input;
    int numberOfBanks;
    int firstBank;
    int secondBank;
    int[][] edgesMatrix;
    List<Set<Integer>> stronglyConnectedComponents;

    public Algorithm(final Input input) {
        this.input = input;
        stronglyConnectedComponents = new ArrayList<>();
    }

    public int[][] runAlgorithm() {
        prepareData();
        findStronglyConnectedComponents();
        int[][] result = new int[2][];
        result[0] = relations(firstBank);
        result[1] = relations(secondBank);
        return result;
    }

    private void prepareData() {
        numberOfBanks = input.getNumberOfBanks();
        firstBank = input.getFirstBank();
        secondBank = input.getSecondBank();
        edgesMatrix = new int[numberOfBanks][numberOfBanks];
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

    private void findStronglyConnectedComponents() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[][] timeStamps = new int[numberOfBanks][2];

        int activeNod = 0;
        stack.offerFirst(activeNod); // putting first node to stack
        timeStamps[0][0] = 1;
        int timeStamp = 2;
        do {
            boolean tmpCondition = true;
            // adding new node to stack
            for(int i = 0;i < numberOfBanks;i++) {
                if (edgesMatrix[activeNod][i] == 1 && timeStamps[i][0] == 0) {
                    activeNod = i;
                    timeStamps[i][0] = timeStamp;
                    stack.offerFirst(i);
                    timeStamp++;
                    tmpCondition = false;
                    break;
                }
            }
            // removing node from stack
            if(tmpCondition) {
                stack.pollFirst();
                timeStamps[activeNod][1] = timeStamp;
                timeStamp++;
                if(!stack.isEmpty()) {
                    activeNod = stack.peekFirst();
                }
                // if stack is empty - starting stack with first not visited node
                else {
                    for(int i = 0;i < numberOfBanks;i++) {
                        if(timeStamps[i][0] == 0) {
                            activeNod = i;
                            timeStamps[i][0] = timeStamp;
                            timeStamp++;
                            stack.offerFirst(i);
                            break;
                        }
                    }
                }
            }
        } while(!stack.isEmpty());

        int[][] transposedEdgesMatrix = transposeEdgesMatrix();
        int[] sortedTimeStamps = sortTimeStamps(timeStamps); // additional array with time stamps sorted decrement

        // putting on stack node with max time stamp
        int index = 0;
        boolean[] stronglyConnectedComponent = new boolean[numberOfBanks];
        for(int i = 0;i < numberOfBanks;i++) {
            if(timeStamps[i][1] == sortedTimeStamps[index]) {
                activeNod = i;
                timeStamps[i][0] = 0;
                index++;
                stack.offerFirst(activeNod);
                stronglyConnectedComponent[i] = true;
                break;
            }
        }
        do {
            boolean tmpCondition = true;
            // adding node to stack
            for(int i = 0;i < numberOfBanks;i++) {
                if(transposedEdgesMatrix[activeNod][i] == 1 && timeStamps[i][0] != 0) {
                    activeNod = i;
                    timeStamps[i][0] = 0;
                    stack.offerFirst(i);
                    stronglyConnectedComponent[i] = true;
                    tmpCondition = false;
                    break;
                }
            }
            // removing node from stack
            if(tmpCondition) {
                stack.pollFirst();
                if(!stack.isEmpty()) {
                    activeNod = stack.peekFirst();
                }
                // if stack is empty
                else {
                    // creating new set of nodes with strong coherency
                    Set<Integer> setOfStronglyConnectedComponents = new HashSet<>();
                    for(int i = 0;i < numberOfBanks;i++) {
                        if(stronglyConnectedComponent[i]) {
                            setOfStronglyConnectedComponents.add(i);
                            stronglyConnectedComponent[i] = false;
                        }
                    }
                    stronglyConnectedComponents.add(setOfStronglyConnectedComponents);
                    // adding to stack first not visited node with max time stamp
                    for(int i = 0;i < numberOfBanks;i++) {
                        if(timeStamps[i][1] == sortedTimeStamps[index]) {
                            if(timeStamps[i][0] != 0) {
                                activeNod = i;
                                timeStamps[i][0] = 0;
                                index++;
                                stack.offerFirst(activeNod);
                                stronglyConnectedComponent[i] = true;
                                break;
                            }
                            else {
                                i = -1;
                                index++;
                            }
                        }
                        if(index == numberOfBanks) {
                            break;
                        }
                    }
                }
            }
        } while(!stack.isEmpty());
        System.out.println(stronglyConnectedComponents.size());
    }

    private int[] sortTimeStamps(int[][] timeStamps) {
        int[] stampsToSort = new int[numberOfBanks];
        for(int i = 0;i < numberOfBanks;i++) {
            stampsToSort[i] = timeStamps[i][1];
        }
        int[] sortedTimeStamps = new int[numberOfBanks];
        int tmpValue = 0;
        int index = 0;
        for(int i = 0;i < numberOfBanks;i++) {
            for(int j = 0;j < numberOfBanks;j++) {
                if(stampsToSort[j] > tmpValue) {
                    tmpValue = stampsToSort[j];
                    index = j;
                }
            }
            sortedTimeStamps[i] = tmpValue;
            tmpValue = 0;
            stampsToSort[index] = 0;
        }
        return sortedTimeStamps;
    }

    private int[][] transposeEdgesMatrix() {
        int[][] transposedMatrix = new int[numberOfBanks][numberOfBanks];
        for(int i = 0;i < numberOfBanks;i++) {
            for(int j = 0;j < numberOfBanks;j++) {
                transposedMatrix[i][j] = edgesMatrix[j][i];
            }
        }
        return transposedMatrix;
    }

    private int[] relations(int bankNumber) {
        int index = 0;
        int[] result;
        for(int i = 0;i < stronglyConnectedComponents.size();i++) {
            if(stronglyConnectedComponents.get(i).contains(bankNumber - 1)) {
                index = i;
                break;
            }
        }
        result = new int[stronglyConnectedComponents.get(index).size()];
        int i = 0;
        for(Integer in : stronglyConnectedComponents.get(index)) {
            result[i] = in + 1;
            i++;
        }
        return result;
    }

}