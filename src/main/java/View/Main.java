package View;

import Controller.IslandController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        IslandController islandController = new IslandController();

        ScheduledExecutorService lifeCycleExecutorService = Executors.newScheduledThreadPool(3);
        lifeCycleExecutorService.scheduleAtFixedRate(() -> {
            islandController.lifeCycle();
        }, 0, 5, TimeUnit.SECONDS);


        ScheduledExecutorService growGrassExecutorService = Executors.newScheduledThreadPool(1);
        growGrassExecutorService.scheduleAtFixedRate(() -> islandController.growGrassOnCell(), 0, 10, TimeUnit.SECONDS);

        ScheduledExecutorService statisticExecutorService = Executors.newScheduledThreadPool(1);
        statisticExecutorService.scheduleAtFixedRate(() -> System.out.println(islandController), 1, 5, TimeUnit.SECONDS);


    }
}
