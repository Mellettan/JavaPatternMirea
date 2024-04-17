package pract_7;

import java.util.ArrayList;
import java.util.List;

/*
 Позволяет сгруппировать объекты в древовидные структуры для представления иерархий часть-целое.
 Компоновщик позволяет работать с отдельными объектами и составными объектами (композитами) одинаковым образом.
 */

/**
 * Интерфейс, представляющий компонент в иерархии компоновщика.
 */
interface Component {
    void operation();
}

/**
 * Листовой узел компоновщика. Представляет конечные элементы структуры.
 */
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Листовой узел " + name + " выполняет операцию.");
    }
}

/**
 * Компоновщик. Содержит листовые узлы и/или другие компоновщики.
 */
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    /**
     * Метод для добавления компонента в композицию.
     *
     * @param component компонент, который необходимо добавить
     */
    public void add(Component component) {
        children.add(component);
    }

    /**
     * Метод для удаления компонента из композиции.
     *
     * @param component компонент, который необходимо удалить
     */
    public void remove(Component component) {
        children.remove(component);
    }

    /**
     * Метод для получения дочернего компонента по индексу.
     *
     * @param i индекс дочернего компонента
     * @return дочерний компонент по указанному индексу
     */
    public Component getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        System.out.println("Компоновщик выполняет операцию.");
        for (Component component : children) {
            component.operation();
        }
    }
}

/**
 * Класс для демонстрации использования паттерна компоновщик.
 */
public class patternComposite {
    public static void main(String[] args) {
        // Создаем листовые элементы
        Leaf leaf1 = new Leaf("Leaf 1");
        Leaf leaf2 = new Leaf("Leaf 2");
        Leaf leaf3 = new Leaf("Leaf 3");

        // Создаем компоновщики
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        // Добавляем листовые элементы в компоновщики
        composite1.add(leaf1);
        composite1.add(leaf2);
        composite2.add(leaf3);

        // Добавляем компоновщики в другой компоновщик
        Composite composite = new Composite();
        composite.add(composite1);
        composite.add(composite2);

        // Выполняем операцию на компоновщике, которая также будет распространяться на все вложенные компоненты
        composite.operation();
    }
}
