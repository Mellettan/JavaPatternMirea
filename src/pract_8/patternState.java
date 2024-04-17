package pract_8;

/*
 Паттерн Состояние позволяет объекту изменять свое поведение при изменении его внутреннего состояния.
 Ключевая идея состоит в том, чтобы вынести логику состояний в отдельные классы,
 что делает ее более явной и управляемой.
 */

/**
 * Интерфейс, представляющий состояние объекта.
 */
interface State {
    /**
     * Метод для выполнения определенного действия в зависимости от состояния.
     *
     * @param context контекст, в котором происходит изменение состояния
     */
    void handle(Context context);
}

/**
 * Класс контекста, в котором происходит изменение состояния.
 */
class Context {
    private State state;

    /**
     * Метод для установки нового состояния.
     *
     * @param state новое состояние
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Метод для вызова обработчика текущего состояния.
     */
    public void request() {
        state.handle(this);
    }
}

/**
 * Конкретное состояние объекта.
 */
class ConcreteStateA implements State {
    @Override
    public void handle(Context context) {
        System.out.println("ConcreteStateA is handling the request.");
        // Меняем состояние контекста при необходимости
        context.setState(new ConcreteStateB());
    }
}

/**
 * Еще одно конкретное состояние объекта.
 */
class ConcreteStateB implements State {
    @Override
    public void handle(Context context) {
        System.out.println("ConcreteStateB is handling the request.");
        // Меняем состояние контекста при необходимости
        context.setState(new ConcreteStateA());
    }
}

/**
 * Класс для демонстрации использования паттерна состояние.
 */
public class patternState {
    public static void main(String[] args) {
        // Создаем контекст
        Context context = new Context();

        // Устанавливаем начальное состояние
        context.setState(new ConcreteStateA());

        // Вызываем обработчик текущего состояния.
        // При этом контекст переходит из состояния ConcreteStateA в состояние ConcreteStateB.
        context.request();

        // Вызываем обработчик текущего состояния еще раз
        context.request();
    }
}
