import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class _1195_FizzBuzzMultithreaded {
    public static void main(String[] args) {
        System.out.println("FizzBuzzMultithreaded");
    }
}

class FizzBuzz {
    private int n;
    private Semaphore semaphoreFizz;
    private Semaphore semaphoreBuzz;
    private Semaphore semaphoreFizzBuzz;
    private Semaphore semaphoreNumber;

    public FizzBuzz(int n) {
        this.n = n;
        this.semaphoreFizz = new Semaphore(0);
        this.semaphoreBuzz = new Semaphore(0);
        this.semaphoreFizzBuzz = new Semaphore(0);
        this.semaphoreNumber = new Semaphore(1);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                semaphoreFizz.acquire();
                printFizz.run();
                findNextSemaphore(i);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                semaphoreBuzz.acquire();
                printBuzz.run();
                findNextSemaphore(i);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                semaphoreFizzBuzz.acquire();
                printFizzBuzz.run();
                findNextSemaphore(i);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                semaphoreNumber.acquire();
                printNumber.accept(i);
                findNextSemaphore(i);
            }
        }
    }

    private void findNextSemaphore(int i) {
        if ((i +1) % 15 == 0) {
            semaphoreFizzBuzz.release();
        } else if ((i +1) % 3 == 0) {
            semaphoreFizz.release();
        } else if ((i +1) % 5 == 0) {
            semaphoreBuzz.release();
        } else {
            semaphoreNumber.release();
        }
    }
}