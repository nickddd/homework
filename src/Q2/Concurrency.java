package Q2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


class Concurrency {

    static class MyBlockingQueue<T> {

        private final T[] items;

        final ReentrantLock lock = new ReentrantLock();

        private final Condition notEmpty = lock.newCondition();

        private final Condition notFull = lock.newCondition();

        private int takeIndex, putIndex, count = 0;
        private int size;

        MyBlockingQueue(int size) {
            this.items = (T[]) new Object[size];
            this.size = size;
        }

        public void put(T t) {
            lock.lock();
            try {
                if (count == this.size) {
                    System.out.println(Thread.currentThread().getName() + ": queue  is full.");
                    notFull.await();
                }
                items[putIndex] = t;
                putIndex = (putIndex + 1) % this.size;
                ++count;
                notEmpty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public T take() {
            lock.lock();
            T t = null;
            try {
                if (count == 0) {
                    System.out.println(Thread.currentThread().getName() + ": queue  is empty.");
                    notEmpty.await();
                }
                t = items[takeIndex];
                items[takeIndex] = null;

                takeIndex = (takeIndex + 1) % this.size;
                --count;
                notFull.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return t;
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<String>(6);
        Thread put1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.put("product:" + i);
                    System.out.println(Thread.currentThread().getName() + " put " + "product" + i);
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread put2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    queue.put("product:" + i);
                    System.out.println(Thread.currentThread().getName() + " put " + "product" + i);
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread take1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    System.out.println(Thread.currentThread().getName() + " takes " + queue.take());
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread take2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 11; i++) {
                    System.out.println(Thread.currentThread().getName() + " takes " + queue.take());
                    try {
                        Thread.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        put1.start();
        put2.start();
        take1.start();
        take2.start();

    }

}