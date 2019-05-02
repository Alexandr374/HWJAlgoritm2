package com.company;

import java.util.Random;

public class SortArray {
    private static int numElements = 100000;
    private static int numExperiment = 10;
    private static int min = -100000;
    private static int max = 100000;

    public static void main(String[] args) {
        long [] selectionSortTimers = new long[numExperiment];
        long [] insertionSortTimers = new long[numExperiment];
        long selectionSortTotalTime = 0;
        long insertionSortTotalTimes = 0;

        for (int i = 0; i < numExperiment; i++) {
            MyArray<Integer> myArray1 = new MyArray<>();
            arrayRandomFill(myArray1,numElements,min,max);
            MyArray<Integer> myArray2 = new MyArray<>(myArray1);

            long start = System.currentTimeMillis();
            myArray1.selectionSort(Integer::compareTo);
            long end = System.currentTimeMillis();
            selectionSortTimers[i] = end - start;
            selectionSortTotalTime += selectionSortTimers[i];

            start = System.currentTimeMillis();
            myArray2.insertSort(Integer::compareTo);
            end = System.currentTimeMillis();
            insertionSortTimers[i] = end - start;
            insertionSortTotalTimes += insertionSortTimers[i];
        }

        System.out.println("|  #  | selection | insertion |");
        System.out.println("_______________________________");
        for (int i = 0; i < numExperiment; i++) {
            System.out.printf("|%5d|%11.2f|%11.2f|%n", i + 1, selectionSortTimers[i]/1000.0, insertionSortTimers[i]/1000.0);
        }

        System.out.printf("| avg |%11.2f|%11.2f|", selectionSortTotalTime/1000.0/numExperiment, insertionSortTotalTimes/1000.0/numExperiment);
    }

    public static void arrayRandomFill (MyArray<Integer> myArray, int count, int a, int b){
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            myArray.add(random.nextInt((b - a + 1) + a));
        }
    }
}
