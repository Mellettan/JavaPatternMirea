package pract_3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SafeSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public SafeSet() {
        set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(1);
    }
    /**
     * Функция «add» получает разрешение от семафора, добавляет элемент в набор,
     * а затем освобождает разрешение семафора.
     *
     * @throws InterruptedException  если текущий поток прерывается при получении семафора
     * @param  element  элемент, который нужно добавить в набор
     */
    public void add(T element) throws InterruptedException {
        semaphore.acquire();
        try {
            set.add(element);
        } finally {
            semaphore.release();
        }
    }
    /**
     * Функция «remove» получает разрешение от семафора, удаляет элемент из набора,
     * а затем освобождает разрешение семафора.
     *
     * @throws InterruptedException  если текущий поток прерывается при получении семафора
     * @param  element  элемент, который нужно удалить из набора
     */
    public void remove(T element) throws InterruptedException {
        semaphore.acquire();
        try {
            set.remove(element);
        } finally {
            semaphore.release();
        }
    }
    /**
     * Функция «contains» получает разрешение от семафора, проверяет, содержится ли элемент в наборе,
     * а затем освобождает разрешение семафора.
     *
     * @throws InterruptedException  если текущий поток прерывается при получении семафора
     * @param  element  элемент, который нужно проверить в наборе
     * @return          true, если набор содержит указанный элемент, в противном случае false
     */
    public boolean contains(T element) throws InterruptedException {
        semaphore.acquire();
        try {
            return set.contains(element);
        } finally {
            semaphore.release();
        }
    }
    /**
     * Функция «clear» получает разрешение от семафора, очищает набор, а затем освобождает разрешение семафора.
     *
     * @throws InterruptedException  если текущий поток прерывается при получении семафора
     */
    public void clear() throws InterruptedException {
        semaphore.acquire();
        try {
            set.clear();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeSet<String> safeSet = new SafeSet<>();
        safeSet.add("Hello");
        safeSet.add("Hello");
        safeSet.add("World");
        System.out.println("Изначальный safeSet: " + safeSet.set);
        System.out.println("Удаляем World");
        safeSet.remove("World");
        System.out.println("safeSet после удаления World: " + safeSet.set);
        System.out.println("Есть ли World в safeSet?: " + safeSet.contains("World"));
        System.out.println("Очищаем safeSet");
        safeSet.clear();
        System.out.println("safeSet после clear: " + safeSet.set);
    }
}