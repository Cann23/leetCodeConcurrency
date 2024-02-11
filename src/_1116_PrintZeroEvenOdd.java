import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class _1116_PrintZeroEvenOdd {
    public static void main(String[] args) {
        System.out.println("PrintZeroEvenOdd");
        }
}


class ZeroEvenOdd {

    private Semaphore semaphoreZero;
    private Semaphore semaphoreEven;
    private Semaphore semaphoreOdd;
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
        this.semaphoreZero = new Semaphore(1);
        this.semaphoreEven = new Semaphore(0);
        this.semaphoreOdd = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphoreZero.acquire();
            printNumber. accept(0);
            if (i % 2 == 1) {
                semaphoreOdd.release();
            } else {
                semaphoreEven.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                semaphoreEven.acquire();
                printNumber. accept(i);
                semaphoreZero.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) {
                semaphoreOdd.acquire();
                printNumber. accept(i);
                semaphoreZero.release();
            }
        }
    }
}