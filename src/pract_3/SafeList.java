package pract_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeList<T> {
    private final List<T> list;
    private final Lock lock;

    public SafeList() {
        list = new ArrayList<>();
        lock = new ReentrantLock();
    }
    /**
     * Добавляет указанный элемент в список, удерживая блокировку.
     *
     * @param  element элемент, который нужно добавить в список
     */
    public void add(T element) {
        lock.lock();
        try {
            list.add(element);
        } finally {
            lock.unlock();
        }
    }
    /**
     * Удаляет указанный элемент из списка, удерживая блокировку.
     *
     * @param  element  элемент, который нужно удалить из списка
     */
    public void remove(T element) {
        lock.lock();
        try {
            list.remove(element);
        } finally {
            lock.unlock();
        }
    }
    /**
     * Проверяет, содержит ли список указанный элемент, удерживая блокировку.
     *
     * @param  element  элемент, который нужно проверить в наборе
     * @return          true, если список содержит указанный элемент, в противном случае false
     */
    public boolean contains(T element) {
        lock.lock();
        try {
            return list.contains(element);
        } finally {
            lock.unlock();
        }
    }
    /**
     * Очищает список, удерживая блокировку.
     */
    public void clear() {
        lock.lock();
        try {
            list.clear();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SafeList<String> safeList = new SafeList<>();
        safeList.add("Hello");
        safeList.add("Hello");
        safeList.add("World");
        System.out.println("Изначальный safeList: " + safeList.list);
        System.out.println("Удаляем World");
        safeList.remove("World");
        System.out.println("safeList после удаления World: " + safeList.list);
        System.out.println("Есть ли World в safeList?: " + safeList.contains("World"));
        System.out.println("Очищаем safeList");
        safeList.clear();
        System.out.println("safeList после clear: " + safeList.list);
    }
}
