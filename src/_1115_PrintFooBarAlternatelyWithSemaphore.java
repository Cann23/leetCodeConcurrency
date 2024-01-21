import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1115_PrintFooBarAlternatelyWithSemaphore {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("PrintFooBarAlternatelyWithSemaphores");

        FooBar2 fooBar = new FooBar2(5);
        Thread fooThread = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.print("foo"));
            } catch (Exception ignore) {
            }
        });
        Thread barThread = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.print("bar"));
            } catch (Exception ignore) {
            }
        });
        fooThread.start();
        barThread.start();

        fooThread.join();
        barThread.join();
    }
}

class FooBar2 {

    private Semaphore semaphoreFoo;
    private Semaphore semaphoreBar;
    private int n;

    public FooBar2(int n) {
        this.n = n;
        semaphoreFoo = new Semaphore(1);
        semaphoreBar = new Semaphore(0);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            semaphoreFoo.acquire();
            printFoo.run();
            semaphoreBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            semaphoreBar.acquire();
            printBar.run();
            semaphoreFoo.release();
        }
    }
}