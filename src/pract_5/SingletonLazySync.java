package pract_5;

/**
 * Класс SingletonLazySync представляет собой реализацию шаблона Singleton с ленивой инициализацией и синхронизацией.
 */
public class SingletonLazySync {
    /**
     * Экземпляр класса SingletonLazySync.
     */
    private static SingletonLazySync instance;

    /**
     * Приватный конструктор для предотвращения создания экземпляров извне класса.
     */
    private SingletonLazySync() {}
    /**
     * Возвращает экземпляр класса SingletonLazySync.
     *
     * @return экземпляр SingletonLazySync
     */
    public static synchronized SingletonLazySync getInstance() {
        if (instance == null) {
            instance = new SingletonLazySync();
        }
        return instance;
    }
}

