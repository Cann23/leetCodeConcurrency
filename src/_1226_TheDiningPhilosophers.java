import java.util.concurrent.Semaphore;

public class _1226_TheDiningPhilosophers {
    public static void main(String[] args) {
        System.out.println("TheDiningPhilosophers");
    }
}

class DiningPhilosophers {

    Object leftFork;
    Object rightFork;

    public DiningPhilosophers() {
        leftFork = new Object();
        rightFork = new Object();
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        synchronized (leftFork) {
            synchronized (rightFork) {
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
            }
        }

    }
}