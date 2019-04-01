package Concurrent;

public class ThreadLocalSynchronization {


    public class Bank {
        private ThreadLocal<Integer> account = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 10;
            }
        };

        public int getAccount() {
            return account.get();
        }


        public void saveAccount(int money) {
            account.set(account.get() + money);
        }


    }


    class ThreadLocalThread implements Runnable {

        private Bank bank;

        public ThreadLocalThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {

            for (int i = 0; i < 20; i++) {
                bank.saveAccount(10);

                System.out.println(Thread.currentThread().getName() + "-->当前余额：" + bank.getAccount() + "元。");
            }

        }
    }


    public void userReentrantLockThread() {
        Bank bank = new Bank();
        ThreadLocalThread threadLocalThread = new ThreadLocalThread(bank);

        Thread thread1 = new Thread(threadLocalThread);
        Thread thread2 = new Thread(threadLocalThread);

        thread1.start();
        thread2.start();


    }

    public static void main(String[] args) {
        ThreadLocalSynchronization threadLocalSynchronization = new ThreadLocalSynchronization();
        threadLocalSynchronization.userReentrantLockThread();
    }


}
