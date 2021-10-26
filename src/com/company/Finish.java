package com.company;
import static com.company.Main.*;


public class Finish extends Stage {
    @Override
    public void go(Car c) {
        countDownLatchStop.countDown();
        try { sfWin.acquire();
            System.out.println(c.getName() + " пересёк финишную черту и ПОБЕДИЛ ");
            } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            countDownLatchStop.await();
             service.shutdown();
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
    }
}