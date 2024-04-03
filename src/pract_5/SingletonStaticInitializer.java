package pract_5;


/**
 * Класс представляет реализацию паттерна Singleton с использованием статического инициализатора.
 */
public class SingletonStaticInitializer {
    /**
     * Экземпляр класса SingletonStaticInitializer.
     */
    private static final SingletonStaticInitializer instance = new SingletonStaticInitializer();

    /**
     * Приватный конструктор для предотвращения создания экземпляров извне класса.
     */
    private SingletonStaticInitializer() {}

    /**
     * Возвращает экземпляр класса SingletonStaticInitializer.
     * @return экземпляр SingletonStaticInitializer
     */
    public static SingletonStaticInitializer getInstance() {
        return instance;
    }
}