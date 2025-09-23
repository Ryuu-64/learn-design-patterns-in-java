package org.ryuu.learn.designpatterns.structural.composite;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class CompositeTest {
    private interface Task extends Component {
        void execute();
    }

    private static class LeafTask implements Task, Leaf {
        @Override
        public void execute() {
            System.out.println("Leaf task executed.");
        }
    }

    private record CompositeTask(List<Task> tasks) implements Task, Composite {
        @Override
        public void execute() {
            System.out.println("Composite task executed. leaf task count=" + tasks.size());
            for (Task task : tasks) {
                task.execute();
            }
        }
    }

    @Test
    void test() {
        Task task11 = new LeafTask();
        Task task12 = new LeafTask();

        CompositeTask task1 = new CompositeTask(Arrays.asList(task11, task12));
        Task task2 = new LeafTask();
        Task task3 = new LeafTask();
        Task task4 = new LeafTask();

        CompositeTask task = new CompositeTask(Arrays.asList(task1, task2, task3, task4));
        task.execute();
    }
}
