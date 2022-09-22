import java.util.concurrent.Semaphore;

public class MultithreadingTest {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        new Philosopher(sem, "A").start();
        new Philosopher(sem, "B").start();
        new Philosopher(sem, "C").start();
        new Philosopher(sem, "D").start();
        new Philosopher(sem, "E").start();

    }
}

class Philosopher extends Thread {
    private Semaphore sem;
    private String name;

    Philosopher(Semaphore sem, String name) {
        this.sem = sem;
        this.name = name;
    }

    public void run() {
        try {
            //Запрашиваем у семафора разрешение на выполнение
            sem.acquire();
            System.out.println(name + " садится за стол");
            // философ ест
            sleep(300);
            System.out.println(name + " поел! Он выходит из-за стола");
            sem.release();
            // философ ушел, освободив место другим
            sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Что-то пошло не так!");
        }
    }
}