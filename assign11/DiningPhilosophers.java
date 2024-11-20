import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Philosopher implements Runnable {
    private final int id;
    private final Lock leftChopstick;
    private final Lock rightChopstick;

    public Philosopher(int id, Lock leftChopstick, Lock rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking...");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Philosopher " + id + " is eating...");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (tryToEat()) {
                    eat();
                    releaseChopsticks();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean tryToEat() {
        if (leftChopstick.tryLock()) {
            if (rightChopstick.tryLock()) {
                return true;
            } else {
                leftChopstick.unlock(); // Release left chopstick if right not available
            }
        }
        return false;
    }

    private void releaseChopsticks() {
        leftChopstick.unlock();
        rightChopstick.unlock();
        System.out.println("Philosopher " + id + " released chopsticks.");
    }
}

public class DiningPhilosophers {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        Lock[] chopsticks = new ReentrantLock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            chopsticks[i] = new ReentrantLock();
        }

        Thread[] philosophers = new Thread[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftChopstick = chopsticks[i];
            Lock rightChopstick = chopsticks[(i + 1) % numPhilosophers];

            philosophers[i] = new Thread(new Philosopher(i, leftChopstick, rightChopstick));
            philosophers[i].start();
        }

        for (Thread philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
