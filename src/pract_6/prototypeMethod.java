package pract_6;

/*
 Прототип — это порождающий паттерн проектирования, который определяет механизм копирования объектов без погружения
 в подробности их реализации.
 */

/**
 * Интерфейс прототипа
 */
interface Prototype extends Cloneable {
    Prototype clone();
}

/**
 * Конкретный прототип
 */
class ConcretePrototype implements Prototype {
    @Override
    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Пример использования
public class prototypeMethod {
    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype();
        ConcretePrototype clonedPrototype = (ConcretePrototype) prototype.clone();
    }
}
