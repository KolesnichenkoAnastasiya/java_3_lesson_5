package com.company;
import java.util.concurrent.*;

public class Main {
public static final int CARS_COUNT = 4;
static final CountDownLatch countDownLatchReady = new CountDownLatch(CARS_COUNT);
static final CountDownLatch countDownLatchStop = new CountDownLatch(CARS_COUNT);
static Semaphore sf = new Semaphore((CARS_COUNT)/2);
static Semaphore sfWin = new Semaphore(1);
static ExecutorService service = Executors.newFixedThreadPool(CARS_COUNT);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40), new Finish());
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        service.execute(() -> {
            try {
                for (Car car: cars) {
            new Thread(car).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        countDownLatchReady.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        countDownLatchStop.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        service.shutdown();
    }
}
