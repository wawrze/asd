package com.wawrze.ads.exercise3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Kruskal implements Algorithm {

    class KruskalsAlgorithm {

        class Edge implements Comparable<Edge> {
            int point1;
            int point2;
            int weight;

            public Edge(int point1, int point2, int weight) {
                this.point1 = point1;
                this.point2 = point2;
                this.weight = weight;
            }

            @Override
            public int compareTo(Edge o) {
                return this.weight - o.weight;
            }
        }

        class Subset
        {
            int parent;
            int rank;
        }

        private int pointsQuantity;
        private int edgesQuantity;
        private Edge[] edges;

        public KruskalsAlgorithm(int pointsQuantity, int edgesQuantity, List<int[]> edgeList) {
            this.pointsQuantity = pointsQuantity;
            this.edgesQuantity = edgesQuantity;
            edges = new Edge[this.edgesQuantity];
            for (int i = 0; i< edgesQuantity; ++i) {
                edges[i] = new Edge(edgeList.get(i)[0] - 1, edgeList.get(i)[1] - 1, edgeList.get(i)[2]);
            }
        }

        public List<int[]> runKruskalsAlgorithm() {
            Edge[] result = new Edge[pointsQuantity];
            Arrays.sort(edges);
            Subset subsets[] = new Subset[pointsQuantity];
            for (int i = 0; i < pointsQuantity; i++) {
                subsets[i] = new Subset();
            }
            for (int i = 0; i < pointsQuantity; i++) {
                subsets[i].parent = i;
                subsets[i].rank = 0;
            }
            int edge = 0;
            for (int i = 0; edge < pointsQuantity - 1; ) {
                Edge nextEdge = edges[i++];
                int point1 = findSubsetParent(subsets, nextEdge.point1);
                int point2 = findSubsetParent(subsets, nextEdge.point2);
                if (point1 != point2) {
                    result[edge++] = nextEdge;
                    connectPoints(subsets, point1, point2);
                }
            }
            List<int[]> resultList = new ArrayList<>();
            for (int i = 0; i < edge; ++i) {
                int[] tmpArray = {result[i].point1 + 1, result[i].point2 + 1, result[i].weight};
                resultList.add(tmpArray);
            }
            return resultList;
        }

        private int findSubsetParent(Subset[] subsets, int i) {
            if(subsets[i].parent != i) {
                subsets[i].parent = findSubsetParent(subsets, subsets[i].parent);
            }
            return subsets[i].parent;
        }

        private void connectPoints(Subset subsets[], int point1, int point2) {
            int point1root = findSubsetParent(subsets, point1);
            int point2root = findSubsetParent(subsets, point2);

            if(subsets[point1root].rank < subsets[point2root].rank) {
                subsets[point1root].parent = point2root;
            }
            else if(subsets[point1root].rank > subsets[point2root].rank) {
                subsets[point2root].parent = point1root;
            }
            else {
                subsets[point2root].parent = point1root;
                subsets[point1root].rank++;
            }
        }

    }

    private PrintWriter writer;
    private Scanner reader;

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0303.txt\"");
            System.out.println("(x) Exit to main menu");
            o = sc.nextLine();
            option(o);
        } while(!o.equals("x"));
    }

    private void option(String o) {
        switch (o) {
            case "f":
                runAlgorithm();
                break;
            default:
                break;
        }
    }

    public void runAlgorithm() {
        File file = null;
        try {
            file = new File("In0303.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return;
        }
        int n = reader.nextInt();
        int m = reader.nextInt();
        reader.nextLine();
        int pointer = 1;
        List<int[]> edges = new ArrayList<>();
        while(pointer <= n) {
            Scanner scanner = new Scanner(reader.nextLine());
            boolean condition = true;
            while(condition) {
                try {
                    int point1 = scanner.nextInt();
                    int weight = scanner.nextInt();
                    int point2 = pointer;
                    if(point1 > point2) {
                        int temp = point1;
                        point1 = point2;
                        point2 = temp;
                    }
                    if(point1 < pointer) {
                        continue;
                    }
                    int[] edge = new int[3];
                    edge[0] = point1;
                    edge[1] = point2;
                    edge[2] = weight;
                    edges.add(edge);
                }
                catch(Exception e) {
                    condition = false;
                }
            }
            pointer++;
        }
        reader.close();
        if(edges.size() != m) {
            System.out.println("Incorrect input!");
            return;
        }
        List<int[]> result = (new KruskalsAlgorithm(n, m, edges)).runKruskalsAlgorithm();
        int minWeight = 0;
        for(int i = 0;i < result.size();i++) {
            minWeight += result.get(i)[2];
        }
        StringBuilder resultStringified = new StringBuilder();
        resultStringified.append(minWeight + "\n");
        result.forEach(r -> {
            resultStringified.append(r[0]);
            resultStringified.append(" ");
            resultStringified.append(r[1]);
            resultStringified.append("[");
            resultStringified.append(r[2]);
            resultStringified.append("], ");
        });
        fileWriter(resultStringified.toString());
        System.out.println("Result:\n" + resultStringified + "\nsaved to file Out0303.txt");
    }

    private void fileWriter(String result) {
        File file = new File("Out0303.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch(IOException e) {}
        }
        try {
            writer = new PrintWriter(file.getName());
        }
        catch(IOException e) {
            System.out.println("There was an error during write to file Out303.txt: " + e.getMessage() + "!");
            return;
        }
        writer.println(result);
        writer.close();
    }

}