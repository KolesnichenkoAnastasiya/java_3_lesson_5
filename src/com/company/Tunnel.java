package com.company;
import static com.company.Main.sf;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
        try {
            try {sf.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
               sf.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

