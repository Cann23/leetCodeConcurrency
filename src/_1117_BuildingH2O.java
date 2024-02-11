import java.util.concurrent.Semaphore;

public class _1117_BuildingH2O {
    public static void main(String[] args) {
        System.out.println("BuildingH2O");
    }
}

class H2O {
    private Semaphore semaphoreHydrogen;
    private Semaphore semaphoreOxygen;

    public H2O() {
        this.semaphoreHydrogen = new Semaphore(2);
        this.semaphoreOxygen = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreHydrogen.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        if (semaphoreHydrogen.availablePermits() == 0) {
            semaphoreOxygen.release();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreOxygen.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        semaphoreHydrogen.release(2);
    }
}
