package com.company;
import java.util.ArrayList;
import static com.company.Main.countDownLatchReady;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private final Race race;
    private int speed;
    private String name;

    public String getName() { return name; }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
            try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatchReady.countDown();
             try {
                countDownLatchReady.await();
                final ArrayList <Stage> stages = race.getStages();
                for (Stage stage: stages){
                    stage.go(this);
                }
             } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}