package pract_4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Класс представляет простую имплементацию ExecutorService с фиксированным числом потоков.
 * Позволяет передавать задачи на выполнение и завершать работу.
 */
public class MyExecutorService {
    private final List<WorkerThread> workerThreads; // Список рабочих потоков
    private final BlockingQueue<Runnable> taskQueue; // Очередь задач для выполнения

    /**
     * Конструктор класса. Создает заданное количество рабочих потоков.
     *
     * @param numThreads Количество потоков, которое необходимо создать.
     */
    public MyExecutorService(int numThreads) {
        this.workerThreads = new ArrayList<>(numThreads);
        this.taskQueue = new LinkedBlockingQueue<>();

        for (int i = 0; i < numThreads; i++) {
            WorkerThread workerThread = new WorkerThread();
            workerThreads.add(workerThread);
            workerThread.start();
        }
    }
    /**
     * Добавляет задачи в очередь задач для выполнения.
     *
     * @param  task  Задача, которую необходимо добавить в очередь
     */
    public void submit(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Внутренний класс представляет поток выполнения задач.
     */
    private class WorkerThread extends Thread {
        /**
         * Метод непрерывно извлекает задачи из очереди taskQueue и выполняет их.
         * Если поток прерывается, то метод завершается.
         */
        @Override
        public void run() {
            while (true) {
                try {
                    Runnable task = taskQueue.take(); // Извлекаем задачу из очереди
                    task.run(); // Выполняем задачу
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
    /**
     * Выполняет завершение работы MyExecutorService.
     * Прерывает все рабочие потоки.
     * После вызова этого метода, MyExecutorService больше не может быть использован.
     */
    public void shutdown() {
        for (WorkerThread workerThread : workerThreads) {
            workerThread.interrupt();
        }
    }

    public static void main(String[] args) {
        MyExecutorService executorService = new MyExecutorService(5);

        executorService.submit(() -> System.out.println("Task 1 (Thread: " + Thread.currentThread().threadId() + ")"));
        executorService.submit(() -> System.out.println("Task 2 (Thread: " + Thread.currentThread().threadId() + ")"));
        executorService.submit(() -> System.out.println("Task 3 (Thread: " + Thread.currentThread().threadId() + ")"));
        executorService.submit(() -> System.out.println("Task 4 (Thread: " + Thread.currentThread().threadId() + ")"));
        executorService.submit(() -> System.out.println("Task 5 (Thread: " + Thread.currentThread().threadId() + ")"));

        executorService.shutdown();
    }
}
