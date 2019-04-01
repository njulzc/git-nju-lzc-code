package MaxMin;

import java.util.concurrent.atomic.AtomicInteger;

public class OutQueueNum {


        public static AtomicInteger count = new AtomicInteger(0);

        public static void main(String[] args) throws InterruptedException {
            for (int i = 0; i < 10; i++) {
                new Thread() {
                    public void run() {
                        for (int j = 0; j < 10; j++) {
                            count.getAndIncrement();
                            System.out.println("AtomicInteger count: " + count);
                        }
                    }
                }.start();
            }
            Thread.sleep(1000);
            System.out.println("AtomicInteger count: " + count);
        }


}
