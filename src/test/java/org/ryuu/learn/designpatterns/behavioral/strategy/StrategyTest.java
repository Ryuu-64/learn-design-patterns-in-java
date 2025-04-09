package org.ryuu.learn.designpatterns.behavioral.strategy;

import org.junit.jupiter.api.Test;

public class StrategyTest {
    private interface SortingStrategy extends StrategyInterface {
        void sort(int[] array);
    }

    private static class BubbleSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            // Implement Bubble Sort algorithm
            System.out.println("Sorting using Bubble Sort");
        }
    }

    private static class MergeSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            // Implement Merge Sort algorithm
            System.out.println("Sorting using Merge Sort");
        }
    }

    private static class QuickSortStrategy implements SortingStrategy {
        @Override
        public void sort(int[] array) {
            // Implement Quick Sort algorithm
            System.out.println("Sorting using Quick Sort");
        }
    }

    private static class SortingContext implements Context {
        private SortingStrategy sortingStrategy;

        public SortingContext(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy;
        }

        public void setSortingStrategy(SortingStrategy sortingStrategy) {
            this.sortingStrategy = sortingStrategy;
        }

        public void performSort(int[] array) {
            sortingStrategy.sort(array);
        }
    }

    @Test
    void test() {
        SortingContext sortingContext = new SortingContext(new BubbleSortStrategy());
        int[] array1 = {5, 2, 9, 1, 5};
        sortingContext.performSort(array1);

        sortingContext.setSortingStrategy(new MergeSortStrategy());
        int[] array2 = {8, 3, 7, 4, 2};
        sortingContext.performSort(array2);

        sortingContext.setSortingStrategy(new QuickSortStrategy());
        int[] array3 = {6, 1, 3, 9, 5};
        sortingContext.performSort(array3);
    }
}
