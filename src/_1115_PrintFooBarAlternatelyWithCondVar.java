import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1115_PrintFooBarAlternatelyWithCondVar {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("PrintFooBarAlternatelyWithConditionalVariables");

        FooBar fooBar = new FooBar(5);
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

class FooBar {

    private Lock lock = new ReentrantLock();
    private Condition conditionFooPrinted = lock.newCondition();
    private Condition conditionBarPrinted = lock.newCondition();
    private boolean isFooPrinted = false;

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            lock.lock();
            try {
                while (isFooPrinted) {
                    conditionBarPrinted.await();
                }
                printFoo.run();
                isFooPrinted = true;
                conditionFooPrinted.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            lock.lock();
            try {
                while (!isFooPrinted) {
                    conditionFooPrinted.await();
                }
                printBar.run();
                isFooPrinted = false;
                conditionBarPrinted.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
