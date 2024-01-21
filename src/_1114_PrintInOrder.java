import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1114_PrintInOrder {
    static Foo foo = new Foo();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("leetCodeConcurrency");

        List<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3));
        foo.first(() -> System.out.println(nums.get(0)));
        foo.second(() -> System.out.println(nums.get(1)));
        foo.third(() -> System.out.println(nums.get(2)));
    }
}

class Foo {

    private final Lock lock = new ReentrantLock();
    private final Condition firstThreadCompleted = lock.newCondition();
    private final Condition secondThreadCompleted = lock.newCondition();
    boolean isFirstThreadCompleted = false;
    boolean isSecondThreadCompleted = false;


    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        lock.lock();
        try {
            printFirst.run();
            isFirstThreadCompleted = true;
            firstThreadCompleted.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        lock.lock();
        try {
            while (!isFirstThreadCompleted) {
                firstThreadCompleted.await();
            }
            printSecond.run();
            isSecondThreadCompleted = true;
            secondThreadCompleted.signal();
        } finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        lock.lock();
        try {
            while (!isSecondThreadCompleted) {
                secondThreadCompleted.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}